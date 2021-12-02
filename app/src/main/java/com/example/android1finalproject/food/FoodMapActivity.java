package com.example.android1finalproject.food;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android1finalproject.R;
import com.example.android1finalproject.main.custom.CustomMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class FoodMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private FirebaseAuth mAuth;
    private CustomMenu customMenu;

    private Request.Priority priority = Request.Priority.HIGH;

    MapView mapView;
    private GoogleMap mMap;

    //Initializing Array of Markers for reference
    ArrayList<Marker> markerArrayList = new ArrayList<>();
    //Initializing API key and setting type type
    String apiKey = "";
    String type = "";
    //Prepare request urls with required parameters
    String textSearchUrlBegin = "https://maps.googleapis.com/maps/api/place/textsearch/json?&query=";
    String textSearchUrlEnd = "+restaurants+in+toronto&key=";
    String textSearchUrl = textSearchUrlBegin + type + textSearchUrlEnd + apiKey;
    String detailsUrl ="https://maps.googleapis.com/maps/api/place/details/json?place_id=";
    // Initialize details for second API request
    String setDetails = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_map_country);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        TextView toolbar_title = findViewById(R.id.toolbar_title);

        mAuth = FirebaseAuth.getInstance();
        customMenu = new CustomMenu();

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        toolbar_title.setText(type.replace(type.charAt(0), type.toUpperCase(Locale.ROOT).charAt(0)));

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                customMenu.showMenu(FoodMapActivity.this, v, mAuth);
            }
        });

        mapView = findViewById(R.id.mapView);

        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        loadMarkers();
    }

    private void loadMarkers(){
        placesApiRequest(mMap);

        //Places API key
        apiKey = getResources().getString(R.string.google_maps_key_for_requests);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                String place_id = marker.getTag().toString();
                String placesAPICallUrl = detailsUrl + place_id + "&key=" + apiKey;

                JsonObjectRequest placeDetailObjectRequest = new JsonObjectRequest(Request.Method.GET, placesAPICallUrl, null, new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject result = response.getJSONObject("result");
                            String name = result.getString("name");
                            String formatted_address = result.getString("formatted_address");
                            String international_phone_number = result.getString("international_phone_number");
                            String website = result.getString("website");
                            String iconUriString = result.getString("icon_mask_base_uri") + ".png";

                            URI iconUri = new URI(iconUriString);
                            URL iconUrl = iconUri.toURL();

                            System.out.println(iconUrl);

                            setDetails = name + "\n\n" +
                                    formatted_address + "\n\n" +
                                    international_phone_number + "\n\n" +
                                    website;

                            // Linkify the message
                            final SpannableString s = new SpannableString(setDetails); // msg should have url to enable clicking
                            Linkify.addLinks(s, Linkify.ALL);

                            final AlertDialog d = new AlertDialog.Builder(FoodMapActivity.this)
                                    .setTitle("Info")
                                    .setPositiveButton(android.R.string.ok, null)
                                    .setMessage( s )
                                    .create();

                            d.show();
                        } catch (JSONException | URISyntaxException | MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FoodMapActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                Volley.newRequestQueue(FoodMapActivity.this).add(placeDetailObjectRequest);

                return false;
            }
        });
    }

    private void placesApiRequest(GoogleMap googleMap) {
        removeMarkers();

        //Places API key
        apiKey = getResources().getString(R.string.google_maps_key_for_requests);
        //Prepare request urls with required parameters
        textSearchUrl = textSearchUrlBegin + type + textSearchUrlEnd + apiKey;

        // Request JSON data from the provided URL and display response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, textSearchUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    LatLng latLng = new LatLng(0, 0);
                    JSONArray results=response.getJSONArray("results");
                    for(int i = 0; i < results.length(); i++){
                        JSONObject locations = results.getJSONObject(i);
                        String place_id = locations.getString("place_id");
                        String name = locations.getString("name");
                        String address = locations.getString("formatted_address");

                        //Getting latitude & longitude
                        JSONObject geometry = locations.getJSONObject("geometry");
                        JSONObject location = geometry.getJSONObject("location");
                        float lat = (float) location.getDouble("lat");
                        float lng = (float) location.getDouble("lng");

                        latLng = new LatLng(lat, lng);

                        //Add markers to map
                        Marker marker = googleMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(name)
                                .snippet(address));
                        marker.setTag(place_id);
                        markerArrayList.add(marker);
                    }
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FoodMapActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Priority getPriority() {
                return priority;
            }
        };
        //Add request to a new request queue
        Volley.newRequestQueue(FoodMapActivity.this).add(jsonObjectRequest);
    }

    private void removeMarkers() {
        if (!markerArrayList.isEmpty()) {
            for (Marker marker :
                    markerArrayList) {
                marker.remove();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
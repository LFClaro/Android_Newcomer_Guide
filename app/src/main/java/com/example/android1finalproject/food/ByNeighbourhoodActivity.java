package com.example.android1finalproject.food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.android1finalproject.housing.models.House;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class ByNeighbourhoodActivity extends AppCompatActivity implements OnMapReadyCallback {

    MapView mapView;
    TextView titleTV, toolbar_title;
    Button findBtn;
    EditText inputEt;
    private GoogleMap mMap;

    //Initializing Array of Markers for reference
    ArrayList<Marker> markerArrayList = new ArrayList<>();
    //Initializing API key and setting search type
    String apiKey = "";
    String searchType = "restaurant";
    //Prepare request urls with required parameters
    String nearbyUrl="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=43.64253900503797,-79.38720700569782&radius=1500000&type=" + searchType + "&sensor=true&key=" + apiKey;
    String detailsUrl="https://maps.googleapis.com/maps/api/place/details/json?place_id=";
    // Initialize details for second API request
    String setDetails = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_by_neighbourhood);

        toolbar_title = findViewById(R.id.toolbar_title);
        mapView = findViewById(R.id.mapView);
        titleTV = findViewById(R.id.title_text);
        findBtn = findViewById(R.id.by_price);
        inputEt = findViewById(R.id.input);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputEt.getText().toString().isEmpty()) {
                    inputEt.setError("Required");
                }
                String inputString = inputEt.getText().toString().trim();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        placesApiRequest(googleMap);

        //Places API key
        apiKey = getResources().getString(R.string.google_maps_key_for_requests);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
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

                            setDetails = name + "\n\n" +
                                    formatted_address + "\n\n" +
                                    international_phone_number + "\n\n" +
                                    website;

                            // Linkify the message
                            final SpannableString s = new SpannableString(setDetails); // msg should have url to enable clicking
                            Linkify.addLinks(s, Linkify.ALL);

                            final AlertDialog d = new AlertDialog.Builder(ByNeighbourhoodActivity.this)
                                    .setTitle("Restaurant Info")
                                    .setPositiveButton(android.R.string.ok, null)
                                    .setIcon(R.drawable.ic_baseline_food_24_black)
                                    .setMessage( s )
                                    .create();

                            d.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ByNeighbourhoodActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                Volley.newRequestQueue(ByNeighbourhoodActivity.this).add(placeDetailObjectRequest);

                return false;
            }
        });
    }

    private void placesApiRequest(GoogleMap googleMap) {
        removeMarkers();

        //Places API key
        apiKey = getResources().getString(R.string.google_maps_key_for_requests);
        //Prepare request urls with required parameters
        nearbyUrl="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=43.64253900503797,-79.38720700569782&radius=1500000&type=" + searchType + "&sensor=true&key=" + apiKey;

        // Request JSON data from the provided URL and display response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, nearbyUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    LatLng latLng = new LatLng(0, 0);
                    JSONArray results=response.getJSONArray("results");
                    for(int i = 0; i < results.length(); i++){
                        JSONObject locations = results.getJSONObject(i);
                        String place_id = locations.getString("place_id");
                        String name = locations.getString("name");
                        String vicinity = locations.getString("vicinity");

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
                                .snippet(vicinity));
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
                Toast.makeText(ByNeighbourhoodActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        //Add request to a new request queue
        Volley.newRequestQueue(ByNeighbourhoodActivity.this).add(jsonObjectRequest);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
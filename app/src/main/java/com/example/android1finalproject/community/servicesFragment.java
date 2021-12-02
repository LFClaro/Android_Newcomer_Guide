package com.example.android1finalproject.community;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android1finalproject.R;
import com.example.android1finalproject.databinding.FragmentServicesBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class servicesFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
            placesApiRequest(googleMap);

        }
    };

    private void placesApiRequest(GoogleMap googleMap) {
        //Places API key
        String apiKey = getResources().getString(R.string.google_maps_key_for_requests);;
        String searchType = "church";

        //Prepare request urls with required parameters
        String nearbyUrl="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=43.64253900503797,-79.38720700569782&radius=1500000&type=" + searchType + "&sensor=true&key=" + apiKey;

        // Request JSON data from the provided URL and display reposnse
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, nearbyUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    LatLng latLng = new LatLng(0, 0);
                    JSONArray results=response.getJSONArray("results");
                    for(int i=0;i<results.length();i++)
                    {
                        JSONObject locations = results.getJSONObject(i);
                        String name = locations.getString("name");
                        String vicinity = locations.getString("vicinity");
                        JSONObject geometry = locations.getJSONObject("geometry");
                        JSONObject location = geometry.getJSONObject("location");
                        float lat = (float) location.getDouble("lat");
                        float lng = (float) location.getDouble("lng");

                        latLng = new LatLng(lat, lng);

                        String details = "Name: " + name + "\n\n" +
                                "Address: " + vicinity;

                        //Add markers to map
                        googleMap.addMarker(new MarkerOptions().position(latLng).title(details));
                    }
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));

                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(@NonNull Marker marker) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Services")
                                    .setMessage(marker.getTitle())

                                    .setNegativeButton("Close", null)
                                    .show();
                            //Toast.makeText(HousingActivity3.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }

        );
//        To add a request header, use getHeaders() anonymous method
//        {@Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//            Map<String, String> headers = new HashMap<>();
//            headers.put("Authorization", "Bearer " + "the-key-abcd-1234-dfksdfjjklkjl-jslkcn-asceoi");
//            return headers;
//        }
//        }
        //JsonObjectRequest

        //Add request to a new request queue
        Volley.newRequestQueue(getActivity()).add(jsonObjectRequest);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_health_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}
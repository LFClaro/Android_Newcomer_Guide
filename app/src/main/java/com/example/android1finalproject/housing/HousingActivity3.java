package com.example.android1finalproject.housing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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

import com.example.android1finalproject.R;
import com.example.android1finalproject.housing.models.House;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class HousingActivity3 extends AppCompatActivity implements OnMapReadyCallback {

    MapView mapView;
    TextView titleTV, toolbar_title;
    Button findBtn;
    EditText inputEt;
    ArrayList<House> houseArrayList;
    ArrayList<Marker> markerArrayList;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing3);

        houseArrayList = new ArrayList<>();
        markerArrayList = new ArrayList<>();

        dummyData();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Housing");

        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Laws");

        mapView = findViewById(R.id.mapView);
        titleTV = findViewById(R.id.title_text);
        findBtn = findViewById(R.id.by_price);
        inputEt = findViewById(R.id.input);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);

        Intent intent = getIntent();
        /*int index = intent.getIntExtra("index", 0);
        if (index == 1) {
            //titleTV.setText("To Buy");
        } else if (index == 2) {
            //titleTV.setText("To Lease");
        } else {
            //
        }*/

        String type = intent.getStringExtra("type");
        if (type.equals("location")) {
            titleTV.setText("By Location");
            inputEt.setHint("Enter Location");
        }

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputEt.getText().toString().isEmpty()) {
                    inputEt.setError("Required");
                }

                String inputString = inputEt.getText().toString().trim();

                if (type.equals("location")) {
                    ArrayList<House> tempList = new ArrayList<>();
                    for (House house :
                            houseArrayList) {
                        if (house.getName().trim().toLowerCase().contains(inputString.toLowerCase())) {
                            tempList.add(house);
                        }
                    }
                    loadMarkers(tempList, false);
                    closeKeyboard();

                } else {

                    if (inputString.contains("-")) {

                        String[] parts = inputString.split("-");
                        double part1 = Double.parseDouble(parts[0]); // 004
                        double part2 = Double.parseDouble(parts[1]);
                        ArrayList<House> tempList = new ArrayList<>();
                        for (House house :
                                houseArrayList) {
                            if (house.getPrice() >= part1 && house.getPrice() <= part2) {
                                tempList.add(house);
                            }
                        }
                        loadMarkers(tempList, false);
                        closeKeyboard();
                    } else {
                        inputEt.setError("Invalid Price Range(i.e 100-500)");
                    }
                }
            }
        });
    }

    private void dummyData() {
        houseArrayList.add(new House(43.651070, -79.347015, "Toronto", 1000.0));
        houseArrayList.add(new House(43.6532, -79.3832, "The PATH - City Hall, Toronto", 2000.0));
        houseArrayList.add(new House(43.8417, -79.4766, "Pleasant Ridge Ave, Thornhill, ON L4J 0G2", 400.0));
        houseArrayList.add(new House(44.0849, -79.0920, "Foxfire Chase, Uxbridge, ON L9P 1R4,", 4000.0));
        houseArrayList.add(new House(44.1046, -79.3942, "East Gwillimbury, ON,", 50.0));
        houseArrayList.add(new House(43.6619, -79.3748, "Horticultural Ave, Toronto, ON M5A 2P2, Canada", 200.0));
        houseArrayList.add(new House(43.6425, -79.3872, "Front St W, Toronto, ON M5V 2T6, Canada", 10.0));
        houseArrayList.add(new House(43.6328, -79.4257, "Dufferin St, Toronto, ON M6K 3C3, Canada", 100.0));
        houseArrayList.add(new House(43.6845, -79.3649, "Young Welcome Centre, Toronto", 6000.0));
        houseArrayList.add(new House(43.6559, -79.4104, "Palmerston Ave, Toronto, ON M6G 2P7, Canada", 71000.0));
        houseArrayList.add(new House(43.6696, -79.4464, "St Clarens Ave, Toronto, ON M6H 3X6, Canada", 11000.0));
        houseArrayList.add(new House(43.6440, -79.4428, "Sorauren Ave, Toronto, ON M6R 2E5, Canada", 13000.0));
        houseArrayList.add(new House(43.6423, -79.4030, "Wellington St W Second Floor", 100000.0));
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        loadMarkers(houseArrayList, true);
    }

    public void loadMarkers(ArrayList<House> houses, boolean isAll) {
        removeMarkers();

        if (houses.isEmpty()) {
            Toast.makeText(HousingActivity3.this, "No Houses Found", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < houses.size(); i++) {
            LatLng latLng = new LatLng(houses.get(i).getLatitude(), houses.get(i).getLongitude());

            String sb = "Title: " + houses.get(i).getName() + "\n\n" +
                    "Price: " + houses.get(i).getPrice()+"$";
            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(sb));
            markerArrayList.add(marker);


            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    new AlertDialog.Builder(HousingActivity3.this)
                            .setTitle("Info")
                            .setMessage(marker.getTitle())

                            .setNegativeButton("Close", null)
                            .show();
                    //Toast.makeText(HousingActivity3.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            if (isAll) {
                if (i == 9) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
                }
            } else {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
            }
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
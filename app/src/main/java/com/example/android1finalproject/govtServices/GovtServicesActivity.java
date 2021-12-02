package com.example.android1finalproject.govtServices;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android1finalproject.R;
import com.example.android1finalproject.housing.HousingActivity;
import com.example.android1finalproject.main.custom.CustomMenu;
import com.google.firebase.auth.FirebaseAuth;

public class GovtServicesActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CustomMenu customMenu;
    private String service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govtservices);

        mAuth = FirebaseAuth.getInstance();
        customMenu = new CustomMenu();

        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        TextView toolbar_title = findViewById(R.id.toolbar_title);

        toolbar_title.setText("Govt. Services");

        Button healthCard = findViewById(R.id.health_card_btn);
        Button SIN = findViewById(R.id.SIN_btn);
        Button photo_ID = findViewById(R.id.photo_ID_btn);
        Button license = findViewById(R.id.license_btn);
        Button vehicle_registration = findViewById(R.id.vehicle_registration_btn);
        Button furniture_rental = findViewById(R.id.furniture_rental_btn);

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customMenu.showMenu(GovtServicesActivity.this, v, mAuth);
            }
        });

        healthCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = "healthCard";
                transition(v);
            }
        });

        SIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = "SIN";
                transition(v);
            }
        });

        photo_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = "photo_ID";
                transition(v);
            }
        });

        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = "license";
                transition(v);
            }
        });

        vehicle_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = "vehicle_registration";
                transition(v);
            }
        });

        furniture_rental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = "furniture_rental";
                transition(v);
            }
        });

    }

    private void transition(View v) {
        Intent intent = new Intent(GovtServicesActivity.this, TransitionActivity.class);
        intent.putExtra("service", service);
        startActivity(intent);
    }
}

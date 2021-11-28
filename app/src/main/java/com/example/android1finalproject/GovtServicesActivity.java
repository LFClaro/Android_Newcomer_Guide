package com.example.android1finalproject;

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

public class GovtServicesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govtServices);

        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        TextView toolbar_title = findViewById(R.id.toolbar_title);

        Button healthCard = findViewById(R.id.health_card_btn);
        Button SIN = findViewById(R.id.SIN_btn);
        Button photo_ID = findViewById(R.id.photo_ID_btn);
        Button license = findViewById(R.id.license_btn);
        Button vehicle_registration = findViewById(R.id.vehicle_registration_btn);
        Button furniture_rental = findViewById(R.id.furniture_rental_btn);

        healthCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition(v);
            }
        });

        SIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition(v);
            }
        });

        photo_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition(v);
            }
        });

        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition(v);
            }
        });

        vehicle_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition(v);
            }
        });

        furniture_rental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition(v);
            }
        });

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                showMenu(v);
            }
        });
    }

    private void showMenu(View v){
        PopupMenu popupMenu = new PopupMenu(GovtServicesActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.community_menu)
                    Toast.makeText(GovtServicesActivity.this, "TODO Community Menu goes here", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.food_menu)
                    Toast.makeText(GovtServicesActivity.this, "TODO Food Menu goes here", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.health_menu)
                    Toast.makeText(GovtServicesActivity.this, "TODO Health Menu goes here", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.housing_menu)
                    Toast.makeText(GovtServicesActivity.this, "TODO Housing Menu goes here", Toast.LENGTH_SHORT).show();;
                if(menuItem.getItemId() == R.id.govt_menu)
                    Toast.makeText(GovtServicesActivity.this, "TODO Government Menu goes here", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        popupMenu.show();
    }

    private void transition(View v) {
        Intent intent = new Intent(this, TransitionActivity.class);
     //   intent.putExtra("student", student);
        startActivity(intent);
    }
}

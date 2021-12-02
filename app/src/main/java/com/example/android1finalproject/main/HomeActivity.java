package com.example.android1finalproject.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android1finalproject.govtServices.GovtServicesActivity;
import com.example.android1finalproject.food.FoodActivity;
import com.example.android1finalproject.health.HealthActivity;
import com.example.android1finalproject.R;
import com.example.android1finalproject.community.CommunityActivity;
import com.example.android1finalproject.housing.HousingActivity;
import com.example.android1finalproject.main.custom.CustomMenu;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CustomMenu customMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        TextView toolbar_title = findViewById(R.id.toolbar_title);


        mAuth = FirebaseAuth.getInstance();
        customMenu = new CustomMenu();

        toolbar_title.setText("Main Menu");

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                customMenu.showMenu(HomeActivity.this, v, mAuth);
            }
        });

        Button communityButton = findViewById(R.id.btnCommunity);
        Button foodButton = findViewById(R.id.btnFood);
        Button housingButton = findViewById(R.id.btnHousing);
        Button healthButton = findViewById(R.id.btnHealth);
        Button govtButton = findViewById(R.id.btnGovt);

        housingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HousingActivity.class);
                startActivity(intent);
            }
        });

        healthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent healthMain = new Intent(HomeActivity.this, HealthActivity.class);
                startActivity(healthMain);
            }
        });

        communityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent communityMain = new Intent(HomeActivity.this, CommunityActivity.class);
                startActivity(communityMain);
            }
        });

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodMain = new Intent(HomeActivity.this, FoodActivity.class);
                startActivity(foodMain);
            }
        });

        govtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent govtServicesMain = new Intent(HomeActivity.this, GovtServicesActivity.class );
                startActivity(govtServicesMain);
            }
        });
    }
}
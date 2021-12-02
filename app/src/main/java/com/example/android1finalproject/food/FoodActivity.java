package com.example.android1finalproject.food;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android1finalproject.R;
import com.example.android1finalproject.main.custom.CustomMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FoodActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CustomMenu customMenu;

    Button countryBtn, neighborhoodBtn, farmersMarketsBtn, groceryStoresBtn, salesAndCouponsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        TextView toolbar_title = findViewById(R.id.toolbar_title);

        mAuth = FirebaseAuth.getInstance();
        customMenu = new CustomMenu();

        toolbar_title.setText("Food");

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                customMenu.showMenu(FoodActivity.this, v, mAuth);
            }
        });

        countryBtn = findViewById(R.id.btnByCountry);
        neighborhoodBtn = findViewById(R.id.btnByNeighbourhood);
        farmersMarketsBtn = findViewById(R.id.btnFarmersMarket);
        groceryStoresBtn = findViewById(R.id.btnGroceryStore);
        salesAndCouponsBtn = findViewById(R.id.btnSalesAndCoupons);

        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this, ByCountryActivityMain.class);
                startActivity(intent);
            }
        });

        neighborhoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this, ByNeighbourhoodActivity.class);
                startActivity(intent);
            }
        });

        farmersMarketsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(FoodActivity.this, FoodMapActivity.class);
                mapIntent.putExtra("type","Farmer's Market");
                startActivity(mapIntent);
            }
        });

        groceryStoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(FoodActivity.this, FoodMapActivity.class);
                mapIntent.putExtra("type","Grocery Stores");
                startActivity(mapIntent);
            }
        });
    }
}
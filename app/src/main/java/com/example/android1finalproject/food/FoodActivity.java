package com.example.android1finalproject.food;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android1finalproject.R;
import com.example.android1finalproject.main.CustomMenu;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        neighborhoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> city = new HashMap<>();
                city.put("name", "Los Angeles");
                city.put("state", "CA");
                city.put("country", "USA");

                db.collection("cities").document("LA")
                        .set(city)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TestFirebase", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TestFirebase", "Error writing document", e);
                            }
                        });
            }
        });
    }
}
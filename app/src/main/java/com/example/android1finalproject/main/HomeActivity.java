package com.example.android1finalproject.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android1finalproject.govtServices.GovtServicesActivity;
import com.example.android1finalproject.health.HealthActivity;
import com.example.android1finalproject.R;
import com.example.android1finalproject.community.CommunityActivity;
import com.example.android1finalproject.housing.HousingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
                Toast.makeText(HomeActivity.this, "TODO Food Menu goes here", Toast.LENGTH_SHORT).show();
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

//    private void showMenu(Activity a, View v){
//        PopupMenu popupMenu = new PopupMenu(a, v);
//        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                if(menuItem.getItemId() == R.id.nav_community){
//                    Intent intent = new Intent(a, CommunityActivity.class);
//                    startActivity(intent);
//                }
//                if(menuItem.getItemId() == R.id.nav_food)
//                    Toast.makeText(a, "TODO Food Menu goes here", Toast.LENGTH_SHORT).show();
//                if(menuItem.getItemId() == R.id.nav_health){
//                    Intent intent = new Intent(a, HealthActivity.class);
//                    startActivity(intent);
//                }
//                if(menuItem.getItemId() == R.id.nav_housing) {
//                    Intent intent = new Intent(a, HousingActivity.class);
//                    startActivity(intent);
//                }
//                if(menuItem.getItemId() == R.id.nav_govt)
//                    Toast.makeText(a, "TODO Government Menu goes here", Toast.LENGTH_SHORT).show();
//                if(menuItem.getItemId() == R.id.nav_sign_out)
//                    signOut();
//                return true;
//            }
//        });
//
//        popupMenu.show();
//    }

//    private void signOut() {
//        mAuth.signOut();
//        updateUI(null);
//    }
//
//    private void updateUI(FirebaseUser user) {
//        Toast.makeText(HomeActivity.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
//        reload();
//    }
//
//    private void reload() {
//        Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
//        startActivity(intent);
//    }
}
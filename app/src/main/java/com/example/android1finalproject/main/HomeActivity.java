package com.example.android1finalproject.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android1finalproject.R;
import com.example.android1finalproject.health.HealthActivity;
import com.example.android1finalproject.housing.HousingActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        TextView toolbar_title = findViewById(R.id.toolbar_title);

        toolbar_title.setText("Main Menu");

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                showMenu(v);
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
    }

    private void showMenu(View v){
        PopupMenu popupMenu = new PopupMenu(HomeActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.community_menu)
                    Toast.makeText(HomeActivity.this, "TODO Community Menu goes here", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.food_menu)
                    Toast.makeText(HomeActivity.this, "TODO Food Menu goes here", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.health_menu)
                    Toast.makeText(HomeActivity.this, "TODO Health Menu goes here", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.housing_menu)
                    Toast.makeText(HomeActivity.this, "TODO Housing Menu goes here", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.govt_menu)
                    Toast.makeText(HomeActivity.this, "TODO Government Menu goes here", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        popupMenu.show();
    }
}
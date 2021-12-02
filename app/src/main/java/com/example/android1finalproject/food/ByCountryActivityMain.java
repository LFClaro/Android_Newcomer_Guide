package com.example.android1finalproject.food;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android1finalproject.R;

public class ByCountryActivityMain extends AppCompatActivity {

    TextView titleTV, toolbar_title;
    Button brazlilianBtn, bangladeshiBtn, chineseBtn, indianBtn, jamaicanBtn, japaneseBtn, russianBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_country);

        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Food");

        titleTV = findViewById(R.id.title_text);

        brazlilianBtn = findViewById(R.id.btnBrazilian);
        bangladeshiBtn = findViewById(R.id.btnBangladeshi);
        chineseBtn = findViewById(R.id.btnChinese);
        indianBtn = findViewById(R.id.btnIndian);
        jamaicanBtn = findViewById(R.id.btnJamaican);
        japaneseBtn = findViewById(R.id.btnJapanese);
        russianBtn = findViewById(R.id.btnRussian);

        brazlilianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByCountryActivityMain.this, FoodMapActivity.class);
                intent.putExtra("type","brazilian");
                startActivity(intent);
            }
        });

        bangladeshiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByCountryActivityMain.this, FoodMapActivity.class);
                intent.putExtra("type","bangladeshi");
                startActivity(intent);
            }
        });

        chineseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByCountryActivityMain.this, FoodMapActivity.class);
                intent.putExtra("type","chinese");
                startActivity(intent);
            }
        });

        indianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByCountryActivityMain.this, FoodMapActivity.class);
                intent.putExtra("type","indian");
                startActivity(intent);
            }
        });

        jamaicanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByCountryActivityMain.this, FoodMapActivity.class);
                intent.putExtra("type","jamaican");
                startActivity(intent);
            }
        });

        japaneseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByCountryActivityMain.this, FoodMapActivity.class);
                intent.putExtra("type","japanese");
                startActivity(intent);
            }
        });

        russianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByCountryActivityMain.this, FoodMapActivity.class);
                intent.putExtra("type","russian");
                startActivity(intent);
            }
        });

    }
}
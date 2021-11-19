package com.example.android1finalproject.housing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android1finalproject.R;

public class HousingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing);

        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Housing");

        Button rent = findViewById(R.id.btnRent);
        Button buy = findViewById(R.id.btnBuy);
        Button lease = findViewById(R.id.btnLease);

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HousingActivity.this,HousingActivity2.class);
                intent.putExtra("index",0);
                startActivity(intent);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HousingActivity.this,HousingActivity2.class);
                intent.putExtra("index",1);
                startActivity(intent);
            }
        });

        lease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HousingActivity.this,HousingActivity2.class);
                intent.putExtra("index",2);
                startActivity(intent);
            }
        });
    }
}
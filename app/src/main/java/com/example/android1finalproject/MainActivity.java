package com.example.android1finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        TextView toolbar_title = findViewById(R.id.toolbar_title);

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent healthMain = new Intent(MainActivity.this, HealthActivity.class);//Parent aActivity then the healthActivity
                startActivity(healthMain);

               // Toast.makeText(MainActivity.this, "TODO Menu goes here", Toast.LENGTH_SHORT).show();
            }


        });
    }
}
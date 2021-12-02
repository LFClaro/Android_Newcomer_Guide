package com.example.android1finalproject.housing;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android1finalproject.R;
import com.example.android1finalproject.main.custom.CustomMenu;
import com.google.firebase.auth.FirebaseAuth;

public class HousingActivity2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CustomMenu customMenu;

    TextView titleTV, descriptionTV, toolbar_title;
    Button lawsBtn, byLocationBtn, byPriceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing2);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        TextView toolbar_title = findViewById(R.id.toolbar_title);

        mAuth = FirebaseAuth.getInstance();
        customMenu = new CustomMenu();

        toolbar_title.setText("Housing");

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                customMenu.showMenu(HousingActivity2.this, v, mAuth);
            }
        });

        titleTV = findViewById(R.id.title_text);
        descriptionTV = findViewById(R.id.description);
        lawsBtn = findViewById(R.id.rules_btn);
        byPriceBtn = findViewById(R.id.by_price);
        byLocationBtn = findViewById(R.id.by_location);

        Intent intent = getIntent();
        String index = intent.getStringExtra("index");
        if (index.equals("buy")) {
            titleTV.setText("To Buy");
            descriptionTV.setText("Documents needed to buy:\n\n 1.Refrences\n 2.Proof of Income\n 3.Govt ID");
            lawsBtn.setText("Click here for Buying laws");
        } else if (index.equals("lease")) {
            titleTV.setText("To Lease");
            descriptionTV.setText("Documents needed for lease:\n\n 1.Refrences\n 2.Proof of Income\n 3.Govt ID");
            lawsBtn.setText("Click here for Lease laws");
        } else {
            titleTV.setText("To Rent");
            descriptionTV.setText("Documents needed for rental:\n\n 1.Refrences\n 2.Proof of Income\n 3.Govt ID");
            lawsBtn.setText("Click here for Rental laws");
        }

        lawsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HousingActivity2.this, LawsActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        });

        byPriceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HousingActivity2.this, HousingActivity3.class);
                intent.putExtra("index", index);
                intent.putExtra("type", "price");
                startActivity(intent);
            }
        });

        byLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HousingActivity2.this, HousingActivity3.class);
                intent.putExtra("index", index);
                intent.putExtra("type", "location");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
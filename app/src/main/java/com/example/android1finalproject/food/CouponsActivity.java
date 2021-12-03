package com.example.android1finalproject.food;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.android1finalproject.R;
import com.example.android1finalproject.food.models.Coupon;
import com.example.android1finalproject.main.custom.CustomMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CouponsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CustomMenu customMenu;

    TextView titleTV, cardName1, cardName2, cardName3, cardName4, cardName5, pct1, pct2, pct3, pct4, pct5, valid1, valid2, valid3, valid4, valid5;
    CardView card1, card2, card3, card4, card5;
    Button btnRedeem1, btnRedeem2, btnRedeem3, btnRedeem4, btnRedeem5;

    ArrayList<Coupon> coupons = new ArrayList<Coupon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        TextView toolbar_title = findViewById(R.id.toolbar_title);

        mAuth = FirebaseAuth.getInstance();
        customMenu = new CustomMenu();

        toolbar_title.setText("Sales & Coupons");

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                customMenu.showMenu(CouponsActivity.this, v, mAuth);
            }
        });

        titleTV = findViewById(R.id.title_text);

        card1 = findViewById(R.id.card_view);
        card2 = findViewById(R.id.card_view2);
        card3 = findViewById(R.id.card_view3);
        card4 = findViewById(R.id.card_view4);
        card5 = findViewById(R.id.card_view5);

        cardName1 = findViewById(R.id.coupon_name);
        cardName2 = findViewById(R.id.coupon_name2);
        cardName3 = findViewById(R.id.coupon_name3);
        cardName4 = findViewById(R.id.coupon_name4);
        cardName5 = findViewById(R.id.coupon_name5);

        pct1 = findViewById(R.id.percent);
        pct2 = findViewById(R.id.percent2);
        pct3 = findViewById(R.id.percent3);
        pct4 = findViewById(R.id.percent4);
        pct5 = findViewById(R.id.percent5);

        valid1 = findViewById(R.id.valid);
        valid2 = findViewById(R.id.valid2);
        valid3 = findViewById(R.id.valid3);
        valid4 = findViewById(R.id.valid4);
        valid5 = findViewById(R.id.valid5);

        btnRedeem1 = findViewById(R.id.redeemBtn);
        btnRedeem2 = findViewById(R.id.redeemBtn2);
        btnRedeem3 = findViewById(R.id.redeemBtn3);
        btnRedeem4 = findViewById(R.id.redeemBtn4);
        btnRedeem5 = findViewById(R.id.redeemBtn5);

        getCoupons();
    }

    private void getCoupons() {
//        coupons.add(new Coupon("Brasil Stone Grill", 25, new Date(2021, 12, 31), "https://www.facebook.com/Brasilstonegrilltoronto/"));
//        coupons.add(new Coupon("The Real Jerk", 20, new Date(2022, 01, 31), "https://www.therealjerk.com/"));
//        coupons.add(new Coupon("Cibo", 15, new Date(2022, 02, 28), "http://www.cibowinebar.com/"));
//        coupons.add(new Coupon("WVRST", 30, new Date(2022, 04, 30), "https://wvrst.com/"));
//        coupons.add(new Coupon("Auberge du Pommier", 10, new Date(2022, 05, 30), "https://www.aubergedupommier.com/"));

        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        for (int c = 0; c < coupons.size(); c++) {
//            db.collection("coupons").document(String.valueOf(c)).set(coupons.get(c));
//        }

        db.collection("coupons")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Firestore", document.getId() + " => " + document.getData());
                                coupons.add(new Coupon(document.getString("name"), document.getLong("percentage").intValue(), document.getDate("validUntil"), document.getString("website")));
                            }
                            populateCoupons(coupons, true);
                        } else {
                            Log.d("Firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void populateCoupons(ArrayList<Coupon> coupons, boolean isAll) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        cardName1.setText(coupons.get(0).getName());
        cardName2.setText(coupons.get(1).getName());
        cardName3.setText(coupons.get(2).getName());
        cardName4.setText(coupons.get(3).getName());
        cardName5.setText(coupons.get(4).getName());

        pct1.setText(coupons.get(0).getPercentage() + "%");
        pct2.setText(coupons.get(1).getPercentage() + "%");
        pct3.setText(coupons.get(2).getPercentage() + "%");
        pct4.setText(coupons.get(3).getPercentage() + "%");
        pct5.setText(coupons.get(4).getPercentage() + "%");

        valid1.setText("Valid until "+ dateFormat.format(coupons.get(0).getValidUntil()));
        valid2.setText("Valid until "+ dateFormat.format(coupons.get(1).getValidUntil()));
        valid3.setText("Valid until "+ dateFormat.format(coupons.get(2).getValidUntil()));
        valid4.setText("Valid until "+ dateFormat.format(coupons.get(3).getValidUntil()));
        valid5.setText("Valid until "+ dateFormat.format(coupons.get(4).getValidUntil()));

        btnRedeem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(coupons.get(0).getWebsite())));
            }
        });
        btnRedeem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(coupons.get(1).getWebsite())));
            }
        });
        btnRedeem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(coupons.get(2).getWebsite())));
            }
        });
        btnRedeem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(coupons.get(3).getWebsite())));
            }
        });
        btnRedeem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(coupons.get(4).getWebsite())));
            }
        });
    }
}
package com.example.android1finalproject.govtServices;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android1finalproject.R;
import com.example.android1finalproject.govtServices.Data.DataAccess;
import com.example.android1finalproject.main.CustomMenu;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;

public class TransitionActivity extends AppCompatActivity {
        private FirebaseAuth mAuth;
        private CustomMenu customMenu;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_transition);

            mAuth = FirebaseAuth.getInstance();
            customMenu = new CustomMenu();

            ListView listView = findViewById(R.id.listView);
            ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
            ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
            TextView toolbar_title = findViewById(R.id.toolbar_title);
            TextView textView_Service = findViewById(R.id.textView_Service);
            TextView textView_Location = findViewById(R.id.textView_Location);

            toolbar_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    customMenu.showMenu(TransitionActivity.this, v, mAuth);
                }
            });
            Button btn_searchLocation = findViewById(R.id.btn_showLocations);
            Intent intent = getIntent();
            String service = intent.getStringExtra("service");
            try {
                DataAccess dataAccess = DataAccess.getInstance();
                textView_Service.setText(dataAccess.getServiceDetail(service).getServiceName());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(TransitionActivity.this, R.layout.activity_govtservices_listview, dataAccess.getServiceDetail(service).getDocuments());
                listView.setAdapter(adapter);
                textView_Location.setText(dataAccess.getServiceDetail(service).getServiceCenter());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            btn_searchLocation.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(TransitionActivity.this, Ma
                    intent.putExtra("location", "HealthCardLocation");pActivity.class);
                    startActivity(intent);*/
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=Service Ontario");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });
        }
}
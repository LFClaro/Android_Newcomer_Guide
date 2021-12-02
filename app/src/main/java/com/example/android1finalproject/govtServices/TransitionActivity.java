package com.example.android1finalproject.govtServices;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android1finalproject.R;
import com.example.android1finalproject.govtServices.Data.DataAccess;
import com.example.android1finalproject.govtServices.Data.ServiceDetailsModel;
import com.example.android1finalproject.main.custom.CustomMenu;
import com.google.firebase.auth.FirebaseAuth;

public class TransitionActivity extends AppCompatActivity {
    ListView listView;
    ImageView toolbar_menu;
    ImageView toolbar_logo;
    TextView toolbar_title;
    TextView textView_Service;
    TextView textView_Location;
    private FirebaseAuth mAuth;
    private CustomMenu customMenu;
    String serviceCenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        mAuth = FirebaseAuth.getInstance();
        customMenu = new CustomMenu();

        listView = findViewById(R.id.listView);
        toolbar_menu = findViewById(R.id.toolbar_menu);
        toolbar_logo = findViewById(R.id.toolbar_logo);
        toolbar_title = findViewById(R.id.toolbar_title);
        textView_Service = findViewById(R.id.textView_Service);
        textView_Location = findViewById(R.id.textView_Location);

        toolbar_title.setText("");

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customMenu.showMenu(TransitionActivity.this, v, mAuth);
            }
        });
        Button btn_searchLocation = findViewById(R.id.btn_showLocations);
        Intent intent = getIntent();
        String service = intent.getStringExtra("service");

        DataAccess dataAccess = DataAccess.getInstance();
        dataAccess.fetchServiceDetails(service, this::populateUI);

        btn_searchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Emulator only --> we need to set the emulator location manually,
                //else, the emulator will take the San Franciso location by default.
                Uri gmmIntentUri = Uri.parse(String.format("geo:0,0?q=%s", serviceCenter));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    private void populateUI(ServiceDetailsModel details) {
        toolbar_title.setText(details.getServiceName().replace(" Application", ""));
        textView_Service.setText(details.getServiceName());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(TransitionActivity.this, R.layout.activity_govtservices_listview, details.getDocuments());
        listView.setAdapter(adapter);
        serviceCenter = details.getServiceCenter();
        textView_Location.setText(serviceCenter);
    }
}
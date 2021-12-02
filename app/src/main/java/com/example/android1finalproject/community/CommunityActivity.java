package com.example.android1finalproject.community;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.android1finalproject.R;
import com.example.android1finalproject.food.ByNeighbourhoodActivity;
import com.example.android1finalproject.main.custom.CustomMenu;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.android1finalproject.databinding.ActivityCommunityBinding;
import com.google.firebase.auth.FirebaseAuth;

public class CommunityActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCommunityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCommunityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("   Community");
        getSupportActionBar().setLogo(R.drawable.ic_mapleleaf_40);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_community);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_community);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
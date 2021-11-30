package com.example.android1finalproject.main;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.android1finalproject.R;
import com.example.android1finalproject.community.CommunityActivity;
import com.example.android1finalproject.food.FoodActivity;
import com.example.android1finalproject.health.HealthActivity;
import com.example.android1finalproject.housing.HousingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CustomMenu {
    private Login login = new Login();
    private FirebaseAuth mAuth;

    public CustomMenu() { mAuth = FirebaseAuth.getInstance(); }

    public void showMenu(Activity a, View v, FirebaseAuth mAuth){
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);

        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.nav_community){
                    Intent intent = new Intent(a, CommunityActivity.class);
                    a.startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_food){
                    Intent intent = new Intent(a, FoodActivity.class);
                    a.startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_health){
                    Intent intent = new Intent(a, HealthActivity.class);
                    a.startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_housing) {
                    Intent intent = new Intent(a, HousingActivity.class);
                    a.startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_govt)
                    Toast.makeText(a, "TODO Government Menu goes here", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.nav_sign_out)
                    login.signOut(a, mAuth);
                return true;
            }
        });
        popupMenu.show();

        Menu menu = popupMenu.getMenu();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            MenuItem signoutItem = menu.findItem(R.id.nav_sign_out);
            signoutItem.setTitle("Sign out " + email);
        }
    }
}

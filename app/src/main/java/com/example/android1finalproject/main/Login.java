package com.example.android1finalproject.main;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login {
    private FirebaseAuth mAuth;

    public Login() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void signOut(Activity a, FirebaseAuth mAuth) {
        mAuth.signOut();
        updateUI(a,null);
    }

    public void updateUI(Activity a, FirebaseUser user) {
        Toast.makeText(a, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
        reload(a);
    }

    public void reload(Activity a) {
        Intent intent = new Intent(a, LogInActivity.class);
        a.startActivity(intent);
    }
}

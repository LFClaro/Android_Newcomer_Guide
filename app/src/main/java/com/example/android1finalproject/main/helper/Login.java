package com.example.android1finalproject.main.helper;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.android1finalproject.main.HomeActivity;
import com.example.android1finalproject.main.LogInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login {
    private static final String TAG = "Login";
    private FirebaseAuth mAuth;

    public Login() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void signOut(Activity a, FirebaseAuth mAuth) {
        mAuth.signOut();
        updateUI(a,null);
    }

    public void updateUI(Activity a, FirebaseUser user) {
        if (user != null) {
            Toast.makeText(a, "Authentication Successful.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(a, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
        }
        reload(a, user);
    }

    public void reload(Activity a, FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(a, HomeActivity.class);
            a.startActivity(intent);
        } else {
            Intent intent = new Intent(a, LogInActivity.class);
            a.startActivity(intent);
        }
    }
}

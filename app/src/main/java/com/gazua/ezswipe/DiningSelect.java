package com.gazua.ezswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DiningSelect extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_select);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signOutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                mAuth.signOut();
                startActivity(new Intent(DiningSelect.this, MainActivity.class));
                Toast.makeText(DiningSelect.this, "Sign Out.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}

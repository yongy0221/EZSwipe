package com.gazua.ezswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DiningSelect extends AppCompatActivity {

//    final FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference ref = database.getReference("https://ezswipe-1db4e.firebaseio.com/");

    private TextView MessageText;

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

//        MessageText = (TextView)findViewById(R.id.msgText);



    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

//        ref.child("Test").setValue("Test!!");
    }
}

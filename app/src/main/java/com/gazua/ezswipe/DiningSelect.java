package com.gazua.ezswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DiningSelect extends AppCompatActivity {

    FirebaseAuth mAuth;
    public String message="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_select);
        TextView textView = findViewById(R.id.status);
        textView.setText(message);
        mAuth = FirebaseAuth.getInstance();
    }
    public void confirmClicked(View v){
        Intent intent = new Intent(getApplicationContext(), BuyerPrice.class);
        startActivity(intent);
    }
    public void bpClicked(View v){
        message="@Bplate";
        TextView textView = findViewById(R.id.status);
        textView.setText(message);
    }
    public void cvClicked(View v){
        message="@Covel";
        TextView textView = findViewById(R.id.status);
        textView.setText(message);
    }
    public void ftClicked(View v){
        message="@Feast";
        TextView textView = findViewById(R.id.status);
        textView.setText(message);
    }
    public void dnClicked(View v){
        message="@DeNeve.";
        TextView textView = findViewById(R.id.status);
        textView.setText(message);
    }

}

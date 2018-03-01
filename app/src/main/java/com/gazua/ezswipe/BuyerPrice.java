package com.gazua.ezswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;


public class BuyerPrice extends AppCompatActivity {

    EditText priceInput;
    EditText numberInput;

    String numPerson;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_price);

        final Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                priceInput = (EditText) findViewById(R.id.priceText);
                numberInput = (EditText) findViewById(R.id.personText);

                numPerson = numberInput.getText().toString();
                price = priceInput.getText().toString();

                DiningSelect.push_reference().child("Price").setValue(price);
                DiningSelect.push_reference().child("Number").setValue(numPerson);

                startActivity(new Intent(BuyerPrice.this, BuyerWait.class));
            }
        });


        Toast.makeText(BuyerPrice.this, DiningSelect.push_reference().toString(), Toast.LENGTH_LONG).show();
    }



}

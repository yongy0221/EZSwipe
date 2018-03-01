package com.gazua.ezswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private Button mFirebaseBtn;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_price);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner_num);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(BuyerPrice.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.numbers));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mFirebaseBtn = (Button) findViewById(R.id.buyer_btn);

        mDatabase = FirebaseDatabase.getInstance().getReference();
      
      findViewById(R.id.buyerTimeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                startActivity(new Intent(BuyerPrice.this, BuyerTime.class));
                Toast.makeText(BuyerPrice.this, "Buyer Time.", Toast.LENGTH_LONG).show();

            }
        });

        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String name = currentFirebaseUser.getDisplayName();
                String uid = currentFirebaseUser.getUid();
                Date currentTime = Calendar.getInstance().getTime();

                String key = mDatabase.getKey();

                DatabaseReference reference;


                SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                String strDt = simpleDate.format(currentTime);

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Location", "Covel");
                map.put("Price", "8");
                map.put("Number", "1");
                map.put("Status", "0");
                map.put("Buyer_ID", uid);
                map.put("Buyer_name", name);
                map.put("Seller_name", "");
                map.put("Created_at", strDt);
                map.put("Key", key);

                mDatabase.push().setValue(map);
//                reference = mDatabase.push();
//                reference.setValue(map);
//
//                String st = reference.toString();
//                Toast.makeText(BuyerPrice.this, st, Toast.LENGTH_LONG).show();

                mDatabase.child("Location").setValue("Covel");
                mDatabase.child("Price").setValue("8");
                mDatabase.child("Number").setValue("1");
                mDatabase.child("Status").setValue("0");
                mDatabase.child("Buyer_ID").setValue("1");
                mDatabase.child("Buyer_name").setValue("Bruin");
                mDatabase.child("Seller_name").setValue("Bear");
                mDatabase.child("Created_at").setValue("10");
            }
        });
    }
}

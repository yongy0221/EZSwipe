package com.gazua.ezswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DiningSelect extends AppCompatActivity {

//    final FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference ref = database.getReference("https://ezswipe-1db4e.firebaseio.com/");

    private TextView MessageText;
    private static DatabaseReference reference;
    private Button mFirebaseBtn;
    private ImageButton deNeve;
    private ImageButton bplate;
    private ImageButton covel;
    private ImageButton feast;
    private DatabaseReference mDatabase;
    private static String diningHall;
    private Button mSellerBtn;

    //    for list test
    private Button list_btn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_select);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signOutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(DiningSelect.this, MainActivity.class));
                Toast.makeText(DiningSelect.this, "Sign Out.", Toast.LENGTH_LONG).show();
            }
        });

        mFirebaseBtn = (Button) findViewById(R.id.buyer_btn);
        mSellerBtn = (Button) findViewById(R.id.seller_btn);
        bplate = (ImageButton) findViewById(R.id.bplate_btn);
        covel = (ImageButton) findViewById(R.id.covel_btn);
        deNeve = (ImageButton) findViewById(R.id.deNeve_btn);
        feast = (ImageButton) findViewById(R.id.feast_btn);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                final String name = currentFirebaseUser.getDisplayName();
                final String uid = currentFirebaseUser.getUid();
                final Date currentTime = Calendar.getInstance().getTime();
//                Boolean switchState = simpleSwitch.isChecked();
//                Toast.makeText(DiningSelect.this, switchState.toString(), Toast.LENGTH_LONG).show();
                SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String strDt = simpleDate.format(currentTime);
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Location", diningHall);
                map.put("Price", "");
                map.put("Number", "");
                map.put("Status", "0");
                map.put("Buyer_ID", uid);
                map.put("Buyer_name", name);
                map.put("Seller_name", "");
                map.put("Created_at", strDt);
                map.put("RID","");
                reference = mDatabase.push();
                reference.setValue(map);
                reference.child("RID").setValue(push_reference().toString());
                startActivity(new Intent(DiningSelect.this, BuyerPrice.class));
                Toast.makeText(DiningSelect.this, "Buyer Price.", Toast.LENGTH_LONG).show();
            }
        });

        mSellerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiningSelect.this, SellerList.class));
                Toast.makeText(DiningSelect.this, "Seller List", Toast.LENGTH_LONG).show();
            }

        });


        bplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diningHall = "Bruin Plate";
            }
        });

        covel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diningHall = "Covel";
            }
        });

        deNeve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diningHall = "De Neve";
            }
        });

        feast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diningHall = "Feast";
            }
        });

    }

        @Override
        protected void onStart () {
            super.onStart();

            if (mAuth.getCurrentUser() == null) {
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }

//        ref.child("Test").setValue("Test!!");
        }


    public static DatabaseReference push_reference() {
        return reference;
    }

    public static String dining_hall() {
        return diningHall;
    }
}

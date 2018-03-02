package com.gazua.ezswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SellerList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_list);

        final TextView textView = (TextView) findViewById(R.id.textView);

        final List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    String dining_str = (String) child.child("Location").getValue();
                    if(Objects.equals(dining_str, DiningSelect.dining_hall())) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("RID", (String) child.child("RID").getValue());
                        map.put("Buyer_ID", (String) child.child("Buyer_ID").getValue());
                        map.put("Buyer_name", (String) child.child("Buyer_name").getValue());
                        map.put("Created_at", (String) child.child("Created_at").getValue());
                        map.put("Location", (String) child.child("Location").getValue());
                        map.put("Number", (String) child.child("Number").getValue());
                        map.put("Price", (String) child.child("Price").getValue());
                        map.put("Seller_name", (String) child.child("Seller_name").getValue());
                        map.put("Status", (String) child.child("Status").getValue());
                        map.put("Buyer_ID", (String) child.child("Buyer_ID").getValue());

                        list.add(map);
                    }
                }

                textView.setText(list.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SellerList.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

    }



}

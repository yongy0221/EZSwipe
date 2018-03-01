package com.gazua.ezswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BuyerWait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_wait);
        Toast.makeText(BuyerWait.this, BuyerPrice.push_reference().toString(), Toast.LENGTH_LONG).show();
    }
}

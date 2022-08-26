package com.anku.turban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BuyNow extends AppCompatActivity {


    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);

        payButton = findViewById(R.id.payNow);

        payButtonNow();


    }

    private void payButtonNow(){

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyNow.this, MainActivity.class));
                Toast.makeText(BuyNow.this, "Order placed Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
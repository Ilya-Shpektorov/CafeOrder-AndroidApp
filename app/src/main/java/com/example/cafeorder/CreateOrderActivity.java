package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {
    TextView wlcmPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        intent.getStringExtra("password");

        wlcmPersonal = findViewById(R.id.textViewWlcmPersonal);
        wlcmPersonal.setText(String.format("%s, %s", getString(R.string.wlcm_personal), name));

    }
}

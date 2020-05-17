package com.example.cafeorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {
    TextView orderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent intent = getIntent();
        orderTextView = findViewById(R.id.orderResultTextView);
        if (intent.hasExtra("order")) {
            String orderText = intent.getStringExtra("order");
            orderTextView.setText(orderText);
        } else {
            Intent backToLogin = new Intent(this, LoginActivity.class);
            startActivity(backToLogin);
        }
    }
}

package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {
    TextView wlcmPersonal;
    TextView checkTopping;
    RadioGroup drinksGroupRadio;
    RadioButton drinkChoosenRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        intent.getStringExtra("password");

        wlcmPersonal = findViewById(R.id.textViewWlcmPersonal);
        wlcmPersonal.setText(String.format("%s", getString(R.string.wlcm_personal)).replace("клиент", name));
        drinksGroupRadio = findViewById(R.id.radioGroupDrinks);
        drinksGroupRadio.check(R.id.radioButtonTea);
        drinkChoosenRadio = findViewById(drinksGroupRadio.getCheckedRadioButtonId());

        checkTopping = findViewById(R.id.textViewCheckTopping);
        checkTopping.setText(String.format(getString(R.string.check_topping)).replace("напиток", drinkChoosenRadio.getText()));
    }
}

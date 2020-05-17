package com.example.cafeorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateOrderActivity extends AppCompatActivity {
    private TextView textViewWlcmPersonal;
    private TextView textViewCheckTopping;
    private RadioGroup drinksGroupRadio;
    private RadioButton drinkChoosenRadio;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private String name;
    private String password;
    private String choosenDrink;
    private StringBuilder builderTopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        textViewWlcmPersonal = findViewById(R.id.textViewWlcmPersonal);
        String wlcmText = String.format(getString(R.string.wlcm_personal), name);
        textViewWlcmPersonal.setText(wlcmText);
        drinksGroupRadio = findViewById(R.id.radioGroupDrinks);
        drinkChoosenRadio = findViewById(drinksGroupRadio.getCheckedRadioButtonId());
        textViewCheckTopping = findViewById(R.id.textViewCheckTopping);
        choosenDrink = String.format(drinkChoosenRadio.getText().toString()).toLowerCase();
        String checkToppingText = String.format(getString(R.string.check_topping), choosenDrink);
        textViewCheckTopping.setText(checkToppingText);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        spinnerTea = findViewById(R.id.spinnerForTea);
        spinnerCoffee = findViewById(R.id.spinnerForCoffee);
        builderTopping = new StringBuilder();

    }

    public void onClickChangeDrink(View view) {
        drinkChoosenRadio = findViewById(drinksGroupRadio.getCheckedRadioButtonId());
        choosenDrink = String.format(drinkChoosenRadio.getText().toString()).toLowerCase();
        String checkToppingText = String.format(getString(R.string.check_topping), choosenDrink);
        textViewCheckTopping.setText(checkToppingText);

        if (drinkChoosenRadio.getId() == R.id.radioButtonTea) {
            checkBoxLemon.setVisibility(View.VISIBLE);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);

        } else if (drinkChoosenRadio.getId() == R.id.radioButtonCoffee) {
            checkBoxLemon.setVisibility(View.INVISIBLE);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
        }
    }

    public void onClickSendOrder(View view) {
        builderTopping.setLength(0);
        if (checkBoxMilk.isChecked()) {
            builderTopping.append(getString(R.string.milk)).append(" ");
        }
        if (checkBoxSugar.isChecked()) {
            builderTopping.append(getString(R.string.sugar)).append(" ");
        }
        if (checkBoxLemon.isChecked() && drinkChoosenRadio.getId() == R.id.radioButtonTea) {
            builderTopping.append(getString(R.string.lemon));
        }
        String drinkType = "";
        if (choosenDrink.equals(getString(R.string.tea))) {
            drinkType = spinnerTea.getSelectedItem().toString();
        } else if (choosenDrink.equals(getString(R.string.coffee))) {
            drinkType = spinnerCoffee.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order), name, password, choosenDrink, drinkType);
        String toppings;
        if (builderTopping.length() > 0) {
            toppings = getString(R.string.need_additions) + builderTopping.toString();
        } else {
            toppings = "";
        }
        String fullOrder = order + toppings;

        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);
    }
}

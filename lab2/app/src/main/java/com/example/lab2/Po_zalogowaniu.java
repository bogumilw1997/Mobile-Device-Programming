package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Po_zalogowaniu extends AppCompatActivity {

    TextView tekst;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_zalogowaniu);

        sharedPref = getSharedPreferences("SP1",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        tekst = findViewById(R.id.po_witaj);
        tekst.setText("Witaj, " + sharedPref.getString("last_login", ""));
    }




}
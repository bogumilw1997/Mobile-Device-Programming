package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Rejestracja extends AppCompatActivity {
    Context context;
    EditText rej_login, rej_haslo, rej_haslo2;
    Button zarejestruj;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        context = getApplicationContext();
        rej_login = findViewById(R.id.rej_login);
        rej_haslo = findViewById(R.id.rej_haslo);
        rej_haslo2 = findViewById(R.id.rej_haslo2);
        zarejestruj = findViewById(R.id.rej_zarejestruj);
        sharedPref = getSharedPreferences("SP1",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        rej_haslo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = rej_haslo.getText().length();
                if (length <= 7) {
                    rej_haslo.setTextColor(Color.RED);
                }
                else rej_haslo.setTextColor(Color.GREEN);

                if (rej_haslo2.getText().toString().equals(rej_haslo.getText().toString())) {
                    rej_haslo2.setTextColor(Color.GREEN);
                }
                else rej_haslo2.setTextColor(Color.RED);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rej_haslo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (rej_haslo2.getText().toString().equals(rej_haslo.getText().toString())) {
                    rej_haslo2.setTextColor(Color.GREEN);
                }
                else rej_haslo2.setTextColor(Color.RED);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        zarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rej_login.getText().length() > 0) {
                    if (rej_haslo.getText().length() > 7) {
                        if (rej_haslo2.getText().toString().equals(rej_haslo.getText().toString())) {
                            editor.putString(rej_login.getText().toString(), rej_haslo.getText().toString());
                            editor.commit();
                            finish();
                        }
                    }
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.autor:
                //Log.d("menu", "autor");
                Toast.makeText(this,"Bogumił Wierzchowski", Toast.LENGTH_LONG).show();
                break;
            case R.id.rejestracja:
                break;
        }
        return true;
    }
}
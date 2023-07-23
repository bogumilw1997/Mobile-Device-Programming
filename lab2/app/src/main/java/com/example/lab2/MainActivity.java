package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPref;
    EditText login, haslo;
    Button zaloguj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        login = findViewById(R.id.login);
        haslo = findViewById(R.id.haslo);
        zaloguj = findViewById(R.id.zaloguj);
        sharedPref = getSharedPreferences("SP1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        zaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = sharedPref.getString(login.getText().toString(), "");
                if (!s1.equals("")) {
                    if (s1.equals(haslo.getText().toString())) {
                        Intent logowanie = new Intent(context, Po_zalogowaniu.class);
                        editor.putString("last_login", login.getText().toString());
                        editor.commit();
                        startActivity(logowanie);
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.autor:
                Log.d("menu", "autor");
                Toast.makeText(this,"Bogumił Wierzchowski", Toast.LENGTH_LONG).show();
                break;
            case R.id.rejestracja:
                Intent rejestracja = new Intent(context, Rejestracja.class);
                rejestracja.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //Intent rejestracja = new Intent(context, Rejestracja.class);
                startActivity(rejestracja);
                break;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
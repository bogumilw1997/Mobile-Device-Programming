package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Zapytanie extends AppCompatActivity {
    Context context;
    TextView data_zapytanie;
    Button zapytnie_button;
    RunnableHelper runnableHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapytanie);
        context = getApplicationContext();
        data_zapytanie = findViewById(R.id.data_zapytanie);
        runnableHelper = new RunnableHelper();
        zapytnie_button = findViewById(R.id.zapytanie_button);

        data_zapytanie.setText("Wynik zapytania");

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = connMgr.getActiveNetwork();

        if(network != null) {

            NetworkCapabilities caps = connMgr.getNetworkCapabilities(network);
            if (caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                zapytnie_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread thread = new Thread(runnableHelper);
                        thread.start();
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }
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
            case R.id.menu_baza:
                break;
            case R.id.menu_zapytanie:
                Intent zapytanie_activity = new Intent(context, Zapytanie.class);
                zapytanie_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(zapytanie_activity);
                break;
            case R.id.menu_film:
                Intent film_activity = new Intent(context, Film.class);
                film_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(film_activity);
                break;
            case R.id.menu_glowna:
                Intent main_activity = new Intent(context, MainActivity.class);
                main_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(main_activity);
                break;
        }
        return true;
    }

    class RunnableHelper implements Runnable {

        public void run() {
            Log.d("zapytanie", "przygotowany");
            String serverUrl = "https://if.pw.edu.pl/~pszymanski/PUM23/send_data.php?max=100";
            URL url = null;
            try {
                url = new URL(serverUrl);
            } catch (MalformedURLException e) {
                Log.d("MalformedURLException", ""+e);
                throw new RuntimeException(e);
            }
            HttpsURLConnection urlConnection =
                    null;
            try {
                urlConnection = (HttpsURLConnection) url.openConnection();
            } catch (IOException e) {
                Log.d("IOException", ""+e);
                throw new RuntimeException(e);
            }
            try {
                urlConnection.connect();
            } catch (IOException e) {
                Log.d("IOException", ""+e);
                throw new RuntimeException(e);
            }

            int responseCode = 0;
            try {
                responseCode = urlConnection.getResponseCode();
            } catch (IOException e) {
                Log.d("IOException", ""+e);
                throw new RuntimeException(e);
            }
            BufferedReader reader = null;
            StringBuilder response = new StringBuilder();
            if(responseCode == HttpsURLConnection.HTTP_OK) {
                InputStream in = null;
                try {
                    in = urlConnection.getInputStream();
                } catch (IOException e) {
                    Log.d("IOException", ""+e);
                    throw new RuntimeException(e);
                }
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while (true) {
                    Log.d("while", "przygotowany");
                    try {
                        if (!((line = reader.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    response.append(line);
                }
                UIthread uithread = new UIthread(response.toString());
                runOnUiThread(uithread);
            }
        }
    }

    class UIthread implements Runnable {

        private String resonse;

        UIthread(String res){
            this.resonse = res;
        }

        @Override
        public void run() {

            data_zapytanie.setText(resonse);
        }
    }
}
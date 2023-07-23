package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
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
import android.widget.MediaController;
import android.widget.VideoView;

public class Film extends AppCompatActivity {
    Context context;
    MediaController mediaController;
    VideoView vview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        context = getApplicationContext();
        vview = findViewById(R.id.videoView);

        mediaController = new MediaController(context);

        vview.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        vview.setMediaController(mediaController);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = connMgr.getActiveNetwork();

        if(network != null) {

            NetworkCapabilities caps = connMgr.getNetworkCapabilities(network);
            if (caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {

                Log.d("mam", "polaczenie");

                vview.setVideoURI(Uri.parse("http://techslides.com/demos/sample-videos/small.mp4"));
//                vview.setOnPreparedListener(mp -> {
//                    Log.d("film", "przygotowany");
//                    mp.start();
//                });
                vview.setOnPreparedListener(
                        new MediaPlayer.OnPreparedListener() {
                            public void onPrepared(MediaPlayer mp) {
                                Log.d("film", "przygotowany");
                                mp.start();
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
}
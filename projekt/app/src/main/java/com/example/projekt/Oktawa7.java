package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Oktawa7 extends AppCompatActivity {

    Button button_a;
    Button button_b;
    Button button_c;
    Button button_d;
    Button button_e;
    Button button_f;
    Button button_g;
    Button button_c2;
    Menu menuu;
    SoundPool sP;
    Context context;
    int sound_a = 0;
    int sound_b = 0;
    int sound_c = 0;
    int sound_d = 0;
    int sound_e = 0;
    int sound_f = 0;
    int sound_g = 0;
    int sound_c2 = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menuu = menu;
        menu.getItem(6).setChecked(true);
        return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.oktawa1:
                Intent oktawa1_activity = new Intent(context, MainActivity.class);
                oktawa1_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(oktawa1_activity);
                break;
            case R.id.oktawa2:
                Intent oktawa2_activity = new Intent(context, Oktawa2.class);
                oktawa2_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(oktawa2_activity);
                break;
            case R.id.oktawa3:
                Intent oktawa3_activity = new Intent(context, Oktawa3.class);
                oktawa3_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(oktawa3_activity);
                break;
            case R.id.oktawa4:
                Intent oktawa4_activity = new Intent(context, Oktawa4.class);
                oktawa4_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(oktawa4_activity);
                break;
            case R.id.oktawa5:
                Intent oktawa5_activity = new Intent(context, Oktawa5.class);
                oktawa5_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(oktawa5_activity);
                break;
            case R.id.oktawa6:
                Intent oktawa6_activity = new Intent(context, Oktawa6.class);
                oktawa6_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(oktawa6_activity);
                break;
            case R.id.oktawa7:
                break;
            case R.id.start_rec:
                menuu.getItem(7).setChecked(true);
                break;
            case R.id.stop_rec:
                menuu.getItem(8).setChecked(true);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oktawa7);

        context = getApplicationContext();

        button_a = findViewById(R.id.button_a);
        button_b = findViewById(R.id.button_b);
        button_c = findViewById(R.id.button_c);
        button_d = findViewById(R.id.button_d);
        button_e = findViewById(R.id.button_e);
        button_f = findViewById(R.id.button_f);
        button_g = findViewById(R.id.button_g);
        button_c2 = findViewById(R.id.button_c2);

        sP = new SoundPool(6, AudioManager.STREAM_MUSIC,0);
        sound_a = sP.load(this, R.raw.a7, 1);
        sound_b = sP.load(this, R.raw.b7, 1);
        sound_c = sP.load(this, R.raw.c7, 1);
        sound_d = sP.load(this, R.raw.d7, 1);
        sound_e = sP.load(this, R.raw.e7, 1);
        sound_f = sP.load(this, R.raw.f7, 1);
        sound_g = sP.load(this, R.raw.g7, 1);
        sound_c2 = sP.load(this, R.raw.c8, 1);

        button_a.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sP.play(sound_a, 1, 1, 0, 0, 1);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        button_b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sP.play(sound_b, 1, 1, 0, 0, 1);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        button_c.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sP.play(sound_c, 1, 1, 0, 0, 1);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        button_d.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sP.play(sound_d, 1, 1, 0, 0, 1);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        button_e.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sP.play(sound_e, 1, 1, 0, 0, 1);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        button_f.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sP.play(sound_f, 1, 1, 0, 0, 1);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        button_g.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sP.play(sound_g, 1, 1, 0, 0, 1);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        button_c2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sP.play(sound_c2, 1, 1, 0, 0, 1);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }
}
package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;
    EditText nazwa, cena, data;
    Button wyslij;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        nazwa = findViewById(R.id.edit_nazwa);
        cena = findViewById(R.id.edit_cena);
        data = findViewById(R.id.edit_data);

        wyslij = findViewById(R.id.button_wyslij);
        dbHelper = new DbHelper(context);
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

    public final class DataBaseInfo {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private DataBaseInfo() {}

        /* Inner class that defines the table contents */
        public class Column_names implements BaseColumns {
            public static final String TABLE_NAME = "towary";
            public static final String COLUMN_NAME1= "towar";
            public static final String COLUMN_NAME2= "cena";
            public static final String COLUMN_NAME3= "data";

        }
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataBaseInfo.Column_names.TABLE_NAME + " (" +
                    DataBaseInfo.Column_names._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DataBaseInfo.Column_names.COLUMN_NAME1 + " TEXT," +
                    DataBaseInfo.Column_names.COLUMN_NAME2 + " REAL," +
                    DataBaseInfo.Column_names.COLUMN_NAME3 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseInfo.Column_names.TABLE_NAME;

    public class DbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Towary.db";

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
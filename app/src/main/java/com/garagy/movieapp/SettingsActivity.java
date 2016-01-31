package com.garagy.movieapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner sp = (Spinner) findViewById(R.id.Sort_method);
        List<String> sortMethod = new ArrayList<>(Arrays.asList(new String[]{"select Your Display Option", "Popular Movies", "Top Rated", "Favorites"}));
        ArrayAdapter adapter = new ArrayAdapter(
                SettingsActivity.this,
                R.layout.list_item_layout,
                R.id.list_item_id,
                sortMethod
        );
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                Intent intent;
                switch (position) {
                    case 1:
                        editor.putInt(getString(R.string.saved_mode), 0);
                        intent = new Intent(SettingsActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        editor.putInt(getString(R.string.saved_mode), 1);
                        intent = new Intent(SettingsActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        editor.putInt(getString(R.string.saved_mode), 2);
                        intent = new Intent(SettingsActivity.this, FavouriteActivity.class);
                        startActivity(intent);
                        break;
                }

                editor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}

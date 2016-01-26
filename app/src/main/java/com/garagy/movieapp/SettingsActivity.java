package com.garagy.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        Spinner sp = (Spinner) findViewById(R.id.sp_city);
        List<String> cityList = new ArrayList<String>(Arrays.asList(new String[]{"Cairo", "Alexandria", "NewYork", "Boston"}));
        ArrayAdapter adapter = new ArrayAdapter(
                SettingsActivity.this,
                R.layout.list_item_layout,
                R.id.list_item_id,
                cityList
        );
        sp.setAdapter(adapter);


    }

}

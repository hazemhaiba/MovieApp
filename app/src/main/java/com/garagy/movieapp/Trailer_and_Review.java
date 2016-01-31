package com.garagy.movieapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.garagy.movieapp.data.reviews.Review;
import com.garagy.movieapp.data.videos.Videos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trailer_and_Review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_and__review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String trailer = intent.getStringExtra("trailer");
        String reviews = intent.getStringExtra("reviews");
        GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        final Videos videos_info = gson.fromJson(trailer, Videos.class);
        final Review review_info = gson.fromJson(reviews, Review.class);
        Log.e("Review_data", reviews);
        Log.e("trailer info", trailer);
        String trailer_info[] = new String[videos_info.getResults().size() + review_info.getResults().size()];
        Log.e("array size", "" + trailer_info.length);

        for (int i = 0; i < videos_info.getResults().size(); i++) {
            trailer_info[i] = videos_info.getResults().get(i).getName();
        }
        for (int i = 0; i < review_info.getResults().size(); i++) {
            trailer_info[i + videos_info.getResults().size()] = review_info.getResults().get(i).getContent();
        }

        ListView trailer_list_view = (ListView) findViewById(R.id.trailer_list_view);
        List<String> stringList = new ArrayList<String>(Arrays.asList(trailer_info));
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                Trailer_and_Review.this,
                R.layout.list_item_layout,
                R.id.list_item_id,
                stringList
        );
        trailer_list_view.setAdapter(stringArrayAdapter);
        trailer_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < videos_info.getResults().size()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(Important_Strings.youtube_base_url + videos_info.getResults().get(position).getKey()));
                    startActivity(intent);
                }
            }
        });


    }

}

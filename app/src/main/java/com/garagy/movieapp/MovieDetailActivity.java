package com.garagy.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.garagy.movieapp.data.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String json = intent.getStringExtra("movie");
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Result movie = gson.fromJson(json, Result.class);

        TextView movie_name_textview = (TextView) findViewById(R.id.moviename_textbox);// title
        movie_name_textview.setText(movie.getTitle());

        ImageView imageView = (ImageView) findViewById(R.id.backdrop_imageview);
        Picasso.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w500/" + movie.getBackdrop_path()).into(imageView);

        TextView overview_textview = (TextView) findViewById(R.id.overview_textbox);
        overview_textview.setText(movie.getOverview());


    }

}

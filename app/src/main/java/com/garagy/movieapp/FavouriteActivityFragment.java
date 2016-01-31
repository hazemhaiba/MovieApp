package com.garagy.movieapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.garagy.movieapp.adapter.ImageAdapter;
import com.garagy.movieapp.data.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavouriteActivityFragment extends Fragment {

    public FavouriteActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_favourite, container, false);
        final SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String favouties_String = sharedPref.getString("favourite", "");
        GridView gridView = (GridView) fragmentView.findViewById(R.id.gridView_favourite);
        if (!favouties_String.equals("")) {
            final String temp[] = favouties_String.split("_");
            String urls[] = new String[temp.length];
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            for (int i = 0; i < temp.length; i++) {
                String json = sharedPref.getString(temp[i], "");
                Result movie = gson.fromJson(json, Result.class);
                urls[i] = "http://image.tmdb.org/t/p/w185/" + movie.getPoster_path();
            }
            ImageAdapter imageAdapter = new ImageAdapter(getContext(), urls);
            gridView.setAdapter(imageAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                    String json = sharedPref.getString(temp[position], "");
                    if (!json.equals("")) {
                        intent.putExtra("movie", json);
                        startActivity(intent);
                    }

                }
            });

        }


        return fragmentView;
    }


}

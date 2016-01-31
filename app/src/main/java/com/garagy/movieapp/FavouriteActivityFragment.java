package com.garagy.movieapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

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
        GridView gridView = (GridView) fragmentView.findViewById(R.id.gridView_favourite);
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String favouties_String = sharedPref.getString("favourite", "");
        if (favouties_String != "") {
            String temp[] = favouties_String.split("_");
            String urls[] = new String[temp.length];
            for (int i = 0; i < temp.length; i++) {
                urls[i] = "http://image.tmdb.org/t/p/w185/" + newpage.getResults().get(i).getPoster_path();
            }
        } else {
            TextView textView = new TextView(getActivity());
            textView.setText("No Favourite Selected");
            gridView.addView(textView);

        }


        return fragmentView;
    }
}

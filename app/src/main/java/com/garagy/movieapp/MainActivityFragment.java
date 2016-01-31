package com.garagy.movieapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.garagy.movieapp.adapter.ImageAdapter;
import com.garagy.movieapp.data.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    int page = 1;
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setHasOptionsMenu(true);
        Log.e("App Created", "App Created");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        getdata(page);
//        Picasso
        //hazem
        FloatingActionButton fab_next = (FloatingActionButton) fragment.findViewById(R.id.fab_next);
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata(page++);
            }
        });
        FloatingActionButton fab_prev = (FloatingActionButton) fragment.findViewById(R.id.fab_previous);
        fab_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page - 1 > 0) {
                    getdata(page--);
                }
            }
        });


        return fragment;
    }

    public String getdata(final int page) {
        final String[] data = {""};
        Thread xx = new Thread(new Runnable() {
            @Override
            public void run() {

                // These two need to be declared outside the try/catch
// so that they can be closed in the finally block.
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;

// Will contain the raw JSON response as a string.
                String movieJSON = null;

                try {

                    URL url = new URL(getUrl());

                    // Create the request to OpenWeatherMap, and open the connection
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    // Read the input stream into a String
                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    if (inputStream == null) {
                        // Nothing to do.
                        movieJSON = null;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                        // But it does make debugging a *lot* easier if you print out the completed
                        // buffer for debugging.
                        buffer.append(line + "\n");
                    }

                    if (buffer.length() == 0) {
                        // Stream was empty.  No point in parsing.
                        movieJSON = null;
                    }
                    movieJSON = buffer.toString();
                } catch (IOException e) {
                    Log.e("PlaceholderFragment", "Error ", e);
                    // If the code didn't successfully get the Movie data, there's no point in attemping
                    // to parse it.
                    movieJSON = null;
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (final IOException e) {
                            Log.e("PlaceholderFragment", "Error closing stream", e);
                        }
                    }
                }
                if (movieJSON != null) {
                    data[0] = movieJSON;
                    final String finalMovieJSON = movieJSON;

                    getActivity().runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    GsonBuilder builder = new GsonBuilder();
                                    final Gson gson = builder.create();
                                    final Page newpage = gson.fromJson(finalMovieJSON, Page.class);
//                            ImageView imageView= (ImageView) getActivity().findViewById(R.id.imageView);
//                            Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/"+newpage.getResults().get(1).getPoster_path()).into(imageView);
//                            Log.e("picture loaded", "picture loaded-");
                                    GridView gridView = (GridView) getActivity().findViewById(R.id.gridView);
                                    String urls[] = new String[newpage.getResults().size()];
                                    System.out.println(finalMovieJSON);
                                    System.out.println("size of the array is " + newpage.getResults().size() + finalMovieJSON);
                                    for (int i = 0; i < newpage.getResults().size(); i++) {
                                        urls[i] = "http://image.tmdb.org/t/p/w185/" + newpage.getResults().get(i).getPoster_path();
                                    }
                                    ImageAdapter imageViewArrayAdapter = new ImageAdapter(getContext(), urls);
                                    gridView.setAdapter(imageViewArrayAdapter);
                                    gridView.setClickable(true);
                                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent = new Intent(getActivity(), DetailMovie.class);
                                            String jsonextra = gson.toJson(newpage.getResults().get(position));
                                            // Toast.makeText(getActivity(),jsonextra,Toast.LENGTH_LONG).show();
                                            intent.putExtra("movie", jsonextra);
//                                            intent.putExtra("hello","hello");
                                            startActivity(intent);
                                        }
                                    });
                                    gridView.forceLayout();

                                }
                            });
                } else {
                    Log.e("String is empty", "string is empty");
                }
            }

        });
        xx.start();
        if (data[0] != null) {
            return data[0];
        } else {
            return "String is empty and i've no clue why :/";
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            getdata(page);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        Log.e("App Paused", "App Paused");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.e("App Resume", "App Resume");
        super.onResume();
        getdata(page);
    }

    @Override
    public void onStop() {
        Log.e("App Stopped", "App Stopped");
        super.onStop();
    }

    @Override
    public void onStart() {
        Log.e("App Started", "App Started");
        super.onStart();
    }

    @Override
    public void onDestroy() {
        Log.e("App Destroyed", "App Destroyed");
        super.onDestroy();
    }

    public String getUrl() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        Log.e("Shared Pref in Fragment", "" + sharedPref.getInt(getString(R.string.saved_mode), -1));
        int mode = sharedPref.getInt(getString(R.string.saved_mode), 0);
        String url = Important_Strings.Base_url;
        switch (mode) {
            case 0:
                url = url + Important_Strings.Movie_popular;
                break;
            case 1:
                url = url + Important_Strings.Movie_High_rated;
                break;
            default:
                url = url + Important_Strings.Movie_popular;
                break;

        }
        Log.e("Url:", url);
        return url + "?api_key=" + Important_Strings.App_key + "&page=" + page;
    }
}

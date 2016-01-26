package com.garagy.movieapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        getdata(1);
//        Picasso
        //hazem


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
                    // Construct the URL for the OpenWeatherMap query
                    String Url_string = Important_Strings.Base_url + Important_Strings.Movie_popular + "?api_key=" + Important_Strings.App_key + "&page=" + page;
                    URL url = new URL(Url_string);

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
                                    Gson gson = builder.create();
                                    Page newpage = gson.fromJson(finalMovieJSON, Page.class);
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


}

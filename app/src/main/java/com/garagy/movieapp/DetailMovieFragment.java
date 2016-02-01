package com.garagy.movieapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.garagy.movieapp.data.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailMovieFragment extends Fragment {

    Result movie;
    String Trailers;
    String Reviews;
    boolean isFavoutite;

    public DetailMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        final Intent intent = getActivity().getIntent();
        final String json;
        if (intent.hasExtra("movie")) {
            json = intent.getStringExtra("movie");
        } else {
            final SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPref.edit();
            json = sharedPref.getString("movie", "");

        }
        if (json.equals("")) {
            return view;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        movie = gson.fromJson(json, Result.class);
        getdata();
        final TextView movie_name_textview = (TextView) view.findViewById(R.id.moviename_textbox);// title
        movie_name_textview.setText(movie.getTitle());

        ImageView imageView = (ImageView) view.findViewById(R.id.backdrop_imageview);
        Picasso.with(getActivity().getApplicationContext()).load("http://image.tmdb.org/t/p/w500/" + movie.getBackdrop_path()).into(imageView);

        TextView overview_textview = (TextView) view.findViewById(R.id.overview_tv);
        overview_textview.setText(movie.getOverview());

        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBar.setRating((float) movie.getVote_average());

        TextView release_date = (TextView) view.findViewById(R.id.release_date_tv);
        release_date.setText(getString(R.string.Release_date) + movie.getRelease_date());

        Button trailButton = (Button) view.findViewById(R.id.trailer_and_review_btn);
        trailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Trailer_and_Review.class);
                intent1.putExtra("trailer", Trailers);
                intent1.putExtra("reviews", Reviews);
                startActivity(intent1);
            }
        });

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        String favouties_String = sharedPref.getString("favourite", "");
        final ImageButton imageButton = (ImageButton) view.findViewById(R.id.action_favourite);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fav_temp = movie.getId() + "_";
                if (isFavoutite) {
                    if (sharedPref.getString("favourite", "").contains("" + movie.getId())) {
                        String new_fav = sharedPref.getString("favourite", "").replace(fav_temp, "");
                        editor.putString("favourite", new_fav);
                        editor.remove("" + movie.getId());
                    }
                    isFavoutite = false;
                    imageButton.setImageResource(android.support.design.R.drawable.abc_btn_rating_star_off_mtrl_alpha);

                } else {
                    String new_fav = sharedPref.getString("favourite", "").concat(fav_temp);
                    editor.putString("favourite", new_fav);
                    isFavoutite = true;
                    editor.putString("" + movie.getId(), json);
                    imageButton.setImageResource(android.support.design.R.drawable.abc_btn_rating_star_on_mtrl_alpha);

                }
                editor.commit();

            }
        });
        if (favouties_String.contains("" + movie.getId())) {
            isFavoutite = true;
            imageButton.setImageResource(android.support.design.R.drawable.abc_btn_rating_star_on_mtrl_alpha);
        } else {
            isFavoutite = false;
            imageButton.setImageResource(android.support.design.R.drawable.abc_btn_rating_star_off_mtrl_alpha);

        }


        return view;
    }

    public String getdata() {
        final String[] data = {""};
        Thread xx = new Thread(new Runnable() {
            @Override
            public void run() {

                // These two need to be declared outside the try/catch
// so that they can be closed in the finally block.
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;

// Will contain the raw JSON response as a string.
                String trailerInfo = null;
                String temp[] = new String[2];
                for (int i = 0; i < 2; i++) {

                    try {
                        URL url[] = new URL[2];
                        url[0] = new URL(Important_Strings.Base_url + "movie/" + movie.getId() + "/videos" + "?api_key=" + Important_Strings.App_key);
                        url[1] = new URL(Important_Strings.Base_url + "movie/" + movie.getId() + "/reviews" + "?api_key=" + Important_Strings.App_key);

                        urlConnection = (HttpURLConnection) url[i].openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.connect();

                        // Read the input stream into a String
                        InputStream inputStream = urlConnection.getInputStream();
                        StringBuffer buffer = new StringBuffer();
                        if (inputStream == null) {
                            // Nothing to do.
                            trailerInfo = null;
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
                            trailerInfo = null;
                        }
                        trailerInfo = buffer.toString();
                    } catch (IOException e) {
                        Log.e("PlaceholderFragment", "Error ", e);
                        // If the code didn't successfully get the Movie data, there's no point in attemping
                        // to parse it.
                        trailerInfo = null;
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
                    temp[i] = trailerInfo;

                }

                if (temp[0] != null || temp[1] != null) {
                    Trailers = temp[0];
                    Reviews = temp[1];


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

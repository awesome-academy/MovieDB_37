package com.example.moviedb_37.screen.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Movie;

import static com.example.moviedb_37.screen.home.HomeViewModel.BUNDLE_KEY;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String EXTRAS_ARGS = "com.example.moviedb_37.extras.EXTRAS_ARGS";

    public static Intent getIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, String.valueOf(movie.getId()));
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
    }
}

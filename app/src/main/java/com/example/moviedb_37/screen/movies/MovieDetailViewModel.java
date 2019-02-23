package com.example.moviedb_37.screen.movies;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.moviedb_37.data.model.Movie;

public class MovieDetailViewModel {
    private int mLoadBy;
    private String mKey;
    public final ObservableList<Movie> moviesObservable = new ObservableArrayList<>();

    public MovieDetailViewModel(int loadBy, String key) {
        mKey = key;
        mLoadBy = loadBy;
        loadMovies();
    }

    private void loadMovies() {
    }
}

package com.example.moviedb_37.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.moviedb_37.data.model.Movie;

public class ItemMovieListViewModel extends BaseObservable {

    public ObservableField<Movie> mMovieObservableField = new ObservableField<>();

    public void setMovie(Movie movie) {
        mMovieObservableField.set(movie);
    }
}

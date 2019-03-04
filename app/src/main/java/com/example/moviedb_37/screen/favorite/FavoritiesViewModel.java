package com.example.moviedb_37.screen.favorite;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;

public class FavoritiesViewModel extends BaseObservable {
    private MovieRepository mMovieRepository;

    public final ObservableList<Movie> favoriteMoviesObservable = new ObservableArrayList<>();

    public FavoritiesViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadFavoriteMovie();
    }

    private void loadFavoriteMovie() {
    }
}

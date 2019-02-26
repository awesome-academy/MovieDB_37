package com.example.moviedb_37.screen.moviedetails;

import android.databinding.ObservableField;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDetailsViewModel {
    public final ObservableField<Movie> movieObservable = new ObservableField<>();
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MovieDetailsViewModel(int movieId, MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadMovie(movieId);
    }

    private void loadMovie(int movieId) {
    }

    public Movie getMovie() {
        return movieObservable.get();
    }
}

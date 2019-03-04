package com.example.moviedb_37.screen.favorite;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;

import io.reactivex.disposables.CompositeDisposable;

public class FavoritiesViewModel extends BaseObservable {
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public final ObservableList<Movie> favoriteMoviesObservable = new ObservableArrayList<>();

    public FavoritiesViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadFavoriteMovie();
    }

    private void loadFavoriteMovie() {
        favoriteMoviesObservable.addAll(mMovieRepository.getFavoriteMovies());
    }

    public void refreshFavoriteMovies() {
        favoriteMoviesObservable.clear();
        favoriteMoviesObservable.addAll(mMovieRepository.getFavoriteMovies());
    }

    public boolean deleteFavoriteMovie(Movie movie) {
        boolean isSuccess = mMovieRepository.deleteFavoriteMovie(movie);
        if (isSuccess) {
            refreshFavoriteMovies();
        }
        return isSuccess;
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
    }
}

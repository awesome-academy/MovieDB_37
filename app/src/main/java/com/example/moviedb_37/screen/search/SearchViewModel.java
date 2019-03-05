package com.example.moviedb_37.screen.search;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.screen.search.SearchNavigator;

public class SearchViewModel extends BaseObservable {
    private MovieRepository mMovieRepository;

    public final ObservableList<Movie> searchedMoviesObservable = new ObservableArrayList<>();

    private SearchNavigator mSearchNavigator;

    public SearchViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        searchMovie();
    }

    public void setSearchNavigator(SearchNavigator searchNavigator) {
        mSearchNavigator = searchNavigator;
    }

    public void onFilterClick() {
        mSearchNavigator.showSearchFilter();
    }

    private void searchMovie() {
    }

    private void handleError(String message) {
    }
}

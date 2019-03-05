package com.example.moviedb_37.screen.search;

import com.example.moviedb_37.data.model.Movie;

public interface SearchNavigator {
    void showSearchFilter();

    void showMovieDetail(Movie movie);
}

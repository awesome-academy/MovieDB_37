package com.example.moviedb_37.screen.home;

import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.data.model.Movie;

public interface HomeNavigator {
    public void showMovies(Genre genre, int getBy);

    public void showMovieDetail(Movie movie);
}

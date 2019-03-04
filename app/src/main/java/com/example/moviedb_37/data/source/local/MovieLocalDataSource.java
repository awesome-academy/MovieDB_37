package com.example.moviedb_37.data.source.local;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.source.MovieDataSource;

import java.util.List;

public class MovieLocalDataSource implements MovieDataSource.Local {
    private static final String EXIST_TRACK = "Exist track in favorite";
    private static MovieLocalDataSource sInstance;
    private FavoriteReaderDbHelper mDbHelper;

    private MovieLocalDataSource(FavoriteReaderDbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    public static MovieLocalDataSource getInstance(FavoriteReaderDbHelper dbHelper) {
        if (sInstance == null) {
            sInstance = new MovieLocalDataSource(dbHelper);
        }
        return sInstance;
    }

    public List<Movie> getFavoriteMovies() {
        return mDbHelper.getMovie();
    }

    @Override
    public boolean addFavoriteMovie(Movie movie) {
        return mDbHelper.putMovie(movie);
    }

    @Override
    public boolean deleteFavoriteMovie(Movie movie) {
        return mDbHelper.deleteMovie(movie);
    }

    @Override
    public boolean canAddFavorite(Movie movie) {
        return mDbHelper.canAddMovie(movie);
    }

    @Override
    public boolean canAddFavorite(int movieId) {
        return mDbHelper.canAddMovie(movieId);
    }
}

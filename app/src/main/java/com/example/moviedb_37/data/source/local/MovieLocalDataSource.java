package com.example.moviedb_37.data.source.local;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.source.MovieDataSource;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

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

    @Override
    public Single<List<Movie>> getFavoriteMovies() {
        return new Single<List<Movie>>() {
            @Override
            protected void subscribeActual(SingleObserver<? super List<Movie>> observer) {
                mDbHelper.getMovie();
            }
        };
    }

    @Override
    public boolean addFavariteMovie(Movie movie) {
        return mDbHelper.putTrack(movie);
    }

    @Override
    public boolean deleteFavoriteMovie(Movie movie) {
        return mDbHelper.deleteMovie(movie);
    }

    @Override
    public boolean canAddFavarite(Movie movie) {
        return mDbHelper.canAddMovie(movie);
    }
}

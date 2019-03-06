package com.example.moviedb_37.data.repository;

import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.source.MovieDataSource;
import com.example.moviedb_37.data.source.local.MovieLocalDataSource;
import com.example.moviedb_37.data.source.remote.MovieRemoteDataSource;
import com.example.moviedb_37.data.source.remote.config.response.CategoryResult;

import java.util.List;

import io.reactivex.Single;

public class MovieRepository implements MovieDataSource.Local,
        MovieDataSource.Remote {

    private static MovieRepository sInstance;
    private MovieRemoteDataSource mMovieRemoteDataSource;
    private MovieLocalDataSource mMovieLocalDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource,
                            MovieLocalDataSource movieLocalDataSource) {
        mMovieRemoteDataSource = movieRemoteDataSource;
        mMovieLocalDataSource = movieLocalDataSource;
    }

    public static MovieRepository getInstance(MovieRemoteDataSource movieRemoteDataSource,
                                              MovieLocalDataSource movieLocalDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(movieRemoteDataSource, movieLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public Single<List<Movie>> getPopularMovies(int page) {
        return mMovieRemoteDataSource.getPopularMovies(page);
    }

    @Override
    public Single<List<Movie>> getNowPlayingMovies(int page) {
        return mMovieRemoteDataSource.getNowPlayingMovies(page);
    }

    @Override
    public Single<List<Movie>> getUpComingMovies(int page) {
        return mMovieRemoteDataSource.getUpComingMovies(page);
    }

    @Override
    public Single<List<Movie>> getTopRateMovies(int page) {
        return mMovieRemoteDataSource.getTopRateMovies(page);
    }

    @Override
    public Single<List<Genre>> getGenres() {
        return mMovieRemoteDataSource.getGenres();
    }

    @Override
    public Single<List<Movie>> getMoviesByGenre(int page, String genreId) {
        return mMovieRemoteDataSource.getMoviesByGenre(page, genreId);
    }

    @Override
    public Single<Movie> getMovieDetail(int movieId, String append) {
        return mMovieRemoteDataSource.getMovieDetail(movieId, append);
    }

    @Override
    public Single<List<Movie>> getMoviesByProduce(int page, String produceId) {
        return mMovieRemoteDataSource.getMoviesByProduce(page, produceId);
    }

    @Override
    public Single<List<Movie>> getMoviesByActor(int page, String actorId) {
        return mMovieRemoteDataSource.getMoviesByActor(page, actorId);
    }

    @Override
    public Single<CategoryResult> searchMovie(String type, String keyword, int page) {
        return mMovieRemoteDataSource.searchMovie(type, keyword, page);
    }

    @Override
    public List<Movie> getFavoriteMovies() {
        return mMovieLocalDataSource.getFavoriteMovies();
    }

    @Override
    public boolean addFavoriteMovie(Movie movie) {
        return mMovieLocalDataSource.addFavoriteMovie(movie);
    }

    @Override
    public boolean deleteFavoriteMovie(Movie movie) {
        return mMovieLocalDataSource.deleteFavoriteMovie(movie);
    }

    @Override
    public boolean canAddFavorite(Movie movie) {
        return mMovieLocalDataSource.canAddFavorite(movie);
    }

    @Override
    public boolean canAddFavorite(int movieId) {
        return mMovieLocalDataSource.canAddFavorite(movieId);
    }
}

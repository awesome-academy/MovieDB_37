package com.example.moviedb_37.data.source.remote;

import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.data.model.Movie;

import io.reactivex.functions.Function;

import com.example.moviedb_37.data.source.MovieDataSource;
import com.example.moviedb_37.data.source.remote.config.response.CategoryResult;
import com.example.moviedb_37.data.source.remote.config.response.GenreResult;
import com.example.moviedb_37.data.source.remote.config.service.NameApi;
import com.example.moviedb_37.data.source.remote.config.service.NameServiceClient;

import java.util.List;

import io.reactivex.Single;

public class MovieRemoteDataSource implements MovieDataSource.Remote {

    private static MovieRemoteDataSource sInstance;
    private NameApi mApi;

    public MovieRemoteDataSource(NameApi mNameApi) {
        this.mApi = mNameApi;
    }

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieRemoteDataSource(NameServiceClient.getInstance());
        }
        return sInstance;
    }

    public Single<List<Movie>> getPopularMovies(int page) {
        return mApi.getPopularMovies(page)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    public Single<List<Movie>> getNowPlayingMovies (int page) {
        return mApi.getNowPlayingMovies(page)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<List<Movie>> getUpComingMovies(int page) {
        return mApi.getUpComingMovies(page)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<List<Movie>> getTopRateMovies(int page) {
        return mApi.getTopRateMovies(page)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<List<Genre>> getGenres() {
        return mApi.getGenres()
                .map(new Function<GenreResult, List<Genre>>() {
                    @Override
                    public List<Genre> apply(GenreResult genreResult) {
                        return genreResult.getGenres();
                    }
                });
    }
}

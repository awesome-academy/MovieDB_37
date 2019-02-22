package com.example.moviedb_37.data.source.remote.config.service;

import com.example.moviedb_37.data.source.remote.config.response.CategoryResult;
import com.example.moviedb_37.data.source.remote.config.response.GenreResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NameApi {
    @GET("/3/movie/popular")
    Single<CategoryResult> getPopularMovies(@Query("page") int page);

    @GET("/3/movie/now_playing")
    Single<CategoryResult> getNowPlayingMovies(@Query("page") int page);

    @GET("/3/movie/upcoming")
    Single<CategoryResult> getUpComingMovies(@Query("page") int page);

    @GET("/3/movie/top_rated")
    Single<CategoryResult> getTopRateMovies(@Query("page") int page);

    @GET("/3/genre/movie/list")
    Single<GenreResult> getGenres();
}

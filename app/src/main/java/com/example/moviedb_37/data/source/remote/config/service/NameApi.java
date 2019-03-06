package com.example.moviedb_37.data.source.remote.config.service;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.source.remote.config.response.CategoryResult;
import com.example.moviedb_37.data.source.remote.config.response.GenreResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("/3/discover/movie")
    Single<CategoryResult> getMoviesByGenre(@Query("page") int page,
                                            @Query("with_genres") String genreId);

    @GET("/3/movie/{id}")
    Single<Movie> getMovieDetail(@Path("id") int movieId,
                                 @Query("append_to_response") String apend);

    @GET("/3/discover/movie")
    Single<CategoryResult> getMoviesByProduce(@Query("page") int page,
                                              @Query("with_companies") String companyId);

    @GET("/3/discover/movie")
    Single<CategoryResult> getMoviesByActor(@Query("page") int page,
                                            @Query("with_cast") String actorId);

    @GET("/3/search/{type}")
    Single<CategoryResult> searchMovie(@Path("type") String type,
                                       @Query("query") String keyword,
                                       @Query("page") int page);
}

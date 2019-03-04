package com.example.moviedb_37.data.source;

import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.data.model.Movie;

import java.util.List;

import io.reactivex.Single;

public interface MovieDataSource {

    interface Local extends MovieDataSource {
        Single<List<Movie>> getFavoriteMovies();

        boolean addFavariteMovie(Movie movie);

        boolean deleteFavoriteMovie(Movie movie);

        boolean canAddFavarite(Movie movie);
    }

    interface Remote extends MovieDataSource {
        Single<List<Movie>> getPopularMovies(int page);

        Single<List<Movie>> getNowPlayingMovies(int page);

        Single<List<Movie>> getUpComingMovies(int page);

        Single<List<Movie>> getTopRateMovies(int page);

        Single<List<Genre>> getGenres();

        Single<List<Movie>> getMoviesByGenre(int page, String genreId);

        Single<Movie> getMovieDetail(int movieId, String append);

        Single<List<Movie>> getMoviesByProduce(int page, String produceId);

        Single<List<Movie>> getMoviesByActor(int page, String actorId);
    }
}

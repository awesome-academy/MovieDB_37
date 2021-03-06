package com.example.moviedb_37.screen.moviedetails;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.screen.producer.ProducerNavigator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsViewModel {
    private static final String APPEND_TO_MOVIE_DETAIL = "videos,credits";
    public final ObservableField<Movie> movieObservable = new ObservableField<>();
    public final ObservableBoolean isFavoriteMovieObservable = new ObservableBoolean();
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public final ObservableBoolean isLoadingSuccess = new ObservableBoolean();
    private OnChangeVideoListener mOnChangeVideoListener;
    private MovieDetailNavigator mMovieDetailNavigator;

    private ProducerNavigator mProduceNavigator;

    public MovieDetailsViewModel(int movieId, MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadMovies(movieId);
        checkFavoriteMovie(movieId);
    }

    private void checkFavoriteMovie(int movieId) {
        isFavoriteMovieObservable.set(!mMovieRepository.canAddFavorite(movieId));
    }

    public void onFavoriteClick(Movie movie) {
        if (movie == null) {
            return;
        }
        if (isFavoriteMovieObservable.get()) {
            deleteFavoriteMovie(movie);
            return;
        }
        adddFavoriteMovie(movie);
    }

    private void adddFavoriteMovie(Movie movie) {
        boolean isSuccess = mMovieRepository.addFavoriteMovie(movie);
        if (isSuccess) {
            isFavoriteMovieObservable.set(true);
        }
    }

    private void deleteFavoriteMovie(Movie movie) {
        boolean isSuccess = mMovieRepository.deleteFavoriteMovie(movie);
        if (isSuccess) {
            isFavoriteMovieObservable.set(false);
        }
    }

    public void setNavigator(MovieDetailNavigator navigator) {
        mMovieDetailNavigator = navigator;
    }

    public void setProduceNavigator(ProducerNavigator produceNavigator) {
        mProduceNavigator = produceNavigator;
    }

    public void back() {
        mMovieDetailNavigator.back();
    }

    public void setOnChangeVideoListener(OnChangeVideoListener listener) {
        mOnChangeVideoListener = listener;
    }

    private void loadMovies(final int movieId) {
        Disposable disposable = mMovieRepository.getMovieDetail(movieId, APPEND_TO_MOVIE_DETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    movieObservable.set(movie);
                    if (movie.getVideoResult().getVideos().size() > 0) {
                        mOnChangeVideoListener.setVideoId(
                                movie.getVideoResult().getVideos().get(0).getKey());
                        isLoadingSuccess.set(true);
                    }
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    public Movie getMovie() {
        return movieObservable.get();
    }

    private void handleError(String message) {
        isLoadingSuccess.set(true);
    }

    public void clear() {
        mCompositeDisposable.clear();
    }
}

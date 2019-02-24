package com.example.moviedb_37.screen.movies;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.moviedb_37.data.model.CategoryKey;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.screen.home.HomeViewModel;
import com.example.moviedb_37.util.Constans;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailViewModel {
    private String mKey;
    public final ObservableList<Movie> moviesObservable = new ObservableArrayList<>();
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private int mCurrentPage;

    public MovieDetailViewModel(MovieRepository movieRepository, int loadBy, String key) {
        mKey = key;
        mMovieRepository = movieRepository;
        mCurrentPage = Constans.FIRST_PAGE;
        loadMovies(loadBy);
    }

    private void loadMovies(int loadBy) {
        if (loadBy == HomeViewModel.GENRE_SOURCE) {
            loadMoviesByGenre();
            return;
        }
        loadMoviesByCategory();
    }

    private void loadMoviesByCategory() {
        switch (mKey) {
            case CategoryKey.CATEGORY_POPULAR:
                loadPopularMovies();
                break;
            case CategoryKey.CATEGORY_NOW_PLAYING:
                loadNowPlayingMovies();
                break;
            case CategoryKey.CATEGORY_UP_COMING:
                loadUpComingMovies();
                break;
            case CategoryKey.CATEGORY_TOP_RATE:
                loadTopRateMovies();
                break;
        }
    }

    private void loadTopRateMovies() {
        Disposable disposable = mMovieRepository.getTopRateMovies(Constans.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadUpComingMovies() {
        Disposable disposable = mMovieRepository.getUpComingMovies(Constans.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadNowPlayingMovies() {
        Disposable disposable = mMovieRepository.getNowPlayingMovies(Constans.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadPopularMovies() {
        Disposable disposable = mMovieRepository.getPopularMovies(Constans.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadMoviesByGenre() {
        Disposable disposable = mMovieRepository.getMoviesByGenre(mCurrentPage, mKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }


    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
    }
}

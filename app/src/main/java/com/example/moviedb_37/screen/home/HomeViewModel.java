package com.example.moviedb_37.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.util.Constans;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseObservable {

    public static final String BUNDLE_SOURCE = "BUNDLE_SOURCE";
    public static final String BUNDLE_KEY = "BUNDLE_KEY";
    public static final String BUNDLE_NAME = "BUNDLE_NAME";
    public static final int GENRE_SOURCE = 0;
    public static final int CATEGORY_SOURCE = 1;

    public ObservableBoolean mIsLoadMorePopular = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreNowPlaying = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreTopRate = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreUpComing = new ObservableBoolean();

    private CategoryAdapter mPopularAdapter;
    private CategoryAdapter mNowPlayingAdapter;
    private CategoryAdapter mTopRateAdapter;
    private CategoryAdapter mUpComingAdapter;

    private GenresAdapter mGenresAdapter;
    private LinearLayoutManager mGenresLayoutManager;

    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private List<Movie> mMorePopularMovies = new ArrayList<>();
    private List<Movie> mMoreNowPlayingMovies = new ArrayList<>();
    private List<Movie> mMoreUpComingMovies = new ArrayList<>();
    private List<Movie> mMoreTopRateMovies = new ArrayList<>();

    public final ObservableList<Movie> popularMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> nowPlayingMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> upComingMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> topRateMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Genre> genresObservable = new ObservableArrayList<>();


    private HomeNavigator mNavigator;

    public HomeViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        initData();
    }

    public void initData() {
        loadPopularMovies();
        laodNowPlayingMovies();
        loadUpComingMovies();
        loadTopRateMovies();
        loadGenre();
    }

    private void loadGenre() {
        Disposable disposable = mMovieRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genres -> genresObservable.addAll(genres)
                        , throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadTopRateMovies() {
        Disposable disposable = mMovieRepository.getTopRateMovies(Constans.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    topRateMoviesObservable.addAll(movies.subList(
                            0,
                            movies.size() / Constans.SEPARATE_UNIT));
                    mMoreTopRateMovies.addAll(movies.subList(
                            movies.size() / Constans.SEPARATE_UNIT + Constans.INDEX_UNIT,
                            movies.size() - Constans.INDEX_UNIT));
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadUpComingMovies() {
        Disposable disposable = mMovieRepository.getUpComingMovies(Constans.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    upComingMoviesObservable.addAll(movies.subList(
                            0,
                            movies.size() / Constans.SEPARATE_UNIT));
                    mMoreUpComingMovies.addAll(movies.subList(
                            movies.size() / Constans.SEPARATE_UNIT + Constans.INDEX_UNIT,
                            movies.size() - Constans.INDEX_UNIT));
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void laodNowPlayingMovies() {
        Disposable disposable = mMovieRepository.getNowPlayingMovies(Constans.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    nowPlayingMoviesObservable.addAll(movies.subList(
                            0,
                            movies.size() / Constans.SEPARATE_UNIT));
                    mMoreNowPlayingMovies.addAll(movies.subList(
                            movies.size() / Constans.SEPARATE_UNIT + Constans.INDEX_UNIT,
                            movies.size() - Constans.INDEX_UNIT));
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadPopularMovies() {
        Disposable disposable = mMovieRepository.getPopularMovies(Constans.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    popularMoviesObservable.addAll(movies.subList(
                            0,
                            movies.size() / Constans.SEPARATE_UNIT));
                    mMorePopularMovies.addAll(movies.subList(
                            movies.size() / Constans.SEPARATE_UNIT + Constans.INDEX_UNIT,
                            movies.size() - Constans.INDEX_UNIT));
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    public LinearLayoutManager getGenresLayoutManager() {
        return mGenresLayoutManager;
    }


    public void setNavigator(HomeNavigator navigator) {
        mNavigator = navigator;
    }

    public void onClickLoadMoreNowPlaying(View view) {
        nowPlayingMoviesObservable.addAll(mMoreNowPlayingMovies);
        mIsLoadMoreNowPlaying.set(true);
    }

    public void onClickLoadMorePopular(View view) {
        popularMoviesObservable.addAll(mMorePopularMovies);
        mIsLoadMorePopular.set(true);
    }

    public void onClickLoadMoreTopRate(View view) {
        topRateMoviesObservable.addAll(mMoreTopRateMovies);
        mIsLoadMoreTopRate.set(true);
    }

    public void onClickLoadMoreUpComing(View view) {
        upComingMoviesObservable.addAll(mMoreUpComingMovies);
        mIsLoadMoreUpComing.set(true);
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
    }

    public void onCategoryClick(View view, String key, String name) {
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_SOURCE, CATEGORY_SOURCE);
        bundle.putString(BUNDLE_KEY, key);
        bundle.putString(BUNDLE_NAME, name);
        mNavigator.showMovies(bundle);
    }
}

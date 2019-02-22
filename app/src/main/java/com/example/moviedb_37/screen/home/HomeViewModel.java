package com.example.moviedb_37.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
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

    private ObservableField<List<Movie>> mPopularMovies = new ObservableField<>();
    private ObservableField<List<Movie>> mNowPlayingMovies = new ObservableField<>();
    private ObservableField<List<Movie>> mUpComingMovies = new ObservableField<>();
    private ObservableField<List<Movie>> mTopRateMovies = new ObservableField<>();
    private ObservableField<List<Genre>> mGenres = new ObservableField<>();

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
                .subscribe(genres -> mGenres.set(genres)
                        , throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadTopRateMovies() {
        Disposable disposable = mMovieRepository.getTopRateMovies(Constans.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    mTopRateMovies.set(movies.subList(
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
                    mUpComingMovies.set(movies.subList(
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
                    mNowPlayingMovies.set(movies.subList(
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
                    mPopularMovies.set(movies.subList(
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

    public void setGenresLayoutManager(LinearLayoutManager genresLayoutManager) {
        mGenresLayoutManager = genresLayoutManager;
    }

    public ObservableField<List<Movie>> getPopularMovies() {
        return mPopularMovies;
    }

    public ObservableField<List<Movie>> getNowPlayingMovies() {
        return mNowPlayingMovies;
    }

    public ObservableField<List<Movie>> getTopRateMovies() {
        return mTopRateMovies;
    }

    public ObservableField<List<Movie>> getUpComingMovies() {
        return mUpComingMovies;
    }

    public ObservableField<List<Genre>> getGenres() {
        return mGenres;
    }

    public void onClickLoadMoreNowPlaying(View view) {
        mNowPlayingMovies.get().addAll(mMoreUpComingMovies);
        mIsLoadMoreNowPlaying.set(true);
    }

    public void onClickLoadMorePopular(View view) {
        mPopularMovies.get().addAll(mMorePopularMovies);
        mIsLoadMorePopular.set(true);
    }

    public void onClickLoadMoreTopRate(View view) {
        mTopRateMovies.get().addAll(mMoreTopRateMovies);
        mIsLoadMoreTopRate.set(true);
    }

    public void onClickLoadMoreUpComing(View view) {
        mUpComingMovies.get().addAll(mMoreUpComingMovies);
        mIsLoadMoreUpComing.set(true);
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
    }
}

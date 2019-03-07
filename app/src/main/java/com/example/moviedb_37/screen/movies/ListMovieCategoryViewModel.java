package com.example.moviedb_37.screen.movies;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
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

public class ListMovieCategoryViewModel {
    private int mLoadBy;
    private String mKey;
    public final ObservableList<Movie> moviesObservable = new ObservableArrayList<>();
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private int mCurrentPage;
    public final ObservableBoolean isLoadMore = new ObservableBoolean(false);
    public final ObservableBoolean isLoadingSuccess = new ObservableBoolean();
    public ListMovieCategoryViewModel(MovieRepository movieRepository, int loadBy, String key) {
        mKey = key;
        mLoadBy = loadBy;
        mMovieRepository = movieRepository;
        mCurrentPage = Constans.FIRST_PAGE;
        isLoadMore.set(false);
        loadMovies(mLoadBy);
    }

    public int getLoadBy() {
        return mLoadBy;
    }

    public void loadMovies(int loadBy) {
        if (loadBy == HomeViewModel.GENRE_SOURCE) {
            loadMoviesByGenre();
            return;
        }
        if (loadBy == HomeViewModel.PRODUCE_SOURCE) {
            loadMoviesByProduce();
            return;
        }
        if (loadBy == HomeViewModel.ACTOR_SOURCE) {
            loadMoviesByActor();
            return;
        }
        loadMoviesByCategory();
    }

    private void loadMoviesByActor() {
        Disposable disposable = mMovieRepository.getMoviesByActor(mCurrentPage, mKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    moviesObservable.addAll(movies);
                    isLoadMore.set(false);
                    isLoadingSuccess.set(true);
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadMoviesByProduce() {
        Disposable disposable = mMovieRepository.getMoviesByProduce(mCurrentPage, mKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    moviesObservable.addAll(movies);
                    isLoadMore.set(false);
                    isLoadingSuccess.set(true);
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
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
        Disposable disposable = mMovieRepository.getTopRateMovies(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        isLoadMore.set(false);
        isLoadingSuccess.set(true);
        mCompositeDisposable.add(disposable);
    }

    private void loadUpComingMovies() {
        Disposable disposable = mMovieRepository.getUpComingMovies(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        isLoadMore.set(false);
        isLoadingSuccess.set(true);
        mCompositeDisposable.add(disposable);
    }

    private void loadNowPlayingMovies() {
        Disposable disposable = mMovieRepository.getNowPlayingMovies(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        isLoadMore.set(false);
        isLoadingSuccess.set(true);
        mCompositeDisposable.add(disposable);
    }

    private void loadPopularMovies() {
        Disposable disposable = mMovieRepository.getPopularMovies(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        isLoadMore.set(false);
        isLoadingSuccess.set(true);
        mCompositeDisposable.add(disposable);
    }

    private void loadMoviesByGenre() {
        Disposable disposable = mMovieRepository.getMoviesByGenre(mCurrentPage, mKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesObservable.addAll(movies),
                        throwable -> handleError(throwable.getMessage()));
        isLoadMore.set(false);
        isLoadingSuccess.set(true);
        mCompositeDisposable.add(disposable);
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
        isLoadMore.set(false);
        isLoadingSuccess.set(true);
    }

    public void increaseCurrentPage() {
        mCurrentPage += Constans.INDEX_UNIT;
    }
}

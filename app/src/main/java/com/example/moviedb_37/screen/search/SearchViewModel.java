package com.example.moviedb_37.screen.search;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.util.Constans;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends BaseObservable {
    private static final String[] SEARCH_TYPE = {"movie"};
    private MovieRepository mMovieRepository;

    public final ObservableList<Movie> searchedMoviesObservable = new ObservableArrayList<>();
    public final ObservableInt totalResultObservable = new ObservableInt();

    private SearchNavigator mSearchNavigator;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private String mKey;
    private int mCurrentPage;
    private String mSearchType;
    public final ObservableBoolean mIsLoadMore = new ObservableBoolean(false);
    public final ObservableBoolean isLoadingSuccess = new ObservableBoolean(true);

    public SearchViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        mSearchType = SEARCH_TYPE[0];
        mCurrentPage = Constans.INDEX_UNIT;
    }

    public void setSearchNavigator(SearchNavigator searchNavigator) {
        mSearchNavigator = searchNavigator;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public void setCurrentPage(int currentPage) {
        mCurrentPage = currentPage;
    }

    public void setSearchType(String searchType) {
        mSearchType = searchType;
    }

    public void onFilterClick() {
        mSearchNavigator.showSearchFilter();
    }

    public void searchMovie() {
        isLoadingSuccess.set(false);
        if (mSearchType.isEmpty() || mKey.isEmpty() || mCurrentPage <= 0) {
            handleError(null);
            return;
        }
        if (mCurrentPage == Constans.INDEX_UNIT) {
            isLoadingSuccess.set(false);
        }
        Disposable disposable = mMovieRepository.searchMovie(mSearchType, mKey, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryResult -> {
                    if (mCurrentPage > Constans.FIRST_PAGE) {
                        searchedMoviesObservable.addAll(categoryResult.getMovies());
                    } else {
                        totalResultObservable.set(categoryResult.getTotalResults());
                        searchedMoviesObservable.clear();
                        searchedMoviesObservable.addAll(categoryResult.getMovies());
                    }
                    mIsLoadMore.set(false);
                    isLoadingSuccess.set(true);
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
        mIsLoadMore.set(false);
        totalResultObservable.set(0);
        searchedMoviesObservable.clear();
        isLoadingSuccess.set(true);
    }

    public void increaseCurrentPage() {
        mCurrentPage += Constans.INDEX_UNIT;
    }
}

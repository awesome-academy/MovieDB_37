package com.example.moviedb_37.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.example.moviedb_37.data.model.Movie;

public class ItemMovieListViewModel extends BaseObservable {

    public ObservableField<Movie> mMovieObservableField = new ObservableField<>();
    public ObservableBoolean mIsFavotitiesObservable = new ObservableBoolean();
    private CategoryAdapter.ItemClickListener mItemClickListener;

    public ItemMovieListViewModel(CategoryAdapter.ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setMovie(Movie movie) {
        mMovieObservableField.set(movie);
    }

    public void setIsFavotities(boolean isFavorities) {
        mIsFavotitiesObservable.set(isFavorities);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || mMovieObservableField.get() == null) {
            return;
        }
        mItemClickListener.onMovieItemClick(mMovieObservableField.get());
    }

    public void onDeleteFavoriteMovieClick(View view) {
        if (mItemClickListener == null || mMovieObservableField.get() == null) {
            return;
        }
        mItemClickListener.onDeleteFavoritiesClick(mMovieObservableField.get());
    }
}

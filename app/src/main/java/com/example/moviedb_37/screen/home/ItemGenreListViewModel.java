package com.example.moviedb_37.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.example.moviedb_37.data.model.Genre;

public class ItemGenreListViewModel extends BaseObservable {

    public ObservableField<Genre> mGenreObserverField = new ObservableField<>();

    private GenresAdapter.ItemClickListener mItemClickListener;

    public ItemGenreListViewModel(GenresAdapter.ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setGenre(Genre genre) {
        mGenreObserverField.set(genre);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || mGenreObserverField.get() == null) {
            return;
        }
        mItemClickListener.onGenreItemClick(mGenreObserverField.get());
    }

    public ObservableField<Genre> getGenreObserverField() {
        return mGenreObserverField;
    }
}

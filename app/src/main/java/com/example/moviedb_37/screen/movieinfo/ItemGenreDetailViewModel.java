package com.example.moviedb_37.screen.movieinfo;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.example.moviedb_37.data.model.Genre;

public class ItemGenreDetailViewModel extends BaseObservable {
    public final ObservableField<Genre> genreObservable = new ObservableField<>();
    private GenresDetailMovieAdapter.ItemClickListener mListener;

    public ItemGenreDetailViewModel(GenresDetailMovieAdapter.ItemClickListener listener) {
        mListener = listener;
    }

    public void setGenre(Genre genre) {
        genreObservable.set(genre);
    }

    public void onItemClicked(View view) {
        if (mListener == null || genreObservable.get() == null) {
            return;
        }
        mListener.onGenreItemClick(genreObservable.get());
    }
}

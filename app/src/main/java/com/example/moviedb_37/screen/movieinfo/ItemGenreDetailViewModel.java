package com.example.moviedb_37.screen.movieinfo;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.moviedb_37.data.model.Genre;

public class ItemGenreDetailViewModel extends BaseObservable {
    public final ObservableField<Genre> genreObservable = new ObservableField<>();

    public void setGenre(Genre genre) {
        genreObservable.set(genre);
    }
}

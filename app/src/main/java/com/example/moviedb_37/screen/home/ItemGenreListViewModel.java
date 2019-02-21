package com.example.moviedb_37.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.moviedb_37.data.model.Genre;

public class ItemGenreListViewModel extends BaseObservable {

    public ObservableField<Genre> mGenreObserverField = new ObservableField<>();

    public void setGenres(Genre genre) {
        mGenreObserverField.set(genre);
    }
}

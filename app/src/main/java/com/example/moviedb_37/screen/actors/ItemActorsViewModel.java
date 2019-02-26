package com.example.moviedb_37.screen.actors;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.moviedb_37.data.model.Actor;

public class ItemActorsViewModel extends BaseObservable {
    public final ObservableField<Actor> actorObservable = new ObservableField<>();

    public void setActor(Actor actor) {
        actorObservable.set(actor);
    }
}

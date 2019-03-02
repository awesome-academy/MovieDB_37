package com.example.moviedb_37.screen.actors;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.example.moviedb_37.data.model.Actor;

public class ItemActorsViewModel extends BaseObservable {
    public final ObservableField<Actor> actorObservable = new ObservableField<>();
    private ActorsAdapter.ItemClickListener mItemClickListener;

    public ItemActorsViewModel(ActorsAdapter.ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || actorObservable.get() == null) {
            return;
        }
        mItemClickListener.onActorItemClick(actorObservable.get());
    }

    public void setActor(Actor actor) {
        actorObservable.set(actor);
    }
}

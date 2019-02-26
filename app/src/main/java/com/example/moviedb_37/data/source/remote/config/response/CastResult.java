package com.example.moviedb_37.data.source.remote.config.response;

import com.example.moviedb_37.data.model.Actor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResult {
    @SerializedName("cast")
    @Expose
    private List<Actor> mActors;

    public List<Actor> getActors() {
        return mActors;
    }

    public CastResult setActors(List<Actor> actors) {
        mActors = actors;
        return this;
    }
}

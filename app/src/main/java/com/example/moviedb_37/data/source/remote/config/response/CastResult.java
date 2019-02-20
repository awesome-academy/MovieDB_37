package com.example.moviedb_37.data.source.remote.config.response;

import com.example.moviedb_37.data.model.Cast;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResult {
    @SerializedName("cast")
    @Expose
    private List<Cast> mCasts;

    public List<Cast> getCasts() {
        return mCasts;
    }

    public CastResult setCasts(List<Cast> casts) {
        mCasts = casts;
        return this;
    }
}

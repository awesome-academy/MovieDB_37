package com.example.moviedb_37.data.source.remote.config.response;

import com.example.moviedb_37.data.model.Genre;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreResult {
    @SerializedName("genres")
    private List<Genre> mGenres;

    public GenreResult(List<Genre> genres) {
        mGenres = genres;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public GenreResult setGenres(List<Genre> genres) {
        mGenres = genres;
        return this;
    }
}

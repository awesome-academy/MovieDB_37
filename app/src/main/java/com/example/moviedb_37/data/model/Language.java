package com.example.moviedb_37.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {
    @SerializedName("iso_639_1")
    @Expose
    private String mIso639;
    @SerializedName("name")
    @Expose
    private String mName;

    public Language(String iso639, String name) {
        mIso639 = iso639;
        mName = name;

    }

    public String getIso639() {
        return mIso639;
    }

    public Language setIso639(String iso639) {
        mIso639 = iso639;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Language setName(String name) {
        mName = name;
        return this;
    }
}

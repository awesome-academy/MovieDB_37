package com.example.moviedb_37.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("logo_path")
    @Expose
    private String mLogoPath;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("origin_country")
    @Expose
    private String mOrigionCountry;

    public Company(int id, String logoPath, String name, String origionCountry) {
        mId = id;
        mLogoPath = logoPath;
        mName = name;
        mOrigionCountry = origionCountry;
    }

    public int getId() {
        return mId;
    }

    public Company setId(int id) {
        mId = id;
        return this;
    }

    public String getLogoPath() {
        return mLogoPath;
    }

    public Company setLogoPath(String logoPath) {
        mLogoPath = logoPath;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Company setName(String name) {
        mName = name;
        return this;
    }

    public String getOrigionCountry() {
        return mOrigionCountry;
    }

    public Company setOrigionCountry(String origionCountry) {
        mOrigionCountry = origionCountry;
        return this;
    }
}

package com.example.moviedb_37.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Actor {
    @SerializedName("cast_id")
    @Expose
    private int mCastId;
    @SerializedName("credit_id")
    @Expose
    private String mCreditId;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("profile_path")
    @Expose
    private String mProfilePath;

    public Actor(int castId, String creditId, int id, String name, String profilePath) {
        mCastId = castId;
        mCreditId = creditId;
        mId = id;
        mName = name;
        mProfilePath = profilePath;
    }

    public int getCastId() {
        return mCastId;
    }

    public Actor setCastId(int castId) {
        mCastId = castId;
        return this;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public Actor setCreditId(String creditId) {
        mCreditId = creditId;
        return this;
    }

    public int getId() {
        return mId;
    }

    public Actor setId(int id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Actor setName(String name) {
        mName = name;
        return this;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public Actor setProfilePath(String profilePath) {
        mProfilePath = profilePath;
        return this;
    }
}

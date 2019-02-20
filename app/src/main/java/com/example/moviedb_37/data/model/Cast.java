package com.example.moviedb_37.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cast {
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

    public Cast(int castId, String creditId, int id, String name, String profilePath) {
        mCastId = castId;
        mCreditId = creditId;
        mId = id;
        mName = name;
        mProfilePath = profilePath;
    }

    public int getCastId() {
        return mCastId;
    }

    public Cast setCastId(int castId) {
        mCastId = castId;
        return this;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public Cast setCreditId(String creditId) {
        mCreditId = creditId;
        return this;
    }

    public int getId() {
        return mId;
    }

    public Cast setId(int id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Cast setName(String name) {
        mName = name;
        return this;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public Cast setProfilePath(String profilePath) {
        mProfilePath = profilePath;
        return this;
    }
}

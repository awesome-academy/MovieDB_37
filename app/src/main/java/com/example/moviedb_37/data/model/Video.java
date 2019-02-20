package com.example.moviedb_37.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("iso_639_1")
    @Expose
    private String mIso639;
    @SerializedName("iso_3166_1")
    @Expose
    private String mIso3166;
    @SerializedName("key")
    @Expose
    private String mKey;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("site")
    @Expose
    private String mSite;
    @SerializedName("size")
    @Expose
    private int mSize;
    @SerializedName("type")
    @Expose
    private String mType;

    public Video(String id, String key, String name) {
        mId = id;
        mKey = key;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public Video setId(String id) {
        mId = id;
        return this;
    }

    public String getIso639() {
        return mIso639;
    }

    public Video setIso639(String iso639) {
        mIso639 = iso639;
        return this;
    }

    public String getIso3166() {
        return mIso3166;
    }

    public Video setIso3166(String iso3166) {
        mIso3166 = iso3166;
        return this;
    }

    public String getKey() {
        return mKey;
    }

    public Video setKey(String key) {
        mKey = key;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Video setName(String name) {
        mName = name;
        return this;
    }

    public String getSite() {
        return mSite;
    }

    public Video setSite(String site) {
        mSite = site;
        return this;
    }

    public int getSize() {
        return mSize;
    }

    public Video setSize(int size) {
        mSize = size;
        return this;
    }

    public String getType() {
        return mType;
    }

    public Video setType(String type) {
        mType = type;
        return this;
    }
}

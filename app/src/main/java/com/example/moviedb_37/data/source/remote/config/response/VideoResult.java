package com.example.moviedb_37.data.source.remote.config.response;

import com.example.moviedb_37.data.model.Video;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResult {
    @SerializedName("results")
    @Expose
    private List<Video> mVideos;

    public List<Video> getVideos() {
        return mVideos;
    }

    public VideoResult setVideos(List<Video> videos) {
        mVideos = videos;
        return this;
    }
}

package com.example.moviedb_37.screen.trailer;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.moviedb_37.data.model.Video;

public class ItemTrailerViewModel extends BaseObservable {
    public final ObservableField<Video> videoObservable = new ObservableField<>();

    public void setTrailer(Video video) {
        videoObservable.set(video);
    }
}

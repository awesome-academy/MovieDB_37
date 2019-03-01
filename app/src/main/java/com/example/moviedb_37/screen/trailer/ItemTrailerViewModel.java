package com.example.moviedb_37.screen.trailer;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.example.moviedb_37.data.model.Video;

public class ItemTrailerViewModel extends BaseObservable {

    public final ObservableField<Video> videoObservable = new ObservableField<>();
    private TrailerAdapter.ItemClickListener mItemClickListener;

    public ItemTrailerViewModel(TrailerAdapter.ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setTrailer(Video video) {
        videoObservable.set(video);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || videoObservable.get() == null) {
            return;
        }
        mItemClickListener.onTrailerItemClick(videoObservable.get());
    }
}

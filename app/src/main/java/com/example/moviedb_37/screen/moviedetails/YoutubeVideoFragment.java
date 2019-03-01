package com.example.moviedb_37.screen.moviedetails;

import android.os.Bundle;

import com.example.moviedb_37.BuildConfig;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class YoutubeVideoFragment extends YouTubePlayerFragment
        implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayer mYouTubePlayer;
    private String mVideoId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(BuildConfig.YOUTUBE_API_KEY, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mYouTubePlayer != null) mYouTubePlayer.release();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean restored) {
        mYouTubePlayer = player;
        if (!restored && mVideoId != null) {
            mYouTubePlayer.setFullscreenControlFlags(
                    YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
            mYouTubePlayer.cueVideo(mVideoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult result) {
        this.mYouTubePlayer = null;
    }

    public void setVideoId(String videoId) {
        mVideoId = videoId;
        if (mYouTubePlayer != null) {
            mYouTubePlayer.cueVideo(videoId);
        }
    }

    public String getVideoId() {
        return mVideoId;
    }

    public void pause() {
        if (mYouTubePlayer != null) mYouTubePlayer.pause();
    }
}

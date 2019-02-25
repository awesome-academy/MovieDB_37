package com.example.moviedb_37;

import android.app.Application;

import com.example.moviedb_37.data.source.remote.config.service.NameServiceClient;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NameServiceClient.initialize(this);
    }
}

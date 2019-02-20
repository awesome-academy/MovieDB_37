package com.example.moviedb_37.data.source.remote;

import com.example.moviedb_37.data.source.MovieDataSource;
import com.example.moviedb_37.data.source.remote.config.service.NameApi;
import com.example.moviedb_37.data.source.remote.config.service.NameServiceClient;

public class MovieRemoteDataSource implements MovieDataSource.Remote {

    private static MovieRemoteDataSource sInstance;
    private NameApi mApi;

    public MovieRemoteDataSource(NameApi mNameApi) {
        this.mApi = mNameApi;
    }

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieRemoteDataSource(NameServiceClient.getInstance());
        }
        return sInstance;
    }
}

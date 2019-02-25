package com.example.moviedb_37.data.source.remote.config.service;

import android.app.Application;

import com.example.moviedb_37.util.StringUtil;

public class NameServiceClient extends ServiceClient {

    private static final String END_POINT_URL = "https://api.themoviedb.org";
    private static final String EXCEPTION_MESSAGE =
            " is not initialized, call initialize(..) method first.";
    private static NameApi sApiInstacne;

    public static void initialize(Application application) {
        sApiInstacne = createService(application, END_POINT_URL, NameApi.class);
    }

    public static NameApi getInstance() {
        if (sApiInstacne == null) {
            throw new IllegalStateException(StringUtil
                    .append(NameServiceClient.class.getSimpleName(),EXCEPTION_MESSAGE));
        }
        return sApiInstacne;
    }
}

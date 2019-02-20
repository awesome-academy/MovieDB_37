package com.example.moviedb_37.data.source.remote.config.service;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    private static final int CONNECTION_TIMEOUT = 60;
    private static final int CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB;

    public static <T> T createService(Application application, String endPoint, Class<T> serviceClass) {
        return createService(application, endPoint, serviceClass, null);
    }

    private static <T> T createService(Application application, String endPoint, Class<T> serviceClass,
                                       Interceptor interceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        int cacheSize = CACHE_SIZE;
        httpClientBuilder.cache(new Cache(application.getCacheDir(), cacheSize));
        if (interceptor != null) {
            httpClientBuilder.addInterceptor(interceptor);
        }
        httpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(endPoint)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}

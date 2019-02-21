package com.example.moviedb_37.data.source.remote.config.service;

import android.app.Application;

import com.example.moviedb_37.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    private static final String QUERRY_PARAMETER_API_KEY = "api_key";
    private static final String API_KEY = BuildConfig.API_KEY;
    private static final int CONNECTION_TIMEOUT = 60;
    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    public static <T> T createService(Application application, String endPoint, Class<T> serviceClass) {
        return createService(application, endPoint, serviceClass, initInterceptor());
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

    private static Interceptor initInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalHttpUrl = originalRequest.url();

                HttpUrl newUrl = originalHttpUrl.newBuilder()
                        .addQueryParameter(QUERRY_PARAMETER_API_KEY, API_KEY)
                        .build();

                Request.Builder customBuilder = originalRequest.newBuilder().url(newUrl);
                Request customRequest = customBuilder.build();

                return chain.proceed(customRequest);
            }
        };

    }
}

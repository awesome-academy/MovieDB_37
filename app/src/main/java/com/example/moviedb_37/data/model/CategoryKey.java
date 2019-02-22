package com.example.moviedb_37.data.model;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        CategoryKey.CATEGORY_POPULAR, CategoryKey.CATEGORY_NOW_PLAYING,
        CategoryKey.CATEGORY_TOP_RATE, CategoryKey.CATEGORY_UP_COMING
})
public @interface CategoryKey {
    String CATEGORY_POPULAR = "/3/movie/popular";
    String CATEGORY_NOW_PLAYING = "/3/movie/now_playing";
    String CATEGORY_UP_COMING = "/3/movie/upcoming";
    String CATEGORY_TOP_RATE = "/3/movie/top_rated";
}

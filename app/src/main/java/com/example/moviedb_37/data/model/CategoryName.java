package com.example.moviedb_37.data.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        CategoryName.CATEGORY_POPULAR, CategoryName.CATEGORY_NOW_PLAYING,
        CategoryName.CATEGORY_TOP_RATE, CategoryName.CATEGORY_UP_COMING
})
public @interface CategoryName {
    String CATEGORY_POPULAR = "Popular";
    String CATEGORY_NOW_PLAYING = "Now Playing";
    String CATEGORY_UP_COMING = "Up Coming";
    String CATEGORY_TOP_RATE = "Top Rate";
}

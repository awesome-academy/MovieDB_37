package com.example.moviedb_37.util.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.screen.home.CategoryAdapter;
import com.example.moviedb_37.screen.home.GenresAdapter;
import com.example.moviedb_37.util.StringUtil;

import java.util.List;

public class BindingUtils {

    private static final int IMAGE_SIZE_200 = 200;

    @BindingAdapter({"app:bindMovies"})
    public static void setMoviesForRecyclerView(RecyclerView recyclerView,
                                                List<Movie> movies) {

        CategoryAdapter adapter = (CategoryAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(movies);
        }
    }


    @BindingAdapter("app:bindGenres")
    public static void setGenresForRecyclerView(RecyclerView recyclerView,
                                                List<Genre> genres) {
        GenresAdapter adapter = (GenresAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(genres);
        }
    }

    @BindingAdapter({"layoutManager"})
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LinearLayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"setVisibility"})
    public static void setVisibleForView(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
            return;
        }
        view.setVisibility(View.GONE);
    }

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            return;
        }
        String imageLink = StringUtil.getImageLink(IMAGE_SIZE_200, url);
        Glide.with(imageView.getContext())
                .load(imageLink)
                .into(imageView);
    }

    @BindingAdapter("android:scaleType")
    public static void setScaleType(ImageView imageView, String backdropPath) {
        if (backdropPath.isEmpty() || backdropPath == null) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
}

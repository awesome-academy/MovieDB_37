package com.example.moviedb_37.util.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviedb_37.BuildConfig;
import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Actor;
import com.example.moviedb_37.data.model.Company;
import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.model.Video;
import com.example.moviedb_37.screen.actors.ActorsAdapter;
import com.example.moviedb_37.screen.home.CategoryAdapter;
import com.example.moviedb_37.screen.home.GenresAdapter;
import com.example.moviedb_37.screen.movieinfo.GenresDetailMovieAdapter;
import com.example.moviedb_37.screen.producer.ProducerAdapter;
import com.example.moviedb_37.screen.trailer.TrailerAdapter;
import com.example.moviedb_37.util.StringUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

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

    @BindingAdapter("bindImage")
    public static void setRoundedImage(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            imageView.setImageResource(R.drawable.ic_my_movie);
            return;
        }
        String imageLink = StringUtil.getImageLink(IMAGE_SIZE_200, url);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_my_movie);
        requestOptions.error(R.drawable.ic_my_movie);
        Glide.with(imageView.getContext())
                .load(imageLink)
                .apply(requestOptions.circleCropTransform())
                .into(imageView);
    }

    @BindingAdapter("app:bindProduces")
    public static void setProducesForRecyclerView(RecyclerView recyclerView,
                                                  List<Company> companies) {
        ProducerAdapter adapter = (ProducerAdapter) recyclerView.getAdapter();
        if (adapter != null && companies != null) {
            adapter.replaceData(companies);
        }
    }

    @BindingAdapter("app:bindVideos")
    public static void setVideosForRecyclerView(RecyclerView recyclerView,
                                                List<Video> videos) {
        TrailerAdapter adapter = (TrailerAdapter) recyclerView.getAdapter();
        if (adapter != null && videos != null) {
            adapter.replaceData(videos);
        }
    }

    @BindingAdapter("app:bindActors")
    public static void setActorsForRecyclerView(RecyclerView recyclerView,
                                                List<Actor> actors) {
        ActorsAdapter adapter = (ActorsAdapter) recyclerView.getAdapter();
        if (adapter != null && actors != null) {
            adapter.replaceData(actors);
        }
    }

    @BindingAdapter("app:bindGenresDetail")
    public static void setGenresDetailForRecyclerView(RecyclerView recyclerView,
                                                      List<Genre> genres) {
        GenresDetailMovieAdapter adapter = (GenresDetailMovieAdapter) recyclerView.getAdapter();
        if (adapter != null && genres != null) {
            adapter.replaceData(genres);
        }
    }

    @BindingAdapter("app:youTubeThumbnailView")
    public static void setYouTubeThumbnailViewForTrailer(YouTubeThumbnailView thumbnailView,
                                                         final String videoKey) {
        if (videoKey == null) {
            thumbnailView.setImageResource(R.drawable.ic_play_circle);
            return;
        }
        YouTubeThumbnailView.OnInitializedListener listener =
                new YouTubeThumbnailView.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubeThumbnailView view,
                                                        final YouTubeThumbnailLoader loader) {
                        loader.setVideo(videoKey);
                        loader.setOnThumbnailLoadedListener(
                                new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                                    @Override
                                    public void onThumbnailLoaded(
                                            YouTubeThumbnailView youTubeThumbnailView, String s) {
                                        loader.release();
                                    }

                                    @Override
                                    public void onThumbnailError(YouTubeThumbnailView view,
                                                                 YouTubeThumbnailLoader.ErrorReason error) {
                                    }
                                });
                    }

                    @Override
                    public void onInitializationFailure(YouTubeThumbnailView view,
                                                        YouTubeInitializationResult result) {
                    }
                };
        thumbnailView.initialize(BuildConfig.YOUTUBE_API_KEY, listener);
    }
}

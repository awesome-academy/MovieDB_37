package com.example.moviedb_37.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.AbsListView;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.data.source.remote.MovieRemoteDataSource;
import com.example.moviedb_37.databinding.ActivityListMovieCategoryBinding;
import com.example.moviedb_37.screen.home.CategoryAdapter;
import com.example.moviedb_37.screen.home.HomeViewModel;

import java.util.ArrayList;

public class ListMovieCategoryActivity extends AppCompatActivity {
    private static final String EXTRAS_ARGS = "com.example.moviedb_37.extras.EXTRAS_ARGS";

    private ActivityListMovieCategoryBinding mBinding;
    private CategoryAdapter mAdapter;
    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

    private ListMovieCategoryViewModel mViewModel;
    private boolean isScrolling;
    private int currentItem, totalItem, scrolOutItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra(EXTRAS_ARGS);
        mViewModel = new ListMovieCategoryViewModel(
                MovieRepository.getInstance(MovieRemoteDataSource.getInstance()),

                bundle.getInt(HomeViewModel.BUNDLE_SOURCE),
                bundle.getString(HomeViewModel.BUNDLE_KEY));

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_movie_category);
        mBinding.setViewModel(mViewModel);
        setupToolbar();
        setupAdapters();
    }

    private void setupAdapters() {
        RecyclerView genresRecycler = mBinding.recycleGenre;
        genresRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new CategoryAdapter(new ArrayList<Movie>(0));
        genresRecycler.setAdapter(mAdapter);

        setupScrollListener(genresRecycler);
    }

    private void setupScrollListener(RecyclerView genresRecycler) {
        genresRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = mLayoutManager.getChildCount();
                totalItem = mLayoutManager.getItemCount();
                scrolOutItem = mLayoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItem + scrolOutItem == totalItem)) {
                    isScrolling = false;
                    mViewModel.isLoadMore.set(true);
                    mViewModel.increaseCurrentPage();
                    mViewModel.loadMovies(mViewModel.getLoadBy());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getMovieIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ListMovieCategoryActivity.class);
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    private String getTitleToolbar() {
        String title = "";
        title = getIntent().getBundleExtra(EXTRAS_ARGS).getString(HomeViewModel.BUNDLE_NAME);
        return title;
    }

    private void setupToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String title = getTitleToolbar();
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.clear();
    }
}

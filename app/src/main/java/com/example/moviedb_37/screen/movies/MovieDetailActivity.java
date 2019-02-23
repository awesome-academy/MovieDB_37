package com.example.moviedb_37.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.databinding.ActivityMovieDetailBinding;
import com.example.moviedb_37.screen.home.CategoryAdapter;
import com.example.moviedb_37.screen.home.HomeViewModel;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRAS_ARGS = "com.example.moviedb_37.extras.EXTRAS_ARGS";

    private ActivityMovieDetailBinding mBinding;
    private CategoryAdapter mAdapter;
    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

    private MovieDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra(EXTRAS_ARGS);
        mViewModel = new MovieDetailViewModel(
                bundle.getInt(HomeViewModel.BUNDLE_SOURCE),
                bundle.getString(HomeViewModel.BUNDLE_KEY));

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
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
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

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
        Intent intent = new Intent(context, MovieDetailActivity.class);
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
    }
}

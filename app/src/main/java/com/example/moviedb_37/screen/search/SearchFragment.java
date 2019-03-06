package com.example.moviedb_37.screen.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.data.source.local.FavoriteReaderDbHelper;
import com.example.moviedb_37.data.source.local.MovieLocalDataSource;
import com.example.moviedb_37.data.source.remote.MovieRemoteDataSource;
import com.example.moviedb_37.databinding.FragmentSearchBinding;
import com.example.moviedb_37.screen.home.CategoryAdapter;
import com.example.moviedb_37.screen.searchfilter.SearchFilterActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class SearchFragment extends Fragment implements SearchNavigator {
    public static final int REQUSET_CODE_FILTER = 9596;
    private static final String EXTRAS_ARGS = "vunt.com.vn.moviedb_28.extras.EXTRAS_ARGS";
    private static final String BUNDLE_SEARCH_BY = "BUNDLE_SEARCH_BY";
    private SearchViewModel mViewModel;
    private FragmentSearchBinding mBinding;

    private CategoryAdapter mSearchAdapter;

    private static Class mActivityClassName;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    public static Intent getSearchFragmentIntent(Context context, String serchBy) {
        Intent intent = new Intent(context, mActivityClassName);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_SEARCH_BY, serchBy);
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivityClassName = context.getClass();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViewModel();
        setNavigator();
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,
                container, false);
        mBinding.setViewModel(mViewModel);

        setupAdapters();

        return mBinding.getRoot();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SearchFragment.REQUSET_CODE_FILTER && resultCode == RESULT_OK) {
        }
    }

    @Override
    public void showSearchFilter() {
        startActivityForResult(
                SearchFilterActivity.getSearchFilterIntent(getContext()),
                REQUSET_CODE_FILTER);
    }

    @Override
    public void showMovieDetail(Movie movie) {

    }

    private void initViewModel() {
        FavoriteReaderDbHelper dbHelper = new FavoriteReaderDbHelper(getContext());
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(dbHelper));
        mViewModel = new SearchViewModel(movieRepository);
    }

    private void setupAdapters() {
        RecyclerView searchoRecycler = mBinding.recyclerSearch;
        mSearchAdapter = new CategoryAdapter(new ArrayList<Movie>(0));
        searchoRecycler.setAdapter(mSearchAdapter);
    }

    private void setNavigator() {
        mViewModel.setSearchNavigator(this);
    }
}

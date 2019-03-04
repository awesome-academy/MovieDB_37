package com.example.moviedb_37.screen.favorite;

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
import com.example.moviedb_37.databinding.FragmentFavoriteBinding;
import com.example.moviedb_37.screen.home.CategoryAdapter;
import com.example.moviedb_37.screen.moviedetails.MovieDetailsActivity;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment
        implements CategoryAdapter.ItemClickListener, FavoritiesNavigator {

    private FavoritiesViewModel mViewModel;
    private FragmentFavoriteBinding mBinding;
    private CategoryAdapter mFavoriteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewModel();
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite,
                container, false);
        mBinding.setViewModel(mViewModel);

        setupAdapters();

        return mBinding.getRoot();
    }

    private void setupAdapters() {
        RecyclerView favoriteRecycler = mBinding.recyclerFavorities;
        mFavoriteAdapter = new CategoryAdapter(new ArrayList<Movie>(0));
        mFavoriteAdapter.setItemClickListener(this);
        mFavoriteAdapter.setFavorities(true);
        favoriteRecycler.setAdapter(mFavoriteAdapter);
    }

    private void initViewModel() {
        FavoriteReaderDbHelper dbHelper = new FavoriteReaderDbHelper(getContext());
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(dbHelper));
        mViewModel = new FavoritiesViewModel(movieRepository);
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void showMovieDetail(Movie movie) {
        startActivity(MovieDetailsActivity.getIntent(getActivity(), movie));
    }

    @Override
    public void onMovieItemClick(Movie movie) {
        showMovieDetail(movie);
    }

    @Override
    public void onDeleteFavoritiesClick(Movie movie) {

    }
}

package com.example.moviedb_37.screen.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.data.source.remote.MovieRemoteDataSource;
import com.example.moviedb_37.databinding.FragmentHomeBinding;
import com.example.moviedb_37.screen.movies.MovieDetailActivity;

import java.util.ArrayList;

import static com.example.moviedb_37.screen.home.HomeViewModel.BUNDLE_KEY;
import static com.example.moviedb_37.screen.home.HomeViewModel.BUNDLE_NAME;
import static com.example.moviedb_37.screen.home.HomeViewModel.BUNDLE_SOURCE;
import static com.example.moviedb_37.screen.home.HomeViewModel.GENRE_SOURCE;

public class HomeFragment extends Fragment implements HomeNavigator
        , GenresAdapter.ItemClickListener {

    private FragmentHomeBinding mBinding;
    private HomeViewModel mViewModel;
    private CategoryAdapter mPopularAdapter, mNowPlayingAdapter,
            mTopRateAdapter, mUpComingAdapter;
    private GenresAdapter mGenresAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewModel();
        setNavigator();
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        mBinding.setViewModel(mViewModel);
        setupAdapters();
        return mBinding.getRoot();
    }

    private void setupAdapters() {
        RecyclerView popularRecycler = mBinding.recyclerPopular;
        mPopularAdapter = new CategoryAdapter(new ArrayList<Movie>(0));
        popularRecycler.setAdapter(mPopularAdapter);

        RecyclerView nowPlayingRecycler = mBinding.recyclerNowPlaying;
        mNowPlayingAdapter = new CategoryAdapter(new ArrayList<Movie>(0));
        nowPlayingRecycler.setAdapter(mNowPlayingAdapter);

        RecyclerView upComingRecycler = mBinding.recyclerUpComing;
        mUpComingAdapter = new CategoryAdapter(new ArrayList<Movie>(0));
        upComingRecycler.setAdapter(mUpComingAdapter);

        RecyclerView topRateRecycler = mBinding.recyclerTopRate;
        mTopRateAdapter = new CategoryAdapter(new ArrayList<Movie>(0));
        topRateRecycler.setAdapter(mTopRateAdapter);

        RecyclerView genresRecycler = mBinding.recyclerGenre;
        LinearLayoutManager genresLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        genresRecycler.setLayoutManager(genresLayoutManager);
        mGenresAdapter = new GenresAdapter(new ArrayList<Genre>(0));
        mGenresAdapter.setItemClickListener(this);
        genresRecycler.setAdapter(mGenresAdapter);
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private void initViewModel() {
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance());
        mViewModel = new HomeViewModel(movieRepository);
    }

    public void onDestroy() {
        super.onDestroy();
        mViewModel.clear();
    }

    @Override
    public void showMovies(Bundle bundle) {
        Intent intent = MovieDetailActivity.getMovieIntent(getActivity(), bundle);
        startActivity(intent);
    }

    private void setNavigator() {
        if (mViewModel != null) {
            mViewModel.setNavigator(this);
        }
    }

    @Override
    public void onItemClick(Genre genre) {
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_SOURCE, GENRE_SOURCE);
        bundle.putString(BUNDLE_KEY, String.valueOf(genre.getId()));
        bundle.putString(BUNDLE_NAME, genre.getName());
        showMovies(bundle);
    }
}

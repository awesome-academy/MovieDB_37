package com.example.moviedb_37.screen.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.data.source.remote.MovieRemoteDataSource;
import com.example.moviedb_37.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private CategoryAdapter mPopularAdapter, mNowPlayingAdapter,
            mTopRateAdapter, mUpComingAdapter;
    private GenresAdapter mGenresAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewModel();
        setLayoutManager();
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private void setLayoutManager() {
        if (mViewModel != null) {
            LinearLayoutManager genresLayoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            mViewModel.setGenresLayoutManager(genresLayoutManager);
        }
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
}

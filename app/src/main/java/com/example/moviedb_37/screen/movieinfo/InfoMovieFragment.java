package com.example.moviedb_37.screen.movieinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.databinding.FragmentInfoBinding;
import com.example.moviedb_37.screen.BaseFragment;

public class InfoMovieFragment extends BaseFragment {
    private FragmentInfoBinding mBinding;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mBinding = FragmentInfoBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        setupAdapters(mBinding.recyclerGenre,
                new GenresDetailMovieAdapter(mViewModel.getMovie().getGenre()));
        return view;
    }
}

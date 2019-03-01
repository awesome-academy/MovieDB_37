package com.example.moviedb_37.screen.movieinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.databinding.FragmentInfoBinding;
import com.example.moviedb_37.screen.BaseFragment;

import java.util.ArrayList;

public class InfoMovieFragment extends BaseFragment {
    private FragmentInfoBinding mBinding;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mBinding = FragmentInfoBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        setupAdapters(mBinding.recyclerGenre,
                new GenresDetailMovieAdapter(new ArrayList<Genre>()),
                layoutManager);
        return view;
    }
}

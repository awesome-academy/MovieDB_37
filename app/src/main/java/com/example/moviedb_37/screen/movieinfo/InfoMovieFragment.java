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

public class InfoMovieFragment extends BaseFragment
        implements GenresDetailMovieAdapter.ItemClickListener, InfoNavigator {
    private FragmentInfoBinding mBinding;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mBinding = FragmentInfoBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        GenresDetailMovieAdapter adapter = new GenresDetailMovieAdapter(new ArrayList<Genre>());
        adapter.setItemClickListener(this);
        setupAdapters(mBinding.recyclerGenre, adapter, layoutManager);
        return view;
    }

    @Override
    public void onGenreItemClick(Genre genre) {

    }

    @Override
    public void showMovies(Genre genre, int getBy) {

    }
}

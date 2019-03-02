package com.example.moviedb_37.screen.trailer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Video;
import com.example.moviedb_37.databinding.FragmentTrailerBinding;
import com.example.moviedb_37.screen.BaseFragment;

import java.util.ArrayList;

public class TrailerFragment extends BaseFragment implements TrailerAdapter.ItemClickListener {
    private FragmentTrailerBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailer, container, false);
        mBinding = FragmentTrailerBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        TrailerAdapter adapter = new TrailerAdapter(new ArrayList<Video>());
        adapter.setItemClickListener(this);
        setupAdapters(mBinding.recyclerTrailer, adapter);
        return view;
    }

    @Override
    public void onTrailerItemClick(Video video) {
    }
}

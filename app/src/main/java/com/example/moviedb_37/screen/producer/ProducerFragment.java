package com.example.moviedb_37.screen.producer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Company;
import com.example.moviedb_37.databinding.FragmentProducerBinding;
import com.example.moviedb_37.screen.BaseFragment;

import java.util.ArrayList;

public class ProducerFragment extends BaseFragment {
    private FragmentProducerBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_producer, container, false);
        mBinding = FragmentProducerBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        setupAdapters(mBinding.recyclerProducer,new ProducerAdapter(new ArrayList<Company>()));
        return view;
    }
}

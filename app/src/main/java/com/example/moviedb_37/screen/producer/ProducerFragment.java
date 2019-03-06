package com.example.moviedb_37.screen.producer;

import android.content.Context;
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

public class ProducerFragment extends BaseFragment implements ProducerAdapter.ItemClickListener {
    private FragmentProducerBinding mBinding;
    private OnProduceSelectedListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_producer, container, false);
        mBinding = FragmentProducerBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        ProducerAdapter adapter = new ProducerAdapter(new ArrayList<Company>());
        adapter.setItemClickListener(this);
        setupAdapters(mBinding.recyclerProducer, adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnProduceSelectedListener) context;
        } catch (ClassCastException e) {
            handleError(e.getMessage());
        }
    }

    @Override
    public void onProduceItemClick(Company company) {
        mListener.onProduceSelected(company);
    }

    public interface OnProduceSelectedListener {
        public void onProduceSelected(Company company);
    }

    private void handleError(String message) {
    }

    public static ProducerFragment newInstance() {
        ProducerFragment fragment = new ProducerFragment();
        return fragment;
    }
}

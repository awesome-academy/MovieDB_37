package com.example.moviedb_37.screen.producer;

import android.databinding.ObservableField;
import android.view.View;

import com.example.moviedb_37.data.model.Company;

public class ItemProducerViewModel {
    public final ObservableField<Company> companyObservable = new ObservableField<>();

    private ProducerAdapter.ItemClickListener mItemClickListener;

    public ItemProducerViewModel(ProducerAdapter.ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setCompany(Company company) {
        companyObservable.set(company);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || companyObservable.get() == null) {
            return;
        }
        mItemClickListener.onProduceItemClick(companyObservable.get());
    }
}

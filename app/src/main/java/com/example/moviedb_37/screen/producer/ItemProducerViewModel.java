package com.example.moviedb_37.screen.producer;

import android.databinding.ObservableField;

import com.example.moviedb_37.data.model.Company;

public class ItemProducerViewModel {
    public final ObservableField<Company> companyObservable = new ObservableField<>();

    public void setCompany(Company company) {
        companyObservable.set(company);
    }
}

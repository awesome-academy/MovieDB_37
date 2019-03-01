package com.example.moviedb_37.screen.producer;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Company;
import com.example.moviedb_37.databinding.ItemProducerBinding;

import java.util.List;

public class ProducerAdapter extends RecyclerView.Adapter<ProducerAdapter.ViewHolder> {
    private List<Company> mCompanies;

    public ProducerAdapter(List<Company> companies) {
        mCompanies = companies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemProducerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_producer,
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mCompanies.get(i));
    }

    @Override
    public int getItemCount() {
        return mCompanies != null ? mCompanies.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProducerBinding mBinding;
        private ItemProducerViewModel mItemProduceViewModel;

        public ViewHolder(ItemProducerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mItemProduceViewModel = new ItemProducerViewModel();
            mBinding.setViewModel(mItemProduceViewModel);
        }

        public void bindData(Company company) {
            mItemProduceViewModel.setCompany(company);
            mBinding.executePendingBindings();
        }
    }
}

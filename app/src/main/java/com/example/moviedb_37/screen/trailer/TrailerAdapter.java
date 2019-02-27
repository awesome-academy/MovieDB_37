package com.example.moviedb_37.screen.trailer;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Video;
import com.example.moviedb_37.databinding.ItemTrailerMovieBinding;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    private List<Video> mVideos;

    public TrailerAdapter(List<Video> videos) {
        mVideos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTrailerMovieBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_trailer_movie,
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mVideos.get(i));
    }

    @Override
    public int getItemCount() {
        return mVideos != null ? mVideos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTrailerMovieBinding mBinding;
        private ItemTrailerViewModel mItemTrailerViewModel;

        public ViewHolder(ItemTrailerMovieBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mItemTrailerViewModel = new ItemTrailerViewModel();
            mBinding.setViewModel(mItemTrailerViewModel);
        }

        public void bindData(Video video) {
            mItemTrailerViewModel.setTrailer(video);
            mBinding.executePendingBindings();
        }
    }
}

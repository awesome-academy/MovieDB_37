package com.example.moviedb_37.screen.movieinfo;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.databinding.ItemGenreDetailMovieBinding;

import java.util.List;

public class GenresDetailMovieAdapter extends
        RecyclerView.Adapter<GenresDetailMovieAdapter.ViewHolder> {
    private List<Genre> mGenres;

    public GenresDetailMovieAdapter(List<Genre> genres) {
        mGenres = genres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemGenreDetailMovieBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_genre_detail_movie,
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mGenres.get(i));
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public void replaceData(List<Genre> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemGenreDetailMovieBinding mBinding;
        private ItemGenreDetailViewModel mItemGenreDetailViewModel;

        public ViewHolder(ItemGenreDetailMovieBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mItemGenreDetailViewModel = new ItemGenreDetailViewModel();
            mBinding.setViewModel(mItemGenreDetailViewModel);
        }

        public void bindData(Genre genre) {
            mItemGenreDetailViewModel.setGenre(genre);
            mBinding.executePendingBindings();
        }
    }
}

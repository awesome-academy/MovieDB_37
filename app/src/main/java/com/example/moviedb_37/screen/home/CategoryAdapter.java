package com.example.moviedb_37.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.databinding.ItemMovieBinding;
import com.example.moviedb_37.util.Constans;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Movie> mMovies;
    private ItemClickListener mItemClickListener;


    public CategoryAdapter(List<Movie> movies) {
        this.mMovies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMovieBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_movie,
                viewGroup,
                false
        );
        return new ViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public CategoryAdapter setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        return this;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding mBinding;
        private ItemMovieListViewModel mItemMovieListViewModel;

        public ViewHolder(ItemMovieBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemMovieListViewModel = new ItemMovieListViewModel(listener);
            mBinding.setViewModel(mItemMovieListViewModel);
        }

        public void bindData(Movie movie) {
            mItemMovieListViewModel.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }

    public void addData(List<Movie> movies) {
        int posotionStart = mMovies.size();
        mMovies.addAll(movies);
        notifyItemRangeInserted(posotionStart, movies.size());
    }

    public void addData(Movie movie) {
        mMovies.add(movie);
        notifyItemInserted(mMovies.size() - Constans.INDEX_UNIT);
    }

    public void replaceData(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onMovieItemClick(Movie movie);
    }
}

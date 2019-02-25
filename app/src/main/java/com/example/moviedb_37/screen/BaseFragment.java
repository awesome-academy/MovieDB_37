package com.example.moviedb_37.screen;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.example.moviedb_37.screen.moviedetails.MovieDetailsViewModel;

public class BaseFragment extends Fragment {
    protected MovieDetailsViewModel mViewModel;

    public void setViewModel(MovieDetailsViewModel viewModel) {
        mViewModel = viewModel;
    }

    protected void setupAdapters(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}

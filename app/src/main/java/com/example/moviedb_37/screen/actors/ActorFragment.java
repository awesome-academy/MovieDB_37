package com.example.moviedb_37.screen.actors;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Actor;
import com.example.moviedb_37.databinding.FragmentActorBinding;
import com.example.moviedb_37.screen.BaseFragment;

import java.util.ArrayList;

public class ActorFragment extends BaseFragment {
    private FragmentActorBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actor, container, false);
        mBinding = FragmentActorBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        setupAdapters(mBinding.recyclerActors, new ActorsAdapter(new ArrayList<Actor>()));
        return view;
    }
}

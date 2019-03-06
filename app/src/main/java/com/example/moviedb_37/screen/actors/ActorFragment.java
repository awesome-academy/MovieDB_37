package com.example.moviedb_37.screen.actors;

import android.content.Context;
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

public class ActorFragment extends BaseFragment implements ActorsAdapter.ItemClickListener {
    private FragmentActorBinding mBinding;
    private OnActorSelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnActorSelectedListener) context;
        } catch (ClassCastException e) {
            handleError(e.getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actor, container, false);
        mBinding = FragmentActorBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        ActorsAdapter adapter = new ActorsAdapter(new ArrayList<Actor>());
        adapter.setItemClickListener(this);
        setupAdapters(mBinding.recyclerActors, adapter);
        return view;
    }

    @Override
    public void onActorItemClick(Actor actor) {
        mListener.onActorSelected(actor);
    }

    public interface OnActorSelectedListener {
        public void onActorSelected(Actor actor);
    }

    private void handleError(String message) {
    }
    public static ActorFragment newInstance() {
        ActorFragment fragment = new ActorFragment();
        return fragment;
    }
}

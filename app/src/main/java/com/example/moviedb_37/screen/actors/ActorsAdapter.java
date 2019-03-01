package com.example.moviedb_37.screen.actors;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Actor;
import com.example.moviedb_37.databinding.ItemActorInfoBinding;

import java.util.List;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ViewHolder> {
    private List<Actor> mActors;

    public ActorsAdapter(List<Actor> actors) {
        mActors = actors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemActorInfoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_actor_info,
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mActors.get(i));
    }

    @Override
    public int getItemCount() {
        return mActors != null ? mActors.size() : 0;
    }


    public void replaceData(List<Actor> actors) {
        mActors.clear();
        mActors.addAll(actors);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemActorInfoBinding mBinding;
        private ItemActorsViewModel mItemActorsViewModel;

        public ViewHolder(ItemActorInfoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mItemActorsViewModel = new ItemActorsViewModel();
            mBinding.setViewModel(mItemActorsViewModel);
        }

        public void bindData(Actor actor) {
            mItemActorsViewModel.setActor(actor);
            mBinding.executePendingBindings();
        }
    }
}

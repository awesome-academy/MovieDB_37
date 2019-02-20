package com.example.moviedb_37.util.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class Navigator {
    private AppCompatActivity mActivity;

    public Navigator(AppCompatActivity activity) {
        mActivity = activity;
    }

    public void replaceFragment(Fragment fragment, int container) {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();
    }
}

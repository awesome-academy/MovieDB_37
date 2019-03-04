package com.example.moviedb_37.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.moviedb_37.R;
import com.example.moviedb_37.screen.favorite.FavoriteFragment;
import com.example.moviedb_37.screen.home.HomeFragment;
import com.example.moviedb_37.util.ActivityUtils;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                new HomeFragment(), R.id.frame_fragments_container);
    }

    @Override
    protected void onStart() {
        if (mCurrentFragment != null && mCurrentFragment instanceof FavoriteFragment) {
            ((FavoriteFragment) mCurrentFragment).getViewModel().refreshFavoriteMovies();
        }
        super.onStart();
    }

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = this.findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            setupBottomNavigationViewContent(bottomNavigationView);
        }
    }

    private void setupBottomNavigationViewContent(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                showHomeScreen();
                break;
            case R.id.navigation_fovorities:
                showFavoriteScreen();
            case R.id.navigation_search:
                break;
            default:
                break;
        }
        return true;
    }


    public void showHomeScreen() {
        mCurrentFragment = HomeFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                mCurrentFragment, R.id.frame_fragments_container);
    }

    public void showFavoriteScreen() {
        mCurrentFragment = FavoriteFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                mCurrentFragment, R.id.frame_fragments_container);
    }

}

package com.example.moviedb_37.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.moviedb_37.R;
import com.example.moviedb_37.screen.favorite.FavoriteFragment;
import com.example.moviedb_37.screen.home.HomeFragment;
import com.example.moviedb_37.util.ActivityUtils;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String HOME_VIEWMODEL_TAG = "HOME_VIEWMODEL_TAG";
    public static final String FAVORITE_VIEWMODEL_TAG = "FAVORITE_VIEWMODEL_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();
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
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        new HomeFragment(), HOME_VIEWMODEL_TAG);
                break;
            case R.id.navigation_my_fovorities:
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        new FavoriteFragment(), FAVORITE_VIEWMODEL_TAG);
            case R.id.navigation_my_search:
                break;
            default:
                break;
        }
        return true;
    }
}

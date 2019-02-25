package com.example.moviedb_37.screen.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.moviedb_37.R;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.data.source.remote.MovieRemoteDataSource;
import com.example.moviedb_37.databinding.ActivityMovieDetailsBinding;
import com.example.moviedb_37.screen.movieinfo.InfoMovieFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.moviedb_37.screen.home.HomeViewModel.BUNDLE_KEY;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String EXTRAS_ARGS = "com.example.moviedb_37.extras.EXTRAS_ARGS";

    private MovieDetailsViewModel mViewModel;
    private ActivityMovieDetailsBinding mBinding;

    public static Intent getIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, String.valueOf(movie.getId()));
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new MovieDetailsViewModel(0
                , MovieRepository.getInstance(MovieRemoteDataSource.getInstance()));
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        mBinding.setViewModel(mViewModel);

        initViews();
    }

    private void initViews() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

        InfoMovieFragment infoMovieFragment = new InfoMovieFragment();
        infoMovieFragment.setViewModel(mViewModel);
        pagerAdapter.addFragment(infoMovieFragment, getString(R.string.tab_title_information));

    }

    public static class MainPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments = new ArrayList<>();
        private List<String> mTitles = new ArrayList<>();

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments != null ? mFragments.size() : 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }
    }
}

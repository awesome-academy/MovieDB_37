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
import com.example.moviedb_37.data.model.Actor;
import com.example.moviedb_37.data.model.Company;
import com.example.moviedb_37.data.model.Genre;
import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.model.Video;
import com.example.moviedb_37.data.repository.MovieRepository;
import com.example.moviedb_37.data.source.local.FavoriteReaderDbHelper;
import com.example.moviedb_37.data.source.local.MovieLocalDataSource;
import com.example.moviedb_37.data.source.remote.MovieRemoteDataSource;
import com.example.moviedb_37.databinding.ActivityMovieDetailsBinding;
import com.example.moviedb_37.screen.actors.ActorFragment;
import com.example.moviedb_37.screen.movieinfo.InfoMovieFragment;
import com.example.moviedb_37.screen.movies.ListMovieCategoryActivity;
import com.example.moviedb_37.screen.producer.ProducerFragment;
import com.example.moviedb_37.screen.trailer.TrailerFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.moviedb_37.screen.home.HomeViewModel.ACTOR_SOURCE;
import static com.example.moviedb_37.screen.home.HomeViewModel.BUNDLE_KEY;
import static com.example.moviedb_37.screen.home.HomeViewModel.PRODUCE_SOURCE;

public class MovieDetailsActivity extends AppCompatActivity implements
        OnChangeVideoListener, MovieDetailNavigator,
        ProducerFragment.OnProduceSelectedListener, ActorFragment.OnActorSelectedListener,
        TrailerFragment.OnTrailerSelectedListener {

    private static final String EXTRAS_ARGS = "com.example.moviedb_37.extras.EXTRAS_ARGS";

    private MovieDetailsViewModel mViewModel;
    private ActivityMovieDetailsBinding mBinding;
    private YoutubeVideoFragment mYouTubeVideoFragment;
    private String mMovieId;


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
        initViewModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        mBinding.setViewModel(mViewModel);
        initViews();
        mYouTubeVideoFragment =
                (YoutubeVideoFragment) getFragmentManager().findFragmentById(R.id.player);
    }

    private void initViewModel() {
        FavoriteReaderDbHelper dbHelper = new FavoriteReaderDbHelper(this);
        mMovieId = getIntent().getBundleExtra(EXTRAS_ARGS).getString(BUNDLE_KEY);
        mViewModel = new MovieDetailsViewModel(Integer.valueOf(mMovieId),
                MovieRepository.getInstance(MovieRemoteDataSource.getInstance(),
                        MovieLocalDataSource.getInstance(dbHelper)));
        mViewModel.setOnChangeVideoListener(this);
        mViewModel.setNavigator(this);
    }

    private void initViews() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

        InfoMovieFragment infoMovieFragment = InfoMovieFragment.newInstance();
        infoMovieFragment.setViewModel(mViewModel);
        pagerAdapter.addFragment(infoMovieFragment, getString(R.string.tab_title_information));

        ActorFragment actorsFragment = ActorFragment.newInstance();
        actorsFragment.setViewModel(mViewModel);
        pagerAdapter.addFragment(actorsFragment, getString(R.string.tab_title_actor));

        TrailerFragment trailerFragment = TrailerFragment.newInstance();
        trailerFragment.setViewModel(mViewModel);
        pagerAdapter.addFragment(trailerFragment, getString(R.string.tab_title_trailer));

        ProducerFragment produceFragment = ProducerFragment.newInstance();
        produceFragment.setViewModel(mViewModel);
        pagerAdapter.addFragment(produceFragment, getString(R.string.tab_title_producer));

        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void setVideoId(String videoId) {
        mYouTubeVideoFragment.setVideoId(videoId);
    }

    @Override
    public void back() {
        this.finish();
    }

    @Override
    public void showMovies(Actor actor, int getBy) {
        Genre genre = new Genre(String.valueOf(actor.getId()), actor.getName());
        startActivity(ListMovieCategoryActivity.getIntent(this, genre, getBy));
    }

    @Override
    public void showMovies(Genre genre, int getBy) {

    }

    @Override
    public void onProduceSelected(Company company) {
        showMovies(company, PRODUCE_SOURCE);
    }

    @Override
    public void showMovies(Company company, int getBy) {
        Genre genre = new Genre(String.valueOf(company.getId()), company.getName());
        startActivity(ListMovieCategoryActivity.getIntent(this, genre, getBy));
    }

    @Override
    public void onActorSelected(Actor actor) {
        showMovies(actor, ACTOR_SOURCE);
    }

    @Override
    public void onTrailerSelected(Video video) {
        setVideoId(video.getKey());
        mYouTubeVideoFragment.playVideo();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.clear();
    }
}

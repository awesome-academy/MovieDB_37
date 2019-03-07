package com.example.moviedb_37.screen.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.moviedb_37.R;
import com.example.moviedb_37.screen.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long TIME_TO_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.getMainActivityIntent(SplashActivity.this));
                finish();
            }
        }, TIME_TO_SPLASH);
    }
}

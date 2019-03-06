package com.example.moviedb_37.screen.searchfilter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.moviedb_37.R;
import com.example.moviedb_37.screen.search.SearchFragment;

public class SearchFilterActivity extends AppCompatActivity {
    private RadioGroup mRadioGroupFilter;

    public static Intent getSearchFilterIntent(Context context) {
        Intent intent = new Intent(context, SearchFilterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        initViews();
        setupToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                int radioSelectedId = mRadioGroupFilter.getCheckedRadioButtonId();
                RadioButton selectedRadio = findViewById(radioSelectedId);
                setResult(RESULT_OK, SearchFragment.getSearchFragmentIntent(this,
                        selectedRadio.getText().toString()));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    private void initViews() {
        mRadioGroupFilter = findViewById(R.id.radio_group_search_by);
    }

    private void setupToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.title_search_option);
    }
}

package com.example.taquio.trasearch.Guest;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;

import com.example.taquio.trasearch.BusinessProfile.SectionsPageAdapter;
import com.example.taquio.trasearch.R;

public class GuestHome extends AppCompatActivity {

    private Context mContext = GuestHome.this;
    private ViewPager mViewPager;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);

        mViewPager = (ViewPager) findViewById(R.id.guestContainer);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.guestTab);
        tabLayout.setupWithViewPager(mViewPager);

        setupViewPager(mViewPager);
        final SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.guestSearch);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
    }

    private void setupViewPager(ViewPager viewPager) {
        SectPageAdapter adapter = new SectPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new GuestArticlesFragment(), "Articles");
        adapter.addFragment(new GuestVideosFragment(), "Videos");
        viewPager.setAdapter(adapter);
    }
}

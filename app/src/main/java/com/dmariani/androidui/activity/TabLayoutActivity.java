package com.dmariani.androidui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dmariani.androidui.R;
import com.dmariani.androidui.fragment.SimpleTextFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danielle Mariani on 09/02/16.
 */
public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.feature_tablayout_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Caracas");
        titles.add("Margarita");
        titles.add("Canaima");
        titles.add("Medanos");
        titles.add("Morrocoy");
        titles.add("Paramo");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        viewPager.setAdapter(adapter);
    }

    /**
     * MENU
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<String> titles;

        public ViewPagerAdapter(FragmentManager manager, List<String> titles) {
            super(manager);
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return SimpleTextFragment.newInstance(titles.get(position));
        }

        @Override
        public int getCount() {
            if (titles == null || titles.isEmpty()) {
                return 0;
            }
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}

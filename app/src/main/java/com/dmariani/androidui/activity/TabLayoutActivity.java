package com.dmariani.androidui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dmariani.androidui.R;
import com.dmariani.androidui.fragment.ArticleFragment;
import com.dmariani.androidui.manager.ArticleManager;
import com.dmariani.androidui.model.Article;

import java.util.List;

/**
 * @author Danielle Mariani on 09/02/16.
 */
public class TabLayoutActivity extends AppCompatActivity {

    /**
     * Constants
     */
    public static final String TOOLBAR_LAYOUT_TYPE = "toolbar_type";
    public static final int ONLY_TOOLBAR = 0;
    public static final int IMAGE_AND_TOOLBAR = 1;
    private static final String LOG_TAG = "TabLayoutActivity";

    /**
     * Attributes
     */
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getExtras().getInt(TOOLBAR_LAYOUT_TYPE, ONLY_TOOLBAR);

        if (type == ONLY_TOOLBAR) {
            setContentView(R.layout.activity_tablayout);
        } else {
            setContentView(R.layout.activity_tablayout_image_toolbar);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.feature_tablayout_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (type == IMAGE_AND_TOOLBAR) {
            final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbarLayout.setTitleEnabled(false);
        }

        setUpViewPager();
    }

    private void setUpViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        boolean nestedScroll = type == IMAGE_AND_TOOLBAR;
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), ArticleManager.getArticles(this), nestedScroll);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
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
        private List<Article> items;
        private boolean nestedScroll;

        public ViewPagerAdapter(FragmentManager manager, List<Article> items, boolean nestedScroll) {
            super(manager);
            this.items = items;
            this.nestedScroll = nestedScroll;
        }

        @Override
        public Fragment getItem(int position) {
            return ArticleFragment.newInstance(items.get(position), nestedScroll);
        }

        @Override
        public int getCount() {
            if (items == null || items.isEmpty()) {
                return 0;
            }
            return items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items.get(position).getShortTitle();
        }
    }
}

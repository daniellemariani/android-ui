package com.dmariani.androidui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dmariani.androidui.R;
import com.dmariani.androidui.fragment.FeatureFloatingButtonFragment;
import com.dmariani.androidui.fragment.FeatureFloatingLabelFragment;

/**
 * @author Danielle Mariani on 09/02/16.
 */
public class CollapsingToolbarActivity extends AppCompatActivity {

    /**
     * Constants
     */
    public static final String TOOLBAR_LAYOUT_TYPE = "toolbar_type";
    public static final int ONLY_TOOLBAR = 0;
    public static final int IMAGE_AND_TOOLBAR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getIntent().getExtras().getInt(TOOLBAR_LAYOUT_TYPE, ONLY_TOOLBAR);

        if (type == ONLY_TOOLBAR) {
            setContentView(R.layout.activity_collapsing_toolbar);
        } else {
            setContentView(R.layout.activity_collapsing_image_toolbar);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.article_title));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hi", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}

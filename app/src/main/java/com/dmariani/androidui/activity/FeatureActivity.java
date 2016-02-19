package com.dmariani.androidui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dmariani.androidui.R;
import com.dmariani.androidui.fragment.FeatureFloatingButtonFragment;
import com.dmariani.androidui.fragment.FeatureFloatingLabelFragment;
import com.dmariani.androidui.fragment.FeatureRippleButtonFragment;

/**
 * @author Danielle Mariani on 08/02/16.
 */
public class FeatureActivity extends AppCompatActivity {

    public static final String FEATURE_TO_SHOW = "feature";
    public static final int FLOATING_LABELS_FEATURE = 0;
    public static final int FLOATING_BUTTON_FEATURE = 1;
    public static final int RIPPLE_BUTTON_FEATURE = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if (getIntent().getExtras() != null) {
            int featureToShow = getIntent().getExtras().getInt(FEATURE_TO_SHOW);
            init(featureToShow);
        }
    }

    private void init(int featureToShow) {
        switch (featureToShow) {
            case FLOATING_LABELS_FEATURE:
                setContentFragment(FeatureFloatingLabelFragment.newInstance());
                break;
            case FLOATING_BUTTON_FEATURE:
                setContentFragment(FeatureFloatingButtonFragment.newInstance());
                break;
            case RIPPLE_BUTTON_FEATURE:
                setContentFragment(FeatureRippleButtonFragment.newInstance());
                break;
        }
    }

    private void setContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_content, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

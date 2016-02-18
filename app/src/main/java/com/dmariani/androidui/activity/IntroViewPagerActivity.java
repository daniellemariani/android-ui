package com.dmariani.androidui.activity;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.dmariani.androidui.R;
import com.dmariani.androidui.fragment.SimpleTextFragment;
import com.dmariani.androidui.util.ImageUtils;
import com.dmariani.androidui.view.ViewPagerIndicator;

/**
 * @author Danielle Mariani on 18/02/16.
 */
public class IntroViewPagerActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Views
     */
    private ViewPager viewPager;
    private Button buttonSkip;
    private Button buttonFinish;
    private ImageButton buttonNext;

    /**
     * Attributes
     */
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.transparent_black_80));
        }

        setContentView(R.layout.activity_intro_viewpager);
        setUpViewPager();
    }

    private void setUpViewPager() {

        final int color1 = ContextCompat.getColor(this, R.color.blue);
        final int color2 = ContextCompat.getColor(this, R.color.amber);
        final int color3 = ContextCompat.getColor(this, R.color.green);
        final int[] colorList = new int[]{color1, color2, color3};

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        buttonSkip = (Button) findViewById(R.id.button_skip);
        buttonFinish = (Button) findViewById(R.id.button_finish);
        buttonNext = (ImageButton) findViewById(R.id.button_next);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
            buttonNext.setImageDrawable(
                    ImageUtils.tintDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_right_24dp), Color.WHITE)
            );

        buttonNext.setOnClickListener(this);
        buttonSkip.setOnClickListener(this);
        buttonFinish.setOnClickListener(this);

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        ViewPagerIndicator indicator = (ViewPagerIndicator) findViewById(R.id.view_pager_indicator);
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int index = position == (adapter.getCount() - 1) ? position : position + 1;
                int colorUpdate = (Integer) evaluator.evaluate(positionOffset, colorList[position], colorList[index]);
                viewPager.setBackgroundColor(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        viewPager.setBackgroundColor(color1);
                        break;
                    case 1:
                        viewPager.setBackgroundColor(color2);
                        break;
                    case 2:
                        viewPager.setBackgroundColor(color3);
                        break;
                }

                boolean isLastPage = position == (adapter.getCount() - 1);
                buttonNext.setVisibility(isLastPage ? View.GONE : View.VISIBLE);
                buttonFinish.setVisibility(isLastPage ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * CLICK EVENTS
     */

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_skip) {
            onClickSkipButton();
        } else if (id == R.id.button_next) {
            onClickNextButton();
        } else if (id == R.id.button_finish) {
            onClickFinishButton();
        }

    }

    private void onClickSkipButton() {
        finish();
    }

    private void onClickNextButton() {
        currentPage += 1;
        viewPager.setCurrentItem(currentPage, true);
    }

    private void onClickFinishButton() {
        finish();
    }

    /**
     * VIEW PAGER ADAPTER
     */

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private static final int TOTAL_ITEMS = 3;

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            String message = "Page " + (position + 1);
            int icon = R.drawable.ic_airplane_white;
            switch (position) {
                case 0:
                    icon = R.drawable.ic_airplane_white;
                    break;
                case 1:
                    icon = R.drawable.ic_car_white;
                    break;
                case 2:
                    icon = R.drawable.ic_train_white;
                    break;
            }
            return SimpleTextFragment.newInstance(message, icon);
        }

        @Override
        public int getCount() {
            return TOTAL_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page: " + (position + 1);
        }
    }
}

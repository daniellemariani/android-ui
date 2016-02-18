package com.dmariani.androidui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dmariani.androidui.R;

/**
 * @author by Danielle Mariani on 2/19/16.
 */
public class ViewPagerIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    public ViewPagerIndicator(Context context) {
        super(context);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        if (viewPager == null || viewPager.getAdapter() == null) {
            return;
        }

        viewPager.addOnPageChangeListener(this);

        removeAllViews();
        int count = viewPager.getAdapter().getCount();

        for (int i = 0; i < count; i++) {
            ImageView imageIndicator = createCircleIndicator(i == 0);
            addView(imageIndicator);
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        init();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    private ImageView createCircleIndicator(boolean selected) {
        int width = getResources().getDimensionPixelSize(R.dimen.view_pager_indicator_width);
        int height = getResources().getDimensionPixelSize(R.dimen.view_pager_indicator_height);
        int margin = getResources().getDimensionPixelSize(R.dimen.view_pager_indicator_margin);
        LayoutParams params = new LayoutParams(width, height);
        params.setMargins(0, 0, margin, 0);

        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(params);
        if (selected) {
            imageView.setBackgroundResource(R.drawable.view_pager_indicator_selected);
        } else {
            imageView.setBackgroundResource(R.drawable.view_pager_indicator_unselected);
        }
        return imageView;
    }

    private void updateIndicators(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            int background = i == position ? R.drawable.view_pager_indicator_selected : R.drawable.view_pager_indicator_unselected;
            getChildAt(i).setBackgroundResource(background);
        }
    }

    /**
     * VIEW PAGER LISTENER
     */

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        updateIndicators(position);
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(state);
        }
    }
}

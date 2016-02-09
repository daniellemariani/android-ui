package com.dmariani.androidui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmariani.androidui.R;

/**
 * @author Danielle Mariani on 08/02/16.
 */
public class FeatureFloatingButtonFragment extends BaseFragment {

    /**
     * Views
     */
    private FloatingActionButton floatingButton;

    public static FeatureFloatingButtonFragment newInstance() {
        return new FeatureFloatingButtonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feature_floating_button, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        floatingButton = (FloatingActionButton) getView().findViewById(R.id.floating_button);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackbarMessage("Hi!", "Action", null);
            }
        });
    }
}

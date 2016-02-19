package com.dmariani.androidui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dmariani.androidui.R;

/**
 * @author Danielle Mariani on 08/02/16.
 */
public class FeatureRippleButtonFragment extends BaseFragment {

    public static FeatureRippleButtonFragment newInstance() {
        return new FeatureRippleButtonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feature_ripple_button, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle(R.string.feature_ripple_button_title);
    }
}

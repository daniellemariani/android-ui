package com.dmariani.androidui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmariani.androidui.R;

/**
 * @author Danielle Mariani on 12/30/15.
 */
public class SimpleTextFragment extends BaseFragment {

    /**
     * Constants
     */
    private static final String ARG_MESSAGE = "message";

    /**
     * Views
     */
    private TextView textViewTitle;

    public static SimpleTextFragment newInstance(String message) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_MESSAGE, message);
        SimpleTextFragment fragment = new SimpleTextFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple_text, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewTitle = (TextView) getView().findViewById(R.id.textview_message);

        if (getArguments() != null) {
            String message = getArguments().getString(ARG_MESSAGE);
            setTitle(message);
            if (!TextUtils.isEmpty(message)) {
                textViewTitle.setText(message);
            }

        }

    }
}

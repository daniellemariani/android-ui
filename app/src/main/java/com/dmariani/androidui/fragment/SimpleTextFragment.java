package com.dmariani.androidui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private static final String ARG_ICON = "icon";

    /**
     * Views
     */
    private TextView textViewTitle;
    private ImageView imageView;

    public static SimpleTextFragment newInstance(String message) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_MESSAGE, message);
        SimpleTextFragment fragment = new SimpleTextFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static SimpleTextFragment newInstance(String message, int icon) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_MESSAGE, message);
        bundle.putInt(ARG_ICON, icon);
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
        imageView = (ImageView) getView().findViewById(R.id.imageview);

        if (getArguments() != null) {
            String message = getArguments().getString(ARG_MESSAGE);
            int icon = getArguments().getInt(ARG_ICON, -1);
            //setTitle(message);
            if (!TextUtils.isEmpty(message)) {
                textViewTitle.setText(message);
            }

            if (icon > 0) {
                imageView.setImageResource(icon);
                imageView.setVisibility(View.VISIBLE);
            }

        }

    }
}

package com.dmariani.androidui.fragment;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dmariani.androidui.R;

/**
 * @author Danielle Mariani on 08/02/16.
 */
public class BaseFragment extends Fragment {

    public void setTitle(int title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    public void setTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    public Context getContext() {
        return getActivity();
    }

    public void showSnackbarMessage(String message, String action, final View.OnClickListener listener) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG)
                .setAction(action, listener).setActionTextColor(getResources().getColor(R.color.textcolor_snackbar_action)).show();
    }
}

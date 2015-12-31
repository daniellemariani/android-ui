package com.dmariani.androidui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmariani.androidui.R;
import com.dmariani.androidui.util.FileUtils;
import com.dmariani.androidui.util.LogUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

/**
 * Provides basic operations with the device camera
 *
 * @author Danielle Mariani on 12/30/15.
 */
public class CameraFragment extends Fragment implements View.OnClickListener{

    /**
     * Constants
     */
    private static final int REQUEST_TAKE_PHOTO = 1;

    /**
     * Views
     */
    private TextView textViewTitle;
    private ImageView imageViewPhoto;
    private ImageView buttonPhoto;

    /**
     * Attributes
     */
    private File currentPhotoFile;
    private boolean toggleImageSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewTitle = (TextView) getView().findViewById(R.id.textview_message);
        imageViewPhoto = (ImageView) getView().findViewById(R.id.imageview_photo);
        buttonPhoto = (ImageView) getView().findViewById(R.id.button_photo);
        buttonPhoto.setOnClickListener(this);
        imageViewPhoto.setOnClickListener(this);
    }

    private void launchCameraIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            currentPhotoFile = null;
            try {
                currentPhotoFile = FileUtils.createImageFile();
            } catch (IOException e) {
                LogUtils.e(e);
            }

            if (currentPhotoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(currentPhotoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == getActivity().RESULT_OK) {
            Picasso.with(getActivity()).load(currentPhotoFile).into(imageViewPhoto);
            LogUtils.i("Photo saved: " + currentPhotoFile.getAbsolutePath());
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.button_photo) {
            launchCameraIntent();
        } else if (id == R.id.imageview_photo) {
            toggleImageSize();
        }
    }

    private void toggleImageSize() {
        if (currentPhotoFile == null) {
            return;
        }

        if (toggleImageSize) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().show();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.photo_thumbnail_width),
                    getResources().getDimensionPixelSize(R.dimen.photo_thumbnail_height));
            params.addRule(RelativeLayout.BELOW, R.id.textview_message);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageViewPhoto.setLayoutParams(params);
            imageViewPhoto.setBackgroundColor(Color.TRANSPARENT);
            buttonPhoto.setVisibility(View.VISIBLE);
        } else {
            ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageViewPhoto.setLayoutParams(params);
            imageViewPhoto.setBackgroundColor(Color.BLACK);
            buttonPhoto.setVisibility(View.GONE);
        }

        toggleImageSize = !toggleImageSize;
    }
}

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
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

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
public class CameraFragment extends Fragment implements View.OnClickListener {

    /**
     * Constants
     */
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_VIDEO_CAPTURE = 2;

    /**
     * Views
     */
    private TextView textViewTitle;
    private ImageView imageViewPhoto;
    private ImageView buttonPhoto;
    private ImageView buttonVideo;
    private VideoView videoViewFilm;

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
        videoViewFilm = (VideoView) getView().findViewById(R.id.videoview_film);
        buttonPhoto = (ImageView) getView().findViewById(R.id.button_photo);
        buttonVideo = (ImageView) getView().findViewById(R.id.button_video);

        buttonPhoto.setOnClickListener(this);
        buttonVideo.setOnClickListener(this);
        imageViewPhoto.setOnClickListener(this);

        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoViewFilm);
        videoViewFilm.setMediaController(mediaController);

    }

    private void launchPhotoIntent() {
        imageViewPhoto.setVisibility(View.VISIBLE);
        videoViewFilm.setVisibility(View.GONE);

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

    private void launchVideoIntent() {
        imageViewPhoto.setVisibility(View.GONE);
        videoViewFilm.setVisibility(View.VISIBLE);

        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (videoIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            File currentVideoFile = null;
            try {
                currentVideoFile = FileUtils.createVideoFile();
            } catch (IOException e) {
                LogUtils.e(e);
            }

            videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentVideoFile));
            videoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

            startActivityForResult(videoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == getActivity().RESULT_OK) {
            Picasso.with(getActivity()).load(currentPhotoFile).into(imageViewPhoto);
            LogUtils.i("Photo saved: " + currentPhotoFile.getAbsolutePath());
        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Uri videoUri = data.getData();
            LogUtils.i("Video saved: " + videoUri);
            videoViewFilm.setVideoURI(videoUri);
            videoViewFilm.start();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.button_photo) {
            launchPhotoIntent();
        } else if (id == R.id.button_video) {
            launchVideoIntent();
        } else if (id == R.id.imageview_photo) {
            toggleImageSize();
        }
    }

    private void toggleImageSize() {
        if (currentPhotoFile == null) {
            return;
        }

        if (toggleImageSize) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.photo_thumbnail_width),
                    getResources().getDimensionPixelSize(R.dimen.photo_thumbnail_height));
            params.addRule(RelativeLayout.BELOW, R.id.textview_message);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageViewPhoto.setLayoutParams(params);
            imageViewPhoto.setBackgroundColor(Color.TRANSPARENT);
            buttonPhoto.setVisibility(View.VISIBLE);
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageViewPhoto.setLayoutParams(params);
            imageViewPhoto.setBackgroundColor(Color.BLACK);
            buttonPhoto.setVisibility(View.GONE);
        }

        toggleImageSize = !toggleImageSize;
    }
}

package com.dmariani.androidui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmariani.androidui.R;

/**
 * Provides basic operations with the device camera
 *
 * @author Danielle Mariani on 12/30/15.
 */
public class CameraFragment extends Fragment {

    /**
     * Constants
     */
    static final int REQUEST_IMAGE_CAPTURE = 1;

    /**
     * Views
     */
    private TextView textViewTitle;
    private ImageView imageViewPhoto;
    private Button buttonCamera;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewTitle = (TextView) getView().findViewById(R.id.textview_message);
        imageViewPhoto = (ImageView) getView().findViewById(R.id.imageview_photo);
        buttonCamera = (Button) getView().findViewById(R.id.button_camera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCameraIntent();
            }
        });

        launchCameraIntent();
    }

    private void launchCameraIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewPhoto.setImageBitmap(imageBitmap);
        }
    }
}

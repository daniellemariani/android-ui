package com.dmariani.androidui.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Danielle Mariani on 12/30/15.
 */
public class FileUtils {

    private static final String DATE_TIME_FORMAT = "yyyyMMdd_HHmmss";
    private static final String JPG_FORMAT = ".jpg";
    private static final String MP4_FORMAT = ".mp4";
    private static final String FILE_PREFIX = "DM_";
    private static final String FILE_DIRECTORY = "dm_androidui";
    private static final String IMAGE_DIRECTORY = "dm_images";
    private static final String VIDEO_DIRECTORY = "dm_videos";

    public static File getImageDirectory() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + FILE_DIRECTORY
                + File.separator + IMAGE_DIRECTORY;
        File directory = new File(path);
        directory.mkdirs();
        return directory;
    }

    public static File getVideoDirectory() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + FILE_DIRECTORY
                + File.separator + VIDEO_DIRECTORY;
        File directory = new File(path);
        directory.mkdirs();
        return directory;
    }

    public static String createImageFileName() {
        String timeStamp = new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());
        return FILE_PREFIX + timeStamp + JPG_FORMAT;
    }

    public static File createImageFile() throws IOException {
        String imageFileName = createImageFileName();
        return new File(getImageDirectory(), imageFileName);
    }

    public static String createVideoFileName() {
        String timeStamp = new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());
        return FILE_PREFIX + timeStamp + MP4_FORMAT;
    }

    public static File createVideoFile() throws IOException {
        String videoFileName = createVideoFileName();
        return new File(getVideoDirectory(), videoFileName);
    }

    public static void saveImageFile(Context context, Bitmap bitmap) throws IOException {
        File directory = getImageDirectory();
        String imageFileName = createImageFileName();
        OutputStream outputStream;
        File file = new File(directory, imageFileName);
        outputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        outputStream.flush();
        outputStream.close();
        MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
    }
}

package com.dmariani.androidui.util;

import android.util.Log;

/**
 * @author by Danielle Mariani on 12/30/15.
 */
public class LogUtils {

    private static final String TAG = "AndroidUI";

    public static void i(String message) {
        Log.i(TAG, message);
    }

    public static void e(String message) {
        Log.e(TAG, message);
    }

    public static void e(String message, Exception e) {
        Log.e(TAG, message, e);
    }

    public static void e(Exception e) {
        Log.e(TAG, "Something goes wrong", e);
    }

    public static void v(String message) {
        Log.v(TAG, message);
    }

    public static void d(String message) {
        Log.d(TAG, message);
    }
}

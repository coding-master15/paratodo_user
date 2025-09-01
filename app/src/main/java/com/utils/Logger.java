package com.utils;

import android.util.Log;

import com.paratodo.user.BuildConfig;

public class Logger {
    public static void d(String title, String content) {
        if (true || BuildConfig.DEBUG) {
            Log.d(title, content);
        }
    }

    public static void e(String title, String content) {
        if (true || BuildConfig.DEBUG) {
            Log.e(title, content);
        }
    }
}

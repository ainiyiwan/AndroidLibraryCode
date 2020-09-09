package com.zy.androidlibrarycode.lifecycle;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.1.1
 * 描    述：
 * ================================================
 */
public class TextDelegate extends Delegate {
    public TextDelegate(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TextDelegate", "TextDelegate onCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TextDelegate", "TextDelegate onPause");
    }
}

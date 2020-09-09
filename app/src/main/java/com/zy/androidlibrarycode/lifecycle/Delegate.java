package com.zy.androidlibrarycode.lifecycle;

import androidx.fragment.app.FragmentActivity;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.1.1
 * 描    述：
 * ================================================
 */
public class Delegate implements MyObserver {
    private FragmentActivity fragmentActivity;

    public Delegate(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        fragmentActivity.getLifecycle().addObserver(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }
}

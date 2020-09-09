package com.zy.androidlibrarycode.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.1.1
 * 描    述：
 * ================================================
 */
public interface MyObserver extends LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause();
}

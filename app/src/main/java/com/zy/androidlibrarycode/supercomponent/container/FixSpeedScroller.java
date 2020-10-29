package com.zy.androidlibrarycode.supercomponent.container;

import android.content.Context;
import android.os.Build;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * E-mail: liaohailong190@foxmail.com
 * Date: 2019/8/2 11:47
 * Description:
 *
 * @author liaohailong
 */
public class FixSpeedScroller extends Scroller {
    private int mDuration = 200;

    public FixSpeedScroller(Context context) {
        this(context, new AccelerateDecelerateInterpolator());
    }

    public FixSpeedScroller(Context context, Interpolator interpolator) {
        this(context, interpolator, context.getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.HONEYCOMB);
    }

    public FixSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}

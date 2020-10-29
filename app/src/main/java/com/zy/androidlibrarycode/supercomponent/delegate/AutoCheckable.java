package com.zy.androidlibrarycode.supercomponent.delegate;

import android.widget.Checkable;

/**
 * 选中状态处理
 * <p>
 * Created by waylenw on 2019-11-13.
 */
public interface AutoCheckable extends Checkable {

    int[] CHECKED_STATE_SET = {
        android.R.attr.state_checked
    };

    void setAutoCheck(boolean autoCheck);

    boolean isAutoCheck();

}

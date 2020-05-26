package com.zy.androidlibrarycode.annotation.intdef;

import java.lang.annotation.Retention;

import androidx.annotation.IntDef;

import static com.zy.androidlibrarycode.annotation.intdef.NavigationMode.NAVIGATION_MODE_LIST;
import static com.zy.androidlibrarycode.annotation.intdef.NavigationMode.NAVIGATION_MODE_STANDARD;
import static com.zy.androidlibrarycode.annotation.intdef.NavigationMode.NAVIGATION_MODE_TABS;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
@Retention(SOURCE)
@IntDef({NAVIGATION_MODE_STANDARD, NAVIGATION_MODE_LIST, NAVIGATION_MODE_TABS})
public @interface NavigationMode {
    int NAVIGATION_MODE_STANDARD = 0;
    int NAVIGATION_MODE_LIST = 1;
    int NAVIGATION_MODE_TABS = 2;
}

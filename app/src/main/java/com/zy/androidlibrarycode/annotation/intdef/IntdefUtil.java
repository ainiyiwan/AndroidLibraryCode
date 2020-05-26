package com.zy.androidlibrarycode.annotation.intdef;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
public class IntdefUtil {
    public @NavigationMode int value;

    public void setValue(@NavigationMode int a) {
        value = a;
    }

    @NavigationMode
    public int getValue() {
        return value;
    }
}

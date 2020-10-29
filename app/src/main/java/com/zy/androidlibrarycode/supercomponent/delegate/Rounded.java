package com.zy.androidlibrarycode.supercomponent.delegate;

/**
 * 圆角化
 * <p>
 * Created by waylenw on 2019-11-13.
 */
public interface Rounded {

    void setCorner(int radius);

    void setCorner(int radius, CornerType type);

}

package com.zy.androidlibrarycode.supercomponent.container.delegate;

/**
 * 宽高比例化
 * <p>
 * Created by waylenw on 2019-11-13.
 */
public interface AspectRatioed {

    void setAspectRatio(AspectRatio ratio);

    boolean setAspectRatio(float ratio);

    float getAspectRatio();

}

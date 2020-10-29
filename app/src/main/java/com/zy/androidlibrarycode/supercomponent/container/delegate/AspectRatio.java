package com.zy.androidlibrarycode.supercomponent.container.delegate;

/**
 * 常用布局比例定义
 * Created by waylenw on 2019-11-13.
 */
public enum AspectRatio {

    Ratio_1x1(1f),
    Ratio_4x3(4f / 3),
    Ratio_16x9(16f / 9),
    Ratio_9x16(9f / 16);

    public float ratio;

    public float getRatio() {
        return this.ratio;
    }

    AspectRatio(float ratio) {
        this.ratio = ratio;
    }

}
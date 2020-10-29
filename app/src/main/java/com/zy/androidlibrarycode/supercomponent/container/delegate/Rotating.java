package com.zy.androidlibrarycode.supercomponent.container.delegate;

/**
 * 旋转
 * <p>
 * Created by waylenw on 2019-11-13.
 */
public interface Rotating {

    /**
     * 开始旋转
     */
    void startRotate();

    /**
     * 停止旋转
     */
    void stopRotate();

    /**
     * 设置旋转间隔时间
     *
     * @param intervalTime 时间间隔 in ms
     */
    void setIntervalTime(int intervalTime);

    /**
     * 设置旋转间隔角度
     *
     * @param intervalDegree 间隔角度
     */
    void setIntervalDegree(float intervalDegree);

    /**
     * 设置旋转一周持续时长
     *
     * @param duration in ms
     */
    void setDuration(int duration);

    /**
     * 设置是否循环旋转
     *
     * @param smooth 是否转圈
     */
    void setSmooth(boolean smooth);

    /**
     * 设置是否顺时针旋转
     *
     * @param clockwise true 顺时针；false 逆时针
     */
    void setClockwise(boolean clockwise);

    void resetRotate();

}

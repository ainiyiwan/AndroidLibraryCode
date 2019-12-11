package com.zy.androidlibrarycode;

import android.os.Build;

public final class DeviceUtils {

    public static final String AUTOID = "AUTOID";//东大
    public static final String BASEWIN = "BASEWIN";//世麦 seuic/smartpeak
    public static final String SEUIC = "seuic";//东大
    public static final String SMARTPEAK = "smartpeak";//世麦

    /**
     * 获取PDA设备类型
     * @return
     */
    public static String getPdaType() {
        if (AUTOID.equals(getManufacturer())) {
            //如果是东大
            return SEUIC;
        } else if (BASEWIN.equals(getManufacturer())) {
            //如果是世麦
            return SMARTPEAK;
        }
        return getManufacturer();//否则返回设备型号
    }

    /**
     * Return the manufacturer of the product/hardware.
     * <p>e.g. Xiaomi</p>
     *
     * @return the manufacturer of the product/hardware
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }



}

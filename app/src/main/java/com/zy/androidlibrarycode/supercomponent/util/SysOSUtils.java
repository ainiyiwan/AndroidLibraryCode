package com.zy.androidlibrarycode.supercomponent.util;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import androidx.annotation.NonNull;

/**
 * Android系统工具类
 * <p>
 * Created by waylenw on 2019-10-22.
 */
public final class SysOSUtils {

    private SysOSUtils() {
    }

    private static final String SYS_EMUI = "sys_emui";
    private static final String SYS_MIUI = "sys_miui";
    private static final String SYS_FLYME = "sys_flyme";
    private static final String SYS_QIKU = "sys_qihu";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";

    // 系统
    private static String SYSUI;

    private static Application app;

    /**
     * 初始化
     *
     * @param application
     */
    public static void init(@NonNull Application application) {
        app = application;
        initSystem();
    }

    private static void initSystem() {
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
                if (!TextUtils.isEmpty(SystemPropertiesProxy.get(app, KEY_MIUI_VERSION_CODE, ""))
                    || !TextUtils.isEmpty(SystemPropertiesProxy.get(app, KEY_MIUI_VERSION_NAME, ""))
                    || !TextUtils.isEmpty(SystemPropertiesProxy.get(app, KEY_MIUI_INTERNAL_STORAGE, ""))) {
                    SYSUI = SYS_MIUI;//小米
                } else if (!TextUtils.isEmpty(SystemPropertiesProxy.get(app, KEY_EMUI_API_LEVEL, ""))
                    || !TextUtils.isEmpty(SystemPropertiesProxy.get(app, KEY_EMUI_VERSION, ""))
                    || !TextUtils.isEmpty(SystemPropertiesProxy.get(app, KEY_EMUI_CONFIG_HW_SYS_VERSION, ""))) {
                    SYSUI = SYS_EMUI;//华为
                } else if (getMeizuFlymeOSFlag().toLowerCase().contains("flyme")) {
                    SYSUI = SYS_FLYME;//魅族
                }
            } else {
                Properties prop = new Properties();
                prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
                if (prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null) {
                    SYSUI = SYS_MIUI;//小米
                } else if (prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                    || prop.getProperty(KEY_EMUI_VERSION, null) != null
                    || prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null) {
                    SYSUI = SYS_EMUI;//华为
                } else if (getMeizuFlymeOSFlag().toLowerCase().contains("flyme")) {
                    SYSUI = SYS_FLYME;//魅族
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getMeizuFlymeOSFlag() {
        return SystemPropertiesProxy.get(app, "ro.build.display.id", "");
    }

    /**
     * 判断是否是MIUI系统
     *
     * @return true 是小米平台
     */
    public static boolean isMIUI() {
        return SYS_MIUI.equals(SYSUI);
    }

    /**
     * 是否oppo手机
     */
    public static boolean isOPPO() {
        String model = Build.BRAND;
        return model.equalsIgnoreCase("oppo");
    }

    /**
     * 是否是Flyme系统
     *
     * @return
     */
    public static boolean isFlyme() {
        return SYS_FLYME.equals(SYSUI);
    }

    public static boolean is360() {
        return "QIKU".equalsIgnoreCase(Build.MANUFACTURER);
    }

    /**
     * 判断锤子手机
     *
     * @return
     */
    public static boolean isSmartOs() {
        String brand = Build.BRAND;
        return "smartisan".equalsIgnoreCase(brand);
    }

    /**
     * 判断华为手机
     *
     * @return
     */
    public static boolean isEMUI() {
        return SYS_EMUI.equals(SYSUI);
    }

    /**
     * 判断红米2A
     *
     * @return
     */
    public static boolean isHM2A() {
        String model = Build.MODEL;

        return "HM 2A".equalsIgnoreCase(model);
    }

    /**
     * 判断小米5C
     *
     * @return
     */
    public static boolean isXM5C() {
        String model = Build.MODEL;

        return "MI 5C".equalsIgnoreCase(model);
    }


    /**
     * 判断是否是蓝绿两厂
     *
     * @return
     */
    public static boolean isOPPOVIVO() {
        String model = Build.MODEL;
        return model.startsWith("OPPO") || isVIVO();
    }

    public static boolean isVIVO() {
        String model = Build.MODEL;
        return model.startsWith("vivo") || model.contains("VIVO");
    }


    /**
     * 判断是否是蓝绿两厂
     *
     * @return
     */
    public static boolean isOPPOR15() {
        return "PACM00".equalsIgnoreCase(Build.MODEL);
    }


    /**
     * 判断乐视手机
     *
     * @return
     */
    public static boolean isLeTv() {
        String model = Build.MODEL;
        return model.startsWith("Le");
    }


    /**
     * 判断红米note3
     *
     * @return
     */
    public static boolean isREDMINOTE3() {
        String model = Build.MODEL;
        return "REDMI NOTE 3".equalsIgnoreCase(model);
    }


    /**
     * 判断华为p8
     *
     * @return
     */
    public static boolean ALE_TL00() {
        String model = Build.MODEL;

        return "ALE-TL00".equalsIgnoreCase(model);
    }

    /**
     * 判断红米
     *
     * @return
     */
    public static boolean isHM() {
        String model = Build.MODEL;

        return model.startsWith("HM");
    }

    /**
     * 判断三星
     *
     * @return
     */
    public static boolean isSAMSUNG() {
        String model = Build.MODEL;
        return model.startsWith("SM");
    }

    /**
     * 判断天语
     *
     * @return
     */
    public static boolean isKTouch() {
        String model = Build.MODEL;

        return model.startsWith("K-Touch");
    }


    /**
     * 判断vivo x5l
     *
     * @return
     */
    public static boolean isVivoX5L() {
        String model = Build.MODEL;

        return "vivo X5L".equalsIgnoreCase(model);
    }

    /**
     * 判断是否Vivo X9
     *
     * @return
     */
    public static boolean isVivoX9() {
        String model = Build.MODEL;
        return "vivo X9".equalsIgnoreCase(model);
    }


    public static int getTargetSDK(@NonNull Context context) {
        return context.getApplicationInfo().targetSdkVersion;
    }

    public static boolean isTargetUpper6x(@NonNull Context context) {
        return getTargetSDK(context) >= Build.VERSION_CODES.M;
    }

    public static boolean isTargetUpper5x(@NonNull Context context) {
        return getTargetSDK(context) >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isTargetUpper7x(@NonNull Context context) {
        return getTargetSDK(context) >= Build.VERSION_CODES.N;
    }

    public static boolean isTargetUpper8x(@NonNull Context context) {
        return getTargetSDK(context) >= Build.VERSION_CODES.O;
    }

    public static boolean isUpper6x() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isUpper5x() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isUpper7x() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isUpper8x() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }
    public static boolean isUpper9x() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }



    /**
     * 系统是否大于4.4
     */
    public static boolean isUpperKitkat() {
        return Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT;
    }

    /**
     * 系统版本大于 3.1
     *
     * @return
     */
    public static boolean isUpperHoneycombMR1() {
        return Build.VERSION_CODES.HONEYCOMB_MR1 <= Build.VERSION.SDK_INT;
    }

    /**
     * 系统版本大于 3.x
     */
    public static boolean isUpperHoneycomb() {
        return Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT;
    }

    /**
     * 系统版本大于 3.x
     */
    public static boolean isUpper3x() {
        return Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT;
    }

}

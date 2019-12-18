package com.zy.serviceimpl.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：
 * ================================================
 */
public class DeviceUtil {

    /**
     * 是否可以发送短信
     */
    public static boolean canSendSms(Context context) {
        Uri smsUri = Uri.parse("smsto:");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, smsUri);
        PackageManager smsPackageManager = context.getPackageManager();
        List<ResolveInfo> smsResolveInfo = smsPackageManager.queryIntentActivities(smsIntent, 0);
        return smsResolveInfo.size() > 0;
    }

    /**
     * 发送短信
     * @param tel 电话号码
     * @param msgContent 短信内容
     */
    public static void sendSms(Context context, String tel, String msgContent) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:" + tel));
        smsIntent.putExtra("sms_body", msgContent);//sms_body 不能改
        context.startActivity(smsIntent);
    }

    /**
     * 是否可以打电话
     */
    public static boolean canCallPhone(Context context) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
        PackageManager phonePackageManager = context.getPackageManager();
        List<ResolveInfo> phoneResolveInfo = phonePackageManager.queryIntentActivities(callIntent, 0);
        return phoneResolveInfo.size() > 0;
    }

    /**
     * 打电话
     * @param phoneUmber 电话号码
     */
    public static void callPhone(Context context, String phoneUmber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneUmber));
        context.startActivity(callIntent);
    }

    public static boolean viewUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            return true;
        }
        return false;
    }
}

package com.zy.androidlibrarycode.parcel;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.zy.androidlibrarycode.App;


/**
 * Created by Villey on 2017/3/27.
 */

public class ToastUtil {

    private static final String TAG = "ToastUtil";

    private static Toast mToast;
    private static Handler mH = new Handler(Looper.getMainLooper());

    public ToastUtil() {
        if (mToast == null) {
            mToast = new Toast(App.getInstance());
        }
    }

    public static void toast(CharSequence msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        mH.post(() -> {
            mToast.cancel();
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.show();
        });
    }

    public static void toast(int msgId) {
        toast(App.getInstance().getResources().getText(msgId));
    }


    @Deprecated
    public static void toast(Context context, String msg) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public static void toast(Context context, int msgId) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, msgId, Toast.LENGTH_SHORT).show();
    }


}

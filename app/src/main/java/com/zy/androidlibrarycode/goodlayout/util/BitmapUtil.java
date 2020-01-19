package com.zy.androidlibrarycode.goodlayout.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;


/**
 * 位图处理工具类
 * <p>
 * Created by waylenw on 2019-11-11.
 */
public final class BitmapUtil {

    private static final String TAG = "BitmapUtil";

    /**
     * 获取一个View的Bitmap
     *
     * @param view 要截图的控件
     */
    public static Bitmap getViewBitmap(View view) {
        // bugly反馈, java.lang.IllegalArgumentException  width and height must be > 0
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(
            view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        canvas.save(); // 保存画布
        return bitmap;
    }


}

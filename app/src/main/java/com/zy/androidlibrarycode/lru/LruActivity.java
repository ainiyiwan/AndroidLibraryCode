package com.zy.androidlibrarycode.lru;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class LruActivity extends AppCompatActivity {

    private LruCache<String, Bitmap> mMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru);

        // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
        // LruCache通过构造函数传入缓存值，以KB为单位。
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 使用最大可用内存值的1/8作为缓存的大小。
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                return bitmap.getByteCount() / 1024;
            }
        };

    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }


    public void load(View view) {
//        ((ImageView) view).setImageBitmap(
//                decodeSampledBitmapFromResource(getResources(), R.mipmap.baidu, 100, 100));
        startActivity(new Intent(LruActivity.this, DiskLruActivity.class));
    }


    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}

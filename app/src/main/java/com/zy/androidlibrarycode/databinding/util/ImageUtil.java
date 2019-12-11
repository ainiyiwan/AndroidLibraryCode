package com.zy.androidlibrarycode.databinding.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zy.androidlibrarycode.App;

import androidx.databinding.BindingAdapter;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：
 * ================================================
 */
public class ImageUtil {
    @BindingAdapter("url")
    public static void setImageUri(ImageView view, String url) {
        Glide.with(App.getInstance())
                .load(url)
                .into(view);
    }
}

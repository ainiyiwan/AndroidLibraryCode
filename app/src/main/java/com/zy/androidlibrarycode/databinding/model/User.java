package com.zy.androidlibrarycode.databinding.model;

import android.net.Uri;

import lombok.Builder;
import lombok.Data;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：
 * ================================================
 */
@Data
@Builder
public class User {
    private String title;
    private String subTitle;
    private Uri drawableRes;
}

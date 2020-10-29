package com.zy.androidlibrarycode.supercomponent.container.text;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;


import androidx.annotation.DrawableRes;
import androidx.core.app.ActivityCompat;

/**
 * 扩展TextView的一些用法
 * <p>
 * Created by waylenw on 2018/10/29.
 */
public class SuperTextView extends RoundedTextView {
    public SuperTextView(Context context) {
        this(context, null);
    }

    public SuperTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setIncludeFontPadding(false);
    }


    public void setLeftDrawable(@DrawableRes int drawableRes) {
        setDrawable(drawableRes, 0, 0, 0);
    }

    public void setLeftDrawable(Drawable drawable) {
        setDrawable(drawable, null, null, null);
    }


    public void setTopDrawable(@DrawableRes int drawableRes) {
        setDrawable(0, drawableRes, 0, 0);
    }

    public void setTopDrawable(Drawable drawable) {
        setDrawable(null, drawable, null, null);
    }

    public void setRightDrawable(@DrawableRes int drawableRes) {
        setDrawable(0, 0, drawableRes, 0);
    }

    public void setRightDrawable(Drawable drawable) {
        setDrawable(null, null, drawable, null);
    }


    public void setBottomDrawable(@DrawableRes int drawableRes) {
        setDrawable(0, 0, 0, drawableRes);
    }

    public void setBottomDrawable(Drawable drawable) {
        setDrawable(null, null, null, drawable);
    }

    public void setDrawable(@DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom) {
        Drawable leftDrawable = null, topDrawable = null, rightDrawable = null, bottomDrawable = null;
        if (left != 0) {
            leftDrawable = getDrawable(left);
        }
        if (top != 0) {
            topDrawable = getDrawable(top);
        }
        if (right != 0) {
            rightDrawable = getDrawable(right);
        }
        if (bottom != 0) {
            bottomDrawable = getDrawable(bottom);
        }
        setDrawable(leftDrawable, topDrawable, rightDrawable, bottomDrawable);
    }

    public void setDrawable(Drawable leftDrawable, Drawable topDrawable, Drawable rightDrawable, Drawable bottomDrawable) {
        setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottomDrawable);
    }


    public Drawable getDrawable(@DrawableRes int drawableRes) {
        Drawable drawable = ActivityCompat.getDrawable(getContext(), drawableRes);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        return drawable;

    }

    /**
     * 设置删除线
     */
    public void setStyleStrikeLine() {
        getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    /**
     * 设置粗体
     */
    public void setStyleTextBold() {
        getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
    }

}

package com.zy.androidlibrarycode.goodlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.goodlayout.bean.Good;
import com.zy.androidlibrarycode.goodlayout.util.BitmapUtil;
import com.zy.androidlibrarycode.goodlayout.util.ScreenUtils;

import androidx.annotation.Nullable;

public class GoodsNameLayout extends LinearLayout {

    private LinearLayout leftLevelLayout;
    private TextView leftLevelTv;
    private TextView nameTv;
    private int nameSpecTextSize;

    public GoodsNameLayout(Context context) {
        this(context, null);
    }

    public GoodsNameLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoodsNameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_goods_level, this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GoodsNameLayout, defStyleAttr, 0);
        nameSpecTextSize = typedArray.getDimensionPixelSize(R.styleable.GoodsNameLayout_name_spec_size, ScreenUtils.dp2px(context, 11));
        typedArray.recycle();

        leftLevelLayout = findViewById(R.id.ll_goods_level);
        leftLevelTv = findViewById(R.id.tv_goods_level);
        nameTv = findViewById(R.id.tv_good_name_spec);

        float nameSpecTextSizePx = ScreenUtils.px2dp(nameSpecTextSize);
        nameTv.setTextSize(nameSpecTextSizePx);

        // leftLevelLayout.setLayoutParams(new RelativeLayout.LayoutParams(
        //     RelativeLayout.LayoutParams.WRAP_CONTENT,
        //     nameSpecTextSize - DensityUtil.dp2px(1)
        // ));
    }

    public void update(Good good) {

        String goodsName = good.goodsName;

        String goodLevel = good.level;
        if (TextUtils.isEmpty(goodLevel)) {
            nameTv.setText(goodsName);
            return;
        }

        // 设置左边等级图片中的文本内容
        leftLevelTv.setText(goodLevel);

        leftLevelTv.post(() -> {
            // 将左边等级图片的父容器整个生成一个Bitmap
            Bitmap cacheBitmap = BitmapUtil.getViewBitmap(leftLevelLayout);
            if (cacheBitmap == null || cacheBitmap.isRecycled()) {
                nameTv.setText(goodsName);
                return;
            }

            // 生成左边等级图片的Drawable
            Drawable leftLevelDrawable = new BitmapDrawable(cacheBitmap);
            leftLevelDrawable.setBounds(0, 0, cacheBitmap.getWidth(), cacheBitmap.getHeight());

            // 将Drawable和文本内容拼接在一起
            SpannableString spannableString = new SpannableString("TAG" + " " + goodsName);
            spannableString.setSpan(new ImageSpan(leftLevelDrawable, DynamicDrawableSpan.ALIGN_BOTTOM), 0, "TAG".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            nameTv.setText(spannableString);
        });
    }
}

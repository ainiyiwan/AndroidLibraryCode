package com.zy.androidlibrarycode.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zy.androidlibrarycode.R;

import androidx.annotation.Nullable;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.2.0
 * 描    述：特价限购View
 *
 * 需求：特价价格标签上展示「限X份」，其中X份为每单限购数量，如未配置每单限购数量则不展示。
 * "effectGoodsNumber" // 每单限购商品数量 - V3.6.1
 * ================================================
 */
public class SpecialPriceLimitedView extends LinearLayout {
    private TextView mTvLabel;
    private TextView mTvLimit;

    public SpecialPriceLimitedView(Context context) {
        this(context, null);
    }

    public SpecialPriceLimitedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpecialPriceLimitedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_special_price_limited, this, true);
        mTvLabel = view.findViewById(R.id.tv_label);
        mTvLimit = view.findViewById(R.id.tv_limit);
    }

    public void setGone() {
        mTvLimit.setVisibility(GONE);
        setVisibility(GONE);
    }

}

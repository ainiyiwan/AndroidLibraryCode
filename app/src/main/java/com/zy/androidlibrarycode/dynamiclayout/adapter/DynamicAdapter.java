package com.zy.androidlibrarycode.dynamiclayout.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.dynamiclayout.bean.BottomBean;

import androidx.annotation.NonNull;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：
 * ================================================
 */
public class DynamicAdapter extends BaseQuickAdapter<BottomBean, BaseViewHolder> {
    public DynamicAdapter() {
        super(R.layout.item_order_detail_bottom, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BottomBean item) {
        helper.setText(R.id.tv_order_detail_bottom_desc, item.leftText)
                .setText(R.id.tv_order_detail_bottom_data, item.position + "")
                .setText(R.id.btn_order_detail_copy_desc, item.rightText);
    }
}

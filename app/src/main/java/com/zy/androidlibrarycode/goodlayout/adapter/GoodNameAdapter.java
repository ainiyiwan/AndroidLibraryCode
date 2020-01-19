package com.zy.androidlibrarycode.goodlayout.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.goodlayout.bean.Good;
import com.zy.androidlibrarycode.goodlayout.widget.GoodsNameLayout;

import androidx.annotation.NonNull;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.8.0
 * 描    述：
 * ================================================
 */
public class GoodNameAdapter extends BaseQuickAdapter<Good, BaseViewHolder> {
    public GoodNameAdapter() {
        super(R.layout.item_good_layout, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Good item) {
        ((GoodsNameLayout) helper.getView(R.id.tv_goods_detail_goods_name)).update(item);
    }
}

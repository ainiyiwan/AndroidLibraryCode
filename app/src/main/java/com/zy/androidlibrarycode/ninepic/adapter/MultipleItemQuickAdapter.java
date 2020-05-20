package com.zy.androidlibrarycode.ninepic.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.ninepic.entity.MultipleItem;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter() {
        super(null);
        addItemType(MultipleItem.ONE, R.layout.item_image_view_one);
        addItemType(MultipleItem.TWO, R.layout.item_img_view_two);
        addItemType(MultipleItem.THREE, R.layout.item_img_view_three);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {

    }

}

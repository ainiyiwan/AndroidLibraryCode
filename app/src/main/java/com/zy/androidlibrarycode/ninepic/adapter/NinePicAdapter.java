package com.zy.androidlibrarycode.ninepic.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.ninepic.NinePicRecyclerView;
import com.zy.androidlibrarycode.ninepic.entity.NinePicEntity;

import androidx.annotation.NonNull;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
public class NinePicAdapter extends BaseQuickAdapter<NinePicEntity, BaseViewHolder> {
    public NinePicAdapter() {
        super(R.layout.item_nine_pic_parent, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, NinePicEntity ninePicEntity) {
        ((NinePicRecyclerView) baseViewHolder.getView(R.id.nine_pic_recycler)).setData(ninePicEntity.getList());
    }
}

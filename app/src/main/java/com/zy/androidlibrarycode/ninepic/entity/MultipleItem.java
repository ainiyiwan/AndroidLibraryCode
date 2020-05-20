package com.zy.androidlibrarycode.ninepic.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
public class MultipleItem implements MultiItemEntity {
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    private int itemType;

    public MultipleItem() {
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}

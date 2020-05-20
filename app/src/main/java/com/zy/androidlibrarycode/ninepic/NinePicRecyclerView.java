package com.zy.androidlibrarycode.ninepic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.ninepic.adapter.MultipleItemQuickAdapter;
import com.zy.androidlibrarycode.ninepic.decoration.MyDecorationTwo;
import com.zy.androidlibrarycode.ninepic.entity.MultipleItem;
import com.zy.serviceimpl.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：商品信息
 * ================================================
 */
public class NinePicRecyclerView extends LinearLayout {
    private RecyclerView mRecyclerView;
    private MultipleItemQuickAdapter multipleItemAdapter;
    private GridLayoutManager manager;

    public NinePicRecyclerView(Context context) {
        this(context, null);
    }

    public NinePicRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NinePicRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_nine_pic, this, true);
        mRecyclerView = view.findViewById(R.id.rv_list);
        multipleItemAdapter = new MultipleItemQuickAdapter();
        manager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.addItemDecoration(new MyDecorationTwo(getContext()));
//        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
//                return data.get(position).getSpanSize();
//            }
//        });
        mRecyclerView.setAdapter(multipleItemAdapter);
    }

    public void setData(List<String> data) {
        if (ObjectUtils.isEmpty(data)) {
            return;
        }
        List<MultipleItem> list = getDataAndSetSpanCount(data.size());;
        mRecyclerView.setLayoutManager(manager);
        multipleItemAdapter.setNewData(list);
    }

    private List<MultipleItem> getDataAndSetSpanCount(int size) {
        List<MultipleItem> list = new ArrayList<>();
        MultipleItem ninePicEntity = new MultipleItem();

        if (size == 1) {
            manager.setSpanCount(1);
            ninePicEntity.setItemType(MultipleItem.ONE);
            list.add(ninePicEntity);
            return list;
        } else if (size == 2 || size == 4) {
            manager.setSpanCount(2);
            for (int i = 0; i < size; i++) {
                ninePicEntity.setItemType(MultipleItem.TWO);
                list.add(ninePicEntity);
            }
        } else {
            //不处理9张以上的图片
            manager.setSpanCount(3);
            for (int i = 0; i < Math.min(size, 9); i++) {
                ninePicEntity.setItemType(MultipleItem.THREE);
                list.add(ninePicEntity);
            }
        }

        return list;
    }

}

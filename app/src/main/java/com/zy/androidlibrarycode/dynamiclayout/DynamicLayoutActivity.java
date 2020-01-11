package com.zy.androidlibrarycode.dynamiclayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.dynamiclayout.bean.BottomBean;
import com.zy.serviceimpl.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.zy.androidlibrarycode.dynamiclayout.bean.BottomBeanConstant.COUPON;
import static com.zy.androidlibrarycode.dynamiclayout.bean.BottomBeanConstant.NAME;
import static com.zy.androidlibrarycode.dynamiclayout.bean.BottomBeanConstant.PRICE;
import static com.zy.androidlibrarycode.dynamiclayout.bean.BottomBeanConstant.TREE;

/**
 * 动态添加View，通过ViewGroup自己控制，比较复杂，容易出错
 */
public class DynamicLayoutActivity extends AppCompatActivity {
    public static final String TAG = DynamicLayoutActivity.class.getSimpleName();

    private ViewGroup mOrderNumberContainer;
    private List<BottomBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_layout);
        mOrderNumberContainer = findViewById(R.id.ll_dynamic);
        list = new ArrayList<>();
        fillData();
    }

    private void fillData() {
        BottomBean bottomBean = new BottomBean("价格", "100", PRICE);
        BottomBean bottomBean1 = new BottomBean("优惠券", "100", COUPON);
        BottomBean bottomBean2 = new BottomBean("姓名", "100", NAME);
        list.add(bottomBean1);
        list.add(bottomBean);
        list.add(bottomBean2);
        Collections.sort(list);
        Log.d(TAG, list.toString());
        setData();
    }

    public void setData() {
        if (list.isEmpty()) {
            mOrderNumberContainer.setVisibility(GONE);
        } else {
            mOrderNumberContainer.setVisibility(VISIBLE);
            int size = list.size();
            resizeChildCount(mOrderNumberContainer, size, R.layout.item_order_detail_bottom);
            for (int i = 0; i < size; i++) {
                View bottomView = mOrderNumberContainer.getChildAt(i);
                BottomBean bottomBean = list.get(i);
                TextView descTxt = bottomView.findViewById(R.id.tv_order_detail_bottom_desc);
                TextView positionText = bottomView.findViewById(R.id.tv_order_detail_bottom_data);
                TextView copyTxt = bottomView.findViewById(R.id.btn_order_detail_copy_desc);
                descTxt.setText(bottomBean.leftText);
                positionText.setText(bottomBean.position + "");
                copyTxt.setText(bottomBean.rightText);
            }
        }
    }

    /**
     * 给ViewGroup根据数量填充ChildView
     *
     * @param viewGroup   父容器
     * @param newCount    目标数量
     * @param childLayout 子视图布局id
     */
    public void resizeChildCount(@NonNull ViewGroup viewGroup, int newCount,
                                 @LayoutRes int childLayout) {
        int childCount = viewGroup.getChildCount();
        int inflateCount = newCount - childCount;
        if (inflateCount >= 0) {
            for (int i = 0; i < inflateCount; i++) {
                LayoutInflater.from(viewGroup.getContext()).inflate(childLayout, viewGroup, true);
            }
        } else {
            for (int i = childCount - 1; i > newCount - 1; i--) {
                viewGroup.removeViewAt(i);
            }
        }
    }

    public void add(View view) {
        BottomBean bottomBean = new BottomBean("价格1", "100", TREE);
        if (!isContainData(bottomBean.position)) {
            list.add(bottomBean);
            Collections.sort(list);

            int position = findPosition(bottomBean.position);
            if (position != -1) {
                addData(position);
            }
        }
    }

    public void del(View view) {
        if (isContainData(TREE)) {
            int position = findPosition(TREE);
            if (position != -1) {
                list.remove(position);
                Collections.sort(list);

                delData(position);
            }
        }
    }

    /**
     * @param position 要添加数据的位置
     */
    public void addData(int position) {
        int size = list.size();
        resizeChildCount(mOrderNumberContainer, size, R.layout.item_order_detail_bottom);

        View bottomView = mOrderNumberContainer.getChildAt(position);
        BottomBean bottomBean = list.get(position);
        TextView descTxt = bottomView.findViewById(R.id.tv_order_detail_bottom_desc);
        TextView positionText = bottomView.findViewById(R.id.tv_order_detail_bottom_data);
        TextView copyTxt = bottomView.findViewById(R.id.btn_order_detail_copy_desc);
        descTxt.setText(bottomBean.leftText);
        positionText.setText(bottomBean.position + "");
        copyTxt.setText(bottomBean.rightText);
    }

    public void delData(int position) {
        mOrderNumberContainer.removeViewAt(position);
    }

    /**
     * 现有数据是否包含该数据
     */
    public boolean isContainData(int position) {
        if (ObjectUtils.isEmpty(list)) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (position == list.get(i).position) {
                return true;
            }
        }
        return false;
    }

    private int findPosition(int position) {
        if (ObjectUtils.isEmpty(list)) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (position == list.get(i).position) {
                return i;
            }
        }
        return -1;
    }
}

package com.zy.androidlibrarycode.dynamiclayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.dynamiclayout.adapter.DynamicAdapter;
import com.zy.androidlibrarycode.dynamiclayout.bean.BottomBean;
import com.zy.serviceimpl.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.zy.androidlibrarycode.dynamiclayout.bean.BottomBeanConstant.COUPON;
import static com.zy.androidlibrarycode.dynamiclayout.bean.BottomBeanConstant.NAME;
import static com.zy.androidlibrarycode.dynamiclayout.bean.BottomBeanConstant.PRICE;

public class DynamicRecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DynamicAdapter mAdapter;
    private List<BottomBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_recycler);
        mRecyclerView = findViewById(R.id.recycler);

        list = new ArrayList<>();
        mAdapter = new DynamicAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addFooterView(getFooterView());

        fillData();
    }

    private void fillData() {
        BottomBean bottomBean = new BottomBean("价格", "100", PRICE);
        BottomBean bottomBean1 = new BottomBean("优惠券", "100", COUPON);
        BottomBean bottomBean2 = new BottomBean("姓名", "100", NAME);
        list.add(bottomBean1);
        list.add(bottomBean);
        list.add(bottomBean2);
        setNewData();
    }

    private View getFooterView() {
        View view = getLayoutInflater().inflate(R.layout.item_dynamic_fotter, (ViewGroup) mRecyclerView.getParent(), false);
        view.findViewById(R.id.btn_add).setOnClickListener(v -> {
            addData();
        });

        view.findViewById(R.id.btn_del).setOnClickListener(v -> {
            delData();
        });
        return view;
    }

    private void addData() {
        int random = new Random().nextInt(100);
        BottomBean bottomBean = new BottomBean("价格1", "100", random);
        if (!isContainData(bottomBean.position)) {
            list.add(bottomBean);
            setNewData();
        }
    }

    private void setNewData() {
        Collections.sort(list);
        mAdapter.setNewData(list);
    }

    private void delData() {
        int random = new Random().nextInt(100);
        if (isContainData(random)) {
            int position = findPosition(random);
            if (position != -1) {
                list.remove(position);
                setNewData();
            }
        }
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

    /**
     * 找到该数据在列表中的位置
     */
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

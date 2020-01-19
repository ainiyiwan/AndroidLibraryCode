package com.zy.androidlibrarycode.goodlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.fdleak.FDLeakSecondActivity;
import com.zy.androidlibrarycode.goodlayout.adapter.GoodNameAdapter;
import com.zy.androidlibrarycode.goodlayout.bean.EvaluationScoreBean;
import com.zy.androidlibrarycode.goodlayout.bean.Good;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GoodsNameActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private GoodNameAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_name);
        mRecyclerView = findViewById(R.id.recycler);
        mAdapter = new GoodNameAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mAdapter);


//        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EvaluationScoreBean bean = new EvaluationScoreBean();
        EvaluationScoreBean.CityCommentAvg cityCommentAvg = bean.getCityCommentAvg();
        String ste = cityCommentAvg.getOverallStarAvg();
        String ste1 = cityCommentAvg.getOverallStarAvg();
    }

    private void initData() {
        List<Good> list = new ArrayList<>();
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("CC", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("dd", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("CC", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("dd", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));
        list.add(new Good("AA", "哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或"));


        mAdapter.setNewData(list);
    }

    public void jump(View view) {
        startActivity(new Intent(this, FDLeakSecondActivity.class));
    }
}

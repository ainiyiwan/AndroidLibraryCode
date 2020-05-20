package com.zy.androidlibrarycode.ninepic;

import android.os.Bundle;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.ninepic.adapter.NinePicAdapter;
import com.zy.androidlibrarycode.ninepic.entity.NinePicEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NinePicActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NinePicAdapter mAdapter;
    private List<NinePicEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_pic);
        mRecyclerView = findViewById(R.id.recycler);

        list = new ArrayList<>();
        mAdapter = new NinePicAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        fillData();
    }

    private void fillData() {
        for (int i = 1; i < 100; i++) {
            NinePicEntity ninePicEntity = new NinePicEntity();
            ninePicEntity.setList(getList(i));
            list.add(ninePicEntity);
        }
        mAdapter.setNewData(list);
    }

    private List<String> getList(int i) {
        List<String> list = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            list.add("");
        }
        return list;
    }
}

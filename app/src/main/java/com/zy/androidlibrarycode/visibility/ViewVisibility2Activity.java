package com.zy.androidlibrarycode.visibility;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.databinding.ActivityViewVisibility2Binding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ViewVisibility2Activity extends AppCompatActivity {
    private static final String TAG = ViewVisibilityActivity.class.getSimpleName();

    private int buttonTop;
    private ActivityViewVisibility2Binding binding;

    MyNestedScrollView.OnScrollListener onScrollListener = new MyNestedScrollView.OnScrollListener() {
        @Override
        public void onScroll(int scrollY) {
            if (scrollY < buttonTop) {
                binding.imageView.setVisibility(View.VISIBLE);
                Log.e(TAG, "condition3 scrollY = " + scrollY +" buttonTop = " + buttonTop);
            }

            if (scrollY > buttonTop) {
                binding.imageView.setVisibility(View.GONE);
                Log.e(TAG, "condition4 scrollY = " + scrollY +" buttonTop = " + buttonTop);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_visibility2);
        binding.nestedScrollView.setOnScrollListener(onScrollListener);
        binding.btn.post(new Runnable() {
            @Override
            public void run() {
//                buttonTop = binding.btn.getTop();
                buttonTop = 10;
            }
        });
    }
}

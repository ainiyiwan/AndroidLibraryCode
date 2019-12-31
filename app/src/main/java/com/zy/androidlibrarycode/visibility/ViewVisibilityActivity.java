package com.zy.androidlibrarycode.visibility;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.databinding.ActivityViewVisibilityBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ViewVisibilityActivity extends AppCompatActivity {
    private static final String TAG = ViewVisibilityActivity.class.getSimpleName();

    private int buttonTop;
    private ActivityViewVisibilityBinding binding;

    MyNestedScrollView.OnScrollListener onScrollListener = new MyNestedScrollView.OnScrollListener() {
        @Override
        public void onScroll(int scrollY) {
            if (binding.imageView.getVisibility() == View.GONE && scrollY < buttonTop) {
                Log.e(TAG, "condition1 scrollY = " + scrollY +" buttonTop = " + buttonTop);
                return;
            }
            if (binding.imageView.getVisibility() == View.VISIBLE && scrollY > buttonTop) {
                Log.e(TAG, "condition2 scrollY = " + scrollY +" buttonTop = " + buttonTop);
                return;
            }

            if (scrollY < buttonTop) {
                binding.imageView.setVisibility(View.GONE);
                Log.e(TAG, "condition3 scrollY = " + scrollY +" buttonTop = " + buttonTop);
            }

            if (scrollY > buttonTop) {
                binding.imageView.setVisibility(View.VISIBLE);
                Log.e(TAG, "condition4 scrollY = " + scrollY +" buttonTop = " + buttonTop);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_visibility);
        binding.nestedScrollView.setOnScrollListener(onScrollListener);
        binding.btn.post(new Runnable() {
            @Override
            public void run() {
//                buttonTop = binding.btn.getTop();
                buttonTop = 10;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Rect mReact = new Rect();
//        binding.nestedScrollView.getHitRect(mReact);
//        if (binding.btn.getLocalVisibleRect(mReact)) {
//            // visible
//            Log.e("ViewVisibilityActivity", "visible");
//        } else {
//            // invisible
//            Log.e("ViewVisibilityActivity", "invisible");
//        }
    }
}

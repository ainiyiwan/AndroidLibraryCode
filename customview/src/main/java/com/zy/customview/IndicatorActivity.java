package com.zy.customview;

import android.os.Bundle;

import com.zy.customview.base.BaseFragmentAdapter;
import com.zy.customview.databinding.ActivityIndicatorBinding;
import com.zy.customview.fragment.IndicatorFragment;
import com.zy.customview.widget.IndicatorLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class IndicatorActivity extends AppCompatActivity implements IndicatorLayout.OnIndicatorChangeListener {

    private String[] mTabs = {"今日任务", "历史任务"};
    private ActivityIndicatorBinding activityIndicatorBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityIndicatorBinding = DataBindingUtil.setContentView(this, R.layout.activity_indicator);

       activityIndicatorBinding.tabIndicator.setVisiableTabCount(mTabs.length);
       activityIndicatorBinding.tabIndicator.setTabs(mTabs);
       activityIndicatorBinding.tabIndicator.setOnIndicatorChangeListener(this);
       activityIndicatorBinding.tabIndicator.setViewPager(activityIndicatorBinding.vpTaskContainer);
       activityIndicatorBinding.vpTaskContainer.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), getFragments()));
    }

    private List<? extends Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            IndicatorFragment indicatorFragment = new IndicatorFragment();
            list.add(indicatorFragment);
        }
        return list;
    }

    @Override
    public void onChanged(int index) {
        activityIndicatorBinding.vpTaskContainer.setCurrentItem(index);
    }
}

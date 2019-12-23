package com.zy.customview.base;


import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author nayuta
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {

    protected List<Fragment> fragmentList = new ArrayList<>();
    protected List<String> mTitles;

    public BaseFragmentAdapter(FragmentManager fm, List<? extends Fragment> fragmentList) {
        super(fm);
        this.fragmentList = new ArrayList<>(fragmentList);
    }

    public BaseFragmentAdapter(FragmentManager fm, List<? extends Fragment> fragmentList, List<String> mTitles) {
        super(fm);
        this.fragmentList = new ArrayList<>(fragmentList);
        this.mTitles = mTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return !(mTitles != null || mTitles.size() > 0) ? mTitles.get(position) : "";
    }

    @Override
    public Fragment getItem(int position) {
        if (fragmentList.isEmpty()){
            return null;
        }
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}

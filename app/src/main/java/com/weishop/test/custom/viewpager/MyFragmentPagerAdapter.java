package com.weishop.test.custom.viewpager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by wangyongchao on 2017/9/20.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> mFragments = new ArrayList<>();

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        mFragments.addAll(fragments);
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println("getItem="+position);
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}

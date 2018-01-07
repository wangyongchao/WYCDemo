package com.weishop.test.fragment.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangyongchao on 2017/9/20.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private View mViews[];

    public MyViewPagerAdapter(View views[]) {
        mViews = views;
    }

    @Override
    public int getCount() {
        return mViews.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
        System.out.println("startUpdate");
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
        System.out.println("finishUpdate");
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        System.out.println("instantiateItem position="+position);
        container.addView(mViews[position]);
        return mViews[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("destroyItem position="+position);
        container.removeView((View) object);
    }
}

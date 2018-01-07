
package com.weishop.test.fragment.viewpager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.fragment.Fragment1;
import com.weishop.test.fragment.Fragment2;

public class FragmentViewPagerActivity extends android.support.v4.app.FragmentActivity implements
        View.OnClickListener {
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("FragmentViewPagerActivity onCreate");
        setContentView(R.layout.activity_fragment_viewpager);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                   | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//4.1支持 16
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//5.0 开始支持 21
        }
        initView();

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.item_view, null);
        TextView textView = (TextView) view1.findViewById(R.id.item1);
        textView.setText("page0");

        View view2 = inflater.inflate(R.layout.item_view, null);
        textView = (TextView) view2.findViewById(R.id.item1);
        textView.setText("page1");

        View view3 = inflater.inflate(R.layout.item_view, null);
        textView = (TextView) view3.findViewById(R.id.item1);
        textView.setText("page2");

        View view4 = inflater.inflate(R.layout.item_view, null);
        textView = (TextView)view4 .findViewById(R.id.item1);
        textView.setText("page3");

        View[] views = {view1, view2, view3,view4};
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("FragmentViewPagerActivity onDestroy");


    }
}

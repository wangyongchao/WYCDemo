
package com.weishop.test.custom.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.custom.viewpager.source.ViewPager;
import com.weishop.test.util.TestUtils;

import androidx.fragment.app.FragmentActivity;

public class FragmentViewPagerActivity extends FragmentActivity implements
        View.OnClickListener {
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("FragmentViewPagerActivity onCreate");
        setContentView(R.layout.activity_fragment_viewpager);

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("FragmentViewPagerActivity onResume");
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                TestUtils.getProperty(FragmentViewPagerActivity.this);
            }
        }, 200);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.item_view, null);
        view1.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
        TextView textView = (TextView) view1.findViewById(R.id.item1);
        textView.setText("page0");

        View view2 = inflater.inflate(R.layout.item_view, null);
        view2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        textView = (TextView) view2.findViewById(R.id.item1);
        textView.setText("page1");

        View view3 = inflater.inflate(R.layout.item_view, null);
        view3.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        textView = (TextView) view3.findViewById(R.id.item1);
        textView.setText("page2");

        View view4 = inflater.inflate(R.layout.item_view, null);
        textView = (TextView) view4.findViewById(R.id.item1);
        textView.setText("page3");

        View[] views = {view1, view2, view3, view4};
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

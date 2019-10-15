
package com.weishop.test.fragment.fragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.fragment.Fragment1;
import com.weishop.test.fragment.Fragment2;
import com.weishop.test.util.TestUtils;

public class FragmentTabsActivity extends android.support.v4.app.FragmentActivity implements
        View.OnClickListener {

    FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TestUtils.TAG, "FragmentTabsActivity onCreate");
        setContentView(R.layout.activity_fragmenttabs);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment simple = getSupportFragmentManager().findFragmentByTag("simple");
                Fragment contacts = getSupportFragmentManager().findFragmentByTag("contacts");

                Log.d(TestUtils.TAG, simple.isDetached() + "," + contacts.isDetached());
            }
        });

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        LayoutInflater inflater = LayoutInflater.from(this);
        TextView view1 = (TextView) inflater.inflate(R.layout.view_indicator, null);
        view1.setText("任务");
        view1.setBackground(getResources().getDrawable(R.drawable.shape_corners_solid_ff5e50));
        view1.setTextColor(getResources().getColor(R.color.white));
        TextView view2 = (TextView) inflater.inflate(R.layout.view_indicator, null);
        view2.setText("课程");

        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator(view1), Fragment1.class,
                null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator(view2), Fragment2.class,
                null);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d(TestUtils.TAG, "tabId=" + tabId);
                changeTabTheme();

            }
        });


    }

    private void changeTabTheme() {
        int tabCount = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TextView textView =
                    (TextView) mTabHost.getTabWidget().getChildTabViewAt(i);
            if (i == mTabHost.getCurrentTab()) {
                textView.setBackground(getResources().getDrawable(R.drawable.shape_corners_solid_ff5e50));
                textView.setTextColor(getResources().getColor(R.color.white));
            } else {
                textView.setBackground(null);
                textView.setTextColor(getResources().getColor(R.color.studycenter_color_ff5e50));
            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TestUtils.TAG, "FragmentTabsActivity onResume");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TestUtils.TAG, "FragmentTabsActivity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TestUtils.TAG, "FragmentTabsActivity onDestroy");
    }
}

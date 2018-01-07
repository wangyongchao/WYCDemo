
package com.weishop.test.fragment.fragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;

import com.weishop.test.fragment.Fragment1;
import com.weishop.test.fragment.Fragment2;
import com.weishop.test.R;

public class FragmentTabHostActivity extends android.support.v4.app.FragmentActivity implements
        View.OnClickListener {

    FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenthost);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);


        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"), Fragment1.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Contacts"), Fragment2.class,
                null);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("FragmentTabHostActivity onDestroy");
    }
}

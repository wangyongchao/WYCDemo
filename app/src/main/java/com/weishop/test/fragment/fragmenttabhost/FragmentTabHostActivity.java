
package com.weishop.test.fragment.fragmenttabhost;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.weishop.test.fragment.Fragment1;
import com.weishop.test.fragment.Fragment2;
import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import androidx.fragment.app.FragmentActivity;

public class FragmentTabHostActivity extends FragmentActivity implements
        View.OnClickListener {

    FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TestUtils.TAG, "FragmentTabHostActivity onCreate");
        setContentView(R.layout.activity_fragmenthost);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);


        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"), Fragment1.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Contacts"), Fragment2.class,
                null);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TestUtils.TAG, "FragmentTabHostActivity onResume");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TestUtils.TAG, "FragmentTabHostActivity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TestUtils.TAG, "FragmentTabHostActivity onDestroy");
    }
}

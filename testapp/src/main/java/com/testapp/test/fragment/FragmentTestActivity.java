
package com.testapp.test.fragment;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;


import com.testapp.test.R;
import com.testapp.test.activitycharacter.AActivity;
import com.testapp.test.activitycharacter.BActivity;

import java.lang.reflect.Method;

public class FragmentTestActivity extends TabActivity implements View.OnClickListener {
    //    Fragment1 fragment1;
    private TabHost mTabHost;
    private Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenttest);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);

        Resources res = getResources();
        mTabHost = this.getTabHost();

        Intent intent = new Intent();
        intent.setClass(this, InnerActivity.class);
        TabHost.TabSpec tabSpec = mTabHost.newTabSpec("First").setIndicator("First", res.getDrawable(R.mipmap
                .ic_launcher)).setContent(intent);
        mTabHost.addTab(tabSpec);

        intent = new Intent();
        intent.setClass(this, BActivity.class);
        intent.putExtra("name", "value");
        tabSpec = mTabHost.newTabSpec("Second").setIndicator("Second", res.getDrawable(R.mipmap
                .ic_launcher)).setContent
                (intent);
        mTabHost.addTab(tabSpec);

        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment1 = new Fragment1();
        fragmentTransaction.add(fragment1, "permission");
        fragmentTransaction.commit();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            fragment1.getPermisson();
        } else {
            PackageManager packageManager = getPackageManager();
            try {
                Method method = packageManager.getClass().getMethod
                        ("buildRequestPermissionsIntent", String[].class);
                String s[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                Intent intent = (Intent) method.invoke(packageManager, new Object[]{new String[]{Manifest.permission
                        .WRITE_EXTERNAL_STORAGE}});
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("FragmentTestActivity onActivityResult");
    }
}

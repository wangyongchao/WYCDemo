
package com.weishop.test.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;

import com.weishop.test.R;

public class FragmentCateActivity extends FragmentActivity implements
        View.OnClickListener {

    private Fragment1 fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        fragment = new Fragment1();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content, fragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        System.out.println("onBackPressed");
        super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}


package com.weishop.test.activitycharacter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;


public class CActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("CActivity onCreate =" + this);
        System.out.println("CActivity taskid=" + this.getTaskId());

        setContentView(R.layout.activity_c);
        findViewById(R.id.ccc).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CActivity.this, DActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("CActivity onRestart =" + this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("CActivity onStart =" + this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("CActivity onResume =" + this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("CActivity onNewIntent =" + this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("CActivity onPause =" + this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("CActivity onStop =" + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("CActivity onDestroy =" + this);
    }
}

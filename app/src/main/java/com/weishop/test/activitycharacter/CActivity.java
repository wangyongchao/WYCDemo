
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
        System.out.println("CActivity onCreate");
        System.out.println("CActivity taskid=" + this.getTaskId());

        setContentView(R.layout.activity_c);
        findViewById(R.id.ccc).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CActivity.this, BActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("CActivity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("CActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("CActivity onResume");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("CActivity onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("CActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("CActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("CActivity onDestroy");
    }
}


package com.weishop.test.activitycharacter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;


public class BActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("BActivity onCreate");

        System.out.println("BActivity taskid=" + this.getTaskId());

        setContentView(R.layout.activity_b);
        findViewById(R.id.bbb).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(BActivity.this, CActivity.class);
//        startActivity(intent);
        finish();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("BActivity onRestart");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("BActivity onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("BActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("BActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("BActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("BActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("BActivity onDestroy");
    }
}

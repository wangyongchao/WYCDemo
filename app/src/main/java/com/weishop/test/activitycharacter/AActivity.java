
package com.weishop.test.activitycharacter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;


public class AActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("AActivity onCreate this="+this);

        System.out.println("AActivity taskid=" + this.getTaskId());

        setContentView(R.layout.activity_a);
        findViewById(R.id.aaa).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(AActivity.this, BActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("AActivity onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("AActivity onSaveInstanceState");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("AActivity onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("AActivity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("AActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("AActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("AActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("AActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("AActivity onDestroy");
    }
}

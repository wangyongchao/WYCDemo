
package com.weishop.test.activitycharacter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;


public class DActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DActivity onCreate =" + this);
        System.out.println("DActivity taskid=" + this.getTaskId());

        setContentView(R.layout.activity_d);
        findViewById(R.id.ddd).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        long currentTimeMillis = System.currentTimeMillis();
        while (true){
            long time = System.currentTimeMillis();
            if(time-currentTimeMillis>20000){
                return;
            }
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DActivity.this, BActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("DActivity onRestart =" + this);
    }



    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("DActivity onResume =" + this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("DActivity onNewIntent =" + this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("DActivity onPause =" + this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("DActivity onStop =" + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("DActivity onDestroy =" + this);
    }
}


package com.weishop.test.network;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianping.logan.Logan;
import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import java.io.File;
import java.io.IOException;

public class OkhttpActivity extends Activity implements View.OnClickListener {

    private TestOkhttp testOkhttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        testOkhttp = new TestOkhttp(this);
        this.findViewById(R.id.send).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        try {
            testOkhttp.testAsync();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void get() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GetExample example = new GetExample();
                    String response = example.run("https://raw.github" +
                            ".com/square/okhttp/master/README" +
                            ".md");
//                    String response = example.run("https://www.baidu.com");
                    System.out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void post() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    PostExample postExample = new PostExample();
                    String json = postExample.bowlingJson("Jesse", "Jake");
                    String response = postExample.post("http://www.roundsapp.com/post", json);
                    System.out.println(response);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

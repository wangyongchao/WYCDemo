
package com.weishop.test.bitmap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

import java.io.File;

public class BitmapActivity extends Activity implements View.OnClickListener {
    private ImageView imageViewOrignal;
    private ImageView imageViewProcess;
    private BitmapApiTest bitmapApiTest = new BitmapApiTest(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // // 设置无标题
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // // 设置全屏
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_bitmap);
        imageViewOrignal = (ImageView) findViewById(R.id.testBitmapOrginal);
        imageViewProcess = (ImageView) findViewById(R.id.testBitmapProcess);
        Button btnView = findViewById(R.id.btn);
        btnView.setOnClickListener(this);

        TestUtils.getProperty(this);


    }


    @Override
    public void onClick(View v) {
        Bitmap bitmapOrignal = bitmapApiTest.loadNormalBitmap();
        imageViewOrignal.setImageBitmap(bitmapOrignal);
        Bitmap bitmap = bitmapApiTest.loadCompressBitmap(bitmapOrignal);
        imageViewProcess.setImageBitmap(bitmap);


    }

}

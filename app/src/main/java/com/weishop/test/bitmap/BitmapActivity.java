
package com.weishop.test.bitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

public class BitmapActivity extends Activity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TestUtils.getMemoryInfo(this);

        // // 设置无标题
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // // 设置全屏
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_bitmap);
        imageView = (ImageView) findViewById(R.id.testBitmap);
        Button btnView = findViewById(R.id.btn);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
                int byteCount = bitmap.getByteCount();
                float caculateMunit = TestUtils.caculateMunit(byteCount);
                System.out.println("caculateMunit="+caculateMunit+",with="+bitmap.getWidth()+",height="+bitmap.getHeight());
                TestUtils.getMemoryInfo(BitmapActivity.this);
//                loadBitmap();
            }
        });

    }

    private void loadBitmap() {

        int factWidth = (int) getResources().getDimension(R.dimen.image_width);
        int factHeight = factWidth;
        System.out.println("factWidth=" + factWidth + ",factHeight=" + factHeight);

        //4608*3456
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
//        options.inPreferredConfig= Bitmap.Config.RGB_565;
        BitmapFactory.decodeResource(getResources(), R.drawable.test, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
        System.out.println("imageHeight=" + imageHeight + ",imageWidth=" + imageWidth + ",imageType=" + imageType);
        int inSampleSize = TestUtils.calculateInSampleSize(options, factWidth, factHeight);

        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test, options);
        int afterImageHeight = options.outHeight;
        int afterImageWidth = options.outWidth;
        System.out.println("afterImageHeight=" + afterImageHeight + ",afterImageWidth=" + afterImageWidth);

        TestUtils.getMemoryInfo(this);

    }


}

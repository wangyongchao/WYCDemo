
package com.weishop.test.bitmap;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import java.io.IOException;
import java.io.InputStream;

public class BitmapActivity extends Activity implements View.OnClickListener {
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
        btnView.setOnClickListener(this);

        TestUtils.getProperty(this);


    }

    /**
     * 压缩bitmap
     */
    private void compressBitmap() {


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
        System.out.println("imageHeight=" + imageHeight + ",imageWidth=" + imageWidth + "," +
                "imageType=" + imageType);
        int inSampleSize = TestUtils.calculateInSampleSize(options, factWidth, factHeight);

        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test, options);
        int afterImageHeight = options.outHeight;
        int afterImageWidth = options.outWidth;
        System.out.println("afterImageHeight=" + afterImageHeight + ",afterImageWidth=" + afterImageWidth);

        TestUtils.getMemoryInfo(this);

    }

    private void testAssetBitmap() {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("test.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            printBimtapPropertities(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void testBitmap() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        printBimtapPropertities(bitmap);
    }

    private void printBimtapPropertities(Bitmap bitmap) {
        int byteCount = bitmap.getByteCount();
        float caculateMunit = TestUtils.caculateMunit(byteCount);
        Log.d(TestUtils.TAG, "caculateMunit=" + caculateMunit + ",with=" + bitmap.getWidth() + "," +
                "height=" + bitmap.getHeight());
        TestUtils.getMemoryInfo(BitmapActivity.this);
    }


    @Override
    public void onClick(View v) {
        int view_width = (int) getResources().getDimension(R.dimen.view_width);
        int view_height = (int) getResources().getDimension(R.dimen.view_height);
        Drawable drawable1 = getResources().getDrawable(R.drawable.livebusiness_group3v3_header_bg);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.img_29);
        BitmapDrawable drawable2 = new BitmapDrawable(bitmap2);
        Drawable drawables[] = {drawable1,drawable2};
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        layerDrawable.setLayerSize(0,view_height,view_height);
        layerDrawable.setLayerSize(1,view_width,view_width);
        layerDrawable.setLayerGravity(1, Gravity.CENTER);


        imageView.setImageBitmap(drawable2bitmap(layerDrawable));

    }

    public static Bitmap drawable2bitmap(Drawable drawable) {

        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable d = (BitmapDrawable) drawable;
            return d.getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        if (width <= 0 || height <= 0) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Rect bounds = new Rect();
        bounds.left = 0;
        bounds.right = width;
        bounds.top = 0;
        bounds.bottom = height;
        drawable.setBounds(bounds);
        drawable.draw(canvas);
        return bitmap;
    }
}

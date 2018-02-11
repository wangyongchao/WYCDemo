
package com.weishop.test.lru;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.weishop.test.R;
import com.weishop.test.activitycharacter.BActivity;
import com.weishop.test.util.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class LRUActivity extends Activity implements View.OnClickListener {

    private ImageView imageView;
    private LruCache<Integer, Bitmap> mMemoryCache;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru);

        TestUtils.getMemoryInfo(this);

        Button addBtn = (Button) findViewById(R.id.add_btn);
        addBtn.setOnClickListener(this);

        Button removeBtn = (Button) findViewById(R.id.remove_btn);
        removeBtn.setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.imageview);

        initMemoryCache();
    }

    private void initMemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M
        mMemoryCache = new LruCache<Integer, Bitmap>(mCacheSize) {
            //必须重写此方法，来测量Bitmap的大小
            @Override
            protected int sizeOf(Integer key, Bitmap value) {
                return value.getByteCount();
            }

            @Override
            protected Bitmap create(Integer key) {
                return createBitmap();
            }

        };
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.add_btn) {
            Bitmap bitmap = createBitmap();
            mMemoryCache.put(count, bitmap);
            count++;

        } else {
            Bitmap bitmap = mMemoryCache.get(0);
            imageView.setImageBitmap(bitmap);
        }


    }

    private Bitmap createBitmap() {
        Bitmap bitmap = null;
        try {
            String externalFilesDir = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/test.jpg";
            FileInputStream fis = new FileInputStream(externalFilesDir);
            bitmap = BitmapFactory.decodeStream(fis);
            int byteCount = bitmap.getByteCount();
            System.out.println("byteCount=" + byteCount + ",unit=" + TestUtils.caculateMunit(byteCount));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}

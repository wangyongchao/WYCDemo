
package com.weishop.test.memory;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class LRUActivity extends Activity implements View.OnClickListener {

    private ImageView imageView;
    private LruCache<String, Bitmap> mMemoryCache;
    private int count;
    private People people;

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

    }


    @Override
    public void onClick(View v) {
        AClass aClass = new AClass();
        AClass bClass = new AClass();
        aClass.obj = bClass;
        bClass.obj = aClass;
        aClass = null;
        bClass = null;
        System.gc();
        int id = v.getId();
//        if (id == R.id.add_btn) {
//            people = new People();
//            TestUtils.getMemoryInfo(this);
//
//        } else {
//
//        }

    }


    private void testNativeBitmap() {
//            String externalFilesDir = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/test/test.jpg";
//            Bitmap bitmap = createBitmap(externalFilesDir);

        try {
            AssetManager am = getAssets();
            InputStream is = am.open("test.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            System.out.println("bitmap=" + bitmap);
            TestUtils.getMemoryInfo(this);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void initMemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 4;
        //给LruCache分配1/8 4M
        mMemoryCache = new LruCache<String, Bitmap>(mCacheSize) {
            //必须重写此方法，来测量Bitmap的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }

            @Override
            protected Bitmap create(String key) {
                return createBitmap(key);
            }

        };
    }


    /**
     * 测试lrucache get
     */
    private void testLruCacheGet() {
        String externalFilesDir = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/test" + 13 + ".jpg";
        Bitmap bitmap = mMemoryCache.get(externalFilesDir);
        imageView.setImageBitmap(bitmap);

    }

    /**
     * 测试lrucache
     *
     * @return
     */
    private void testLruCachePut() {
        String externalFilesDir = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/test" + count + ".jpg";
        Bitmap bitmap = createBitmap(externalFilesDir);
        mMemoryCache.put(externalFilesDir, bitmap);
        count++;
        if (count > 8) {
            count = 0;
        }
    }


    /**
     * 创建bitmap
     *
     * @param externalFilesDir
     * @return
     */
    private Bitmap createBitmap(String externalFilesDir) {
        Bitmap bitmap = null;
        try {
            FileInputStream fis = new FileInputStream(externalFilesDir);
            bitmap = BitmapFactory.decodeStream(fis);
            int byteCount = bitmap.getByteCount();
            System.out.println("externalFilesDir=" + externalFilesDir
                    + ",byteCount=" + byteCount + ",unit=" + TestUtils.caculateMunit(byteCount));

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return bitmap;
    }
}

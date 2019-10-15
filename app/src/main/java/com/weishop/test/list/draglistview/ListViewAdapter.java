package com.weishop.test.list.draglistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weishop.test.R;
import com.weishop.test.list.ListData;
import com.weishop.test.performance.memory.LruCache;

import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongchao on 15/12/3.
 */
public class ListViewAdapter extends BaseAdapter {

    private final Context mContext;
    private List<ListData> mData = new ArrayList<ListData>();
    private LayoutInflater mInflater;
    private LruCache<String, BitmapDrawable> mMemoryCache;

    public ListViewAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, BitmapDrawable>(cacheSize) {
            @Override
            protected int sizeOf(String key, BitmapDrawable drawable) {
                return drawable.getBitmap().getByteCount();
            }
        };
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_test_view, null);
            viewHolder = new ViewHolder();
            viewHolder.nameView = (TextView) convertView.findViewById(R.id.name_view);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            viewHolder.nameView.setOnClickListener(viewHolder);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setPosition(position);
        ListData data = mData.get(position);
        if (data != null) {
            viewHolder.nameView.setText(data.name);
            BitmapDrawable bitmapDrawable = getBitmapFromMemoryCache(data.imgUrl);
            if (bitmapDrawable != null) {
                viewHolder.imageView.setImageDrawable(bitmapDrawable);
                viewHolder.imageView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                viewHolder.imageView.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
                viewHolder.imageView.setBackgroundColor(Color.GRAY);
                ListViewAdapter.BitmapWorkerTask task =
                        new ListViewAdapter.BitmapWorkerTask(viewHolder.imageView, this);
                task.execute(data.imgUrl);
            }
        }
        return convertView;
    }

    private void test() {
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testRead() {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File file = new File(externalStorageDirectory, "/test/testimage.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int read = 0;
            while ((read = fileInputStream.read(bytes)) != -1) {
                System.out.println("lenght=" + read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(List<ListData> data) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }

    }

    public class ViewHolder implements View.OnClickListener {
        private TextView nameView;
        private ImageView imageView;
        private int mPosition;


        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), mData.get(mPosition).name, Toast.LENGTH_LONG).show();

        }

        public void setPosition(int position) {
            this.mPosition = position;
        }
    }

    /**
     * 将一张图片存储到LruCache中。
     *
     * @param key      LruCache的键，这里传入图片的URL地址。
     * @param drawable LruCache的值，这里传入从网络上下载的BitmapDrawable对象。
     */
    public void addBitmapToMemoryCache(String key, BitmapDrawable drawable) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, drawable);
        }
    }

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param key LruCache的键，这里传入图片的URL地址。
     * @return 对应传入键的BitmapDrawable对象，或者null。
     */
    public BitmapDrawable getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    /**
     * 异步下载图片的任务。
     *
     * @author guolin
     */
    static class BitmapWorkerTask extends AsyncTask<String, Void, BitmapDrawable> {

        WeakReference<ListViewAdapter> weakReference;
        WeakReference<ImageView> imageViewWeakReference;

        public BitmapWorkerTask(ImageView imageView, ListViewAdapter adapter) {

            imageViewWeakReference = new WeakReference<ImageView>(imageView);;
            weakReference = new WeakReference<ListViewAdapter>(adapter);
        }

        @Override
        protected BitmapDrawable doInBackground(String... params) {
            String imageUrl = params[0];
            // 在后台开始下载图片
            Bitmap bitmap = downloadBitmap(imageUrl);
            ListViewAdapter listViewAdapter = weakReference.get();
            BitmapDrawable drawable = new BitmapDrawable(listViewAdapter.mContext.getResources(), bitmap);
            listViewAdapter.addBitmapToMemoryCache(imageUrl, drawable);
            return drawable;
        }

        @Override
        protected void onPostExecute(BitmapDrawable drawable) {
            ImageView imageView = imageViewWeakReference.get();
            if (imageView != null && drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }

        /**
         * 建立HTTP请求，并获取Bitmap对象。
         *
         * @param imageUrl 图片的URL地址
         * @return 解析后的Bitmap对象
         */
        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitmap;
        }

    }


}

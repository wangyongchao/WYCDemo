package com.weishop.test.list.draglistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.list.ListData;
import com.weishop.test.performance.memory.LruCache;

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
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ListData data = mData.get(position);
        if (data != null) {
            viewHolder.nameView.setText(data.name);
            BitmapDrawable bitmapDrawable = getBitmapFromMemoryCache(data.imgUrl);
            if (bitmapDrawable != null) {
                viewHolder.imageView.setImageDrawable(bitmapDrawable);
            } else {
                ListViewAdapter.BitmapWorkerTask task = new ListViewAdapter.BitmapWorkerTask(viewHolder.imageView);
                task.execute(data.imgUrl);
            }
        }
        return convertView;
    }

    public void setData(List<ListData> data) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }

    }

    public class ViewHolder {
        private TextView nameView;
        private ImageView imageView;


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
    class BitmapWorkerTask extends AsyncTask<String, Void, BitmapDrawable> {

        private ImageView mImageView;

        public BitmapWorkerTask(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        protected BitmapDrawable doInBackground(String... params) {
            String imageUrl = params[0];
            // 在后台开始下载图片
            Bitmap bitmap = downloadBitmap(imageUrl);
            BitmapDrawable drawable = new BitmapDrawable(mContext.getResources(), bitmap);
            addBitmapToMemoryCache(imageUrl, drawable);
            return drawable;
        }

        @Override
        protected void onPostExecute(BitmapDrawable drawable) {
            if (mImageView != null && drawable != null) {
                mImageView.setImageDrawable(drawable);
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

package com.testapp.test;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongchao on 15/12/3.
 */
public class ListViewAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Label> mData = new ArrayList<Label>();
    private LayoutInflater mInflater;

    public ListViewAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.item_view, null);
            viewHolder = new ViewHolder();
            viewHolder.item1 = (TextView) convertView.findViewById(R.id.item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String data = mData.get(position).getLabelText();
        if (!TextUtils.isEmpty(data)) {
            viewHolder.item1.setText(data);
        }
        return convertView;
    }

    private void test() {
        String str = "fdasdfdsfdsfdsfdsf";
        int i = 0;
        try {
            while (i < 100) {
                i++;
                byte[] md5s = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
                mContext.getSharedPreferences("md5", Context.MODE_PRIVATE).edit().putString("md5", new String(md5s));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void setData(List<Label> data) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }

    }

    public List<Label> getData() {

        return mData;
    }

    public class ViewHolder {
        private TextView item1;

    }


}

package com.weishop.test.data;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weishop.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongchao on 15/12/3.
 */
public class ListViewAdapter extends BaseAdapter {

    private final Context mContext;
    private List<String> mData = new ArrayList<String>();
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
            viewHolder.item1 = (TextView) convertView.findViewById(R.id.item1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        test();
        String data = mData.get(position);
        if (!TextUtils.isEmpty(data)) {
            viewHolder.item1.setText(data);
        }
        return convertView;
    }

    private void test() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setData(List<String> data) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }

    }

    public class ViewHolder {
        private TextView item1;

    }


}


package com.weishop.test.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weishop.test.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final Context mContext;
    private List<String> mDataset = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public View view;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.item1);
            view=v;
        }
    }

    public MyAdapter(Context context) {
        this.mContext=context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        System.out.println("onBindViewHolder position=" + position);
        if(position % 2==0){
            holder.view.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else {
            holder.view.setBackgroundColor(mContext.getResources().getColor(R.color.item_background));
        }
        holder.mTextView.setText(mDataset.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addData(int position,String newItem) {
        mDataset.add(newItem);
        notifyItemInserted(position);
    }

    public void setData(List<String> data) {
        mDataset = data;
    }

    public void removeData(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }
}

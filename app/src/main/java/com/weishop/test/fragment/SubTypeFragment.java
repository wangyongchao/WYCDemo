
package com.weishop.test.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.weishop.test.R;

public class SubTypeFragment extends Fragment {

    public interface OnItemClickListener {
        public void onItemClick(Bundle bundle);

    }

    private OnItemClickListener mOnItemListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setOnItemListener(OnItemClickListener listener) {
        this.mOnItemListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subtype, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button subtype = (Button) view.findViewById(R.id.subtype);
        subtype.setText(getArguments().getString("type"));
        subtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "计算机类");
                if (mOnItemListener != null) {
                    mOnItemListener.onItemClick(bundle);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}


package com.weishop.test.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;


public class Fragment2 extends Fragment implements  RefreshDataImpl{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TestUtils.TAG, "Fragment2 onCreate");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TestUtils.TAG, "Fragment2 onCreateView");
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onDestroyView() {
        Log.d(TestUtils.TAG, "Fragment2 onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TestUtils.TAG, "Fragment2 onAttach");
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Log.d(TestUtils.TAG, "Fragment2 onDetach");
        super.onDetach();
    }

    @Override
    public void refreshData() {

    }
}

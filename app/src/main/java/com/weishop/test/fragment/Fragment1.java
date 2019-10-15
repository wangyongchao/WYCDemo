
package com.weishop.test.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

public class Fragment1 extends Fragment implements RefreshDataImpl{

    private EditText editText;
    private InputMethodManager inputMethodManager;
    private int REQUEST_CODE_MUTI = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TestUtils.TAG, "Fragment1 onCreate");
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TestUtils.TAG, "Fragment1 onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TestUtils.TAG, "Fragment1 onPause");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TestUtils.TAG, "Fragment1 onCreateView");
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onDestroyView() {
        Log.d(TestUtils.TAG, "Fragment1 onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TestUtils.TAG, "Fragment1 onDestroy");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TestUtils.TAG, "Fragment1 onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TestUtils.TAG, "Fragment1 onDetach");
    }


    public void getPermisson() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_MUTI);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        System.out.println("onRequestPermissionsResult");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult");
    }

    @Override
    public void refreshData() {

    }
}





package com.testapp.test.fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Fragment1 extends Fragment {

    private EditText editText;
    private InputMethodManager inputMethodManager;
    private int REQUEST_CODE_MUTI = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Fragment1 onCreate");
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        editText = (EditText) view.findViewById(R.id.editText);
//        TextView textView = (TextView) view.findViewById(R.id.fragment_text);
//        textView.setText(new Random().nextInt(7) + "");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Fragment1 onDestroy");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("Fragment1 onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("Fragment1 onDetach");
    }


    public void getPermisson() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_MUTI);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        System.out.println("onRequestPermissionsResult");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult");
    }
}




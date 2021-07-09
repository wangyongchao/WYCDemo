
package com.weishop.test.activitycharacter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.fragment.Fragment1;

import androidx.appcompat.app.AppCompatActivity;


public class TransparentActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onClick(View v) {


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("TransparentActivity onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("TransparentActivity onSaveInstanceState");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("TransparentActivity onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("TransparentActivity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("TransparentActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("TransparentActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("TransparentActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("TransparentActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("TransparentActivity onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("TransparentActivity onActivityResult");
    }
}

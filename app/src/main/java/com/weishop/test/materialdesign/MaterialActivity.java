
package com.weishop.test.materialdesign;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.weishop.test.R;

public class MaterialActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

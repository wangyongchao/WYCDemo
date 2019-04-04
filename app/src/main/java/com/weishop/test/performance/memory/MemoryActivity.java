
package com.weishop.test.performance.memory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import java.lang.ref.SoftReference;

public class MemoryActivity extends Activity implements View.OnClickListener {
    //    private int[] aa = new int[10000];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        this.findViewById(R.id.memory).setOnClickListener(this);
        this.findViewById(R.id.test).setOnClickListener(this);

//        SingleInstance.getInstance().addContext(this);
        TestUtils.getMemoryInfo(this);

    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, LeakActivity.class));


    }

    private void a() {
        b();
    }

    private void b() {
        a();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SingleInstance.getInstance().clear();
    }
}

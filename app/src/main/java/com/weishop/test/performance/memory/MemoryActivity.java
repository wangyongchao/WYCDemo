
package com.weishop.test.performance.memory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.bitmap.BitmapActivity;
import com.weishop.test.performance.memory.ref.RefTest;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

public class MemoryActivity extends Activity implements View.OnClickListener {
    private TextView countTv;
    private int count = 0;
    private RefTest refTest = new RefTest();
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        countTv = this.findViewById(R.id.count_tv);
        countTv.setText(String.valueOf(count));
        this.findViewById(R.id.test).setOnClickListener(this);
        this.findViewById(R.id.memory_growth).setOnClickListener(this);

//        refTest.startCheckQueue();
//        TestUtils.getMemoryInfo(this);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtils.d("MemoryActivity onLowMemory");
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.test) {
////            countTv.setText(String.valueOf(++count));
////            refTest.testWeakReference();
////            refTest.testSoftReference();
//            refTest.testPhantomReference();
////            startActivity(new Intent(this, LeakActivity.class));
//        } else {
//            refTest.testMemoryGrowth();
//
//        }
        startActivity(new Intent(this, LeakActivity.class));

        TestUtils.getAppHeapMemoryInfo(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

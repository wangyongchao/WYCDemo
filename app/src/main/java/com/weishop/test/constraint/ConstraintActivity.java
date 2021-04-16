
package com.weishop.test.constraint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstraintActivity extends Activity implements View.OnClickListener {

    private Button button;
    private View tvA;
    private View tvB;
    private View tvC;
    private View tvD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_constraint);

        button = this.findViewById(R.id.btn);
        button.setOnClickListener(this);
        button.postDelayed(new Runnable() {
            @Override
            public void run() {
                TestUtils.getProperty(ConstraintActivity.this);

            }
        }, 1000);

        tvA = findViewById(R.id.tv_a);
        tvB = findViewById(R.id.tv_b);
        tvC = findViewById(R.id.tv_c);
        tvD = findViewById(R.id.tv_d);
    }


    @Override
    public void onClick(View v) {
        ConstraintLayout.LayoutParams layoutParams =
                (ConstraintLayout.LayoutParams) tvA.getLayoutParams();
//        layoutParams.horizontalChainStyle=ConstraintLayout.LayoutParams.CHAIN_PACKED
        LogUtils.d("aleft=" + tvA.getLeft() + ",bleft=" + tvB.getLeft() + ",cleft=" + tvC.getLeft());
        LogUtils.d("aright=" + tvA.getRight() + ",bright=" + tvB.getRight() + ",cright=" + tvC.getRight());
        LogUtils.d("awidth=" + tvA.getWidth() + ",bwidth=" + tvB.getWidth()
                + ",cwidth=" + tvC.getWidth() + ",dwidth=" + tvD.getWidth());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

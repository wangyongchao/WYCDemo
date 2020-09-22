package com.weishop.test.animator;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.weishop.test.R;

import java.util.ArrayList;
import java.util.List;

public class BarrageActivity extends AppCompatActivity implements View.OnClickListener {

    private VerticalBarrageView verticalBarrageView;
    private long lastPraiseTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filpper);
        View btn = findViewById(R.id.btn);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastPraiseTime = SystemClock.uptimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println(SystemClock.uptimeMillis() - lastPraiseTime);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return false;
            }
        });
        verticalBarrageView = findViewById(R.id.barrageView);
    }


    @Override
    public void onClick(View v) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("onClickonClickonClick" + String.valueOf(i));
        }
        verticalBarrageView.addBarrages(list);
        verticalBarrageView.start();
    }
}
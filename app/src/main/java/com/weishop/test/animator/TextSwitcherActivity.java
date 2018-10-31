
package com.weishop.test.animator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;
import com.weishop.test.R;

import java.util.ArrayList;
import java.util.List;

public class TextSwitcherActivity extends Activity implements View.OnClickListener {

    private TextSwitcher tvNotice;
    private TickerView tickerView;
    private List<Long> praiseTimeList = new ArrayList<>();
    private int continueNum = 0;
    private boolean isLongPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textswitcher);
        View start = findViewById(R.id.start);
        start.setOnClickListener(this);
        start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("ACTION_DOWN");
                        handler.postDelayed(runnable, 333);
                        isLongPress = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("ACTION_UP");
                        handler.removeCallbacks(runnable);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        System.out.println("ACTION_CANCEL");
                        handler.removeCallbacks(runnable);
                        break;
                }
                return isLongPress;
            }
        });

    }

    private void testOther() {
        tvNotice = (TextSwitcher) findViewById(R.id.textswitcher);
        tvNotice.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(TextSwitcherActivity.this);
                textView.setTextColor(Color.RED);
                textView.setTextSize(20);
                return textView;
            }
        });
        tvNotice.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_slide_in));
        tvNotice.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out_slide_out));
        tvNotice.setText("2222222222222222");


        tickerView = findViewById(R.id.tickerView);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        tickerView.setTextColor(R.color.colorPrimary);
        tickerView.setTextSize(100);
        tickerView.setAnimationDuration(500);
        tickerView.setAnimationInterpolator(new OvershootInterpolator());
        tickerView.setGravity(Gravity.START);
        handler.postDelayed(runnable, 1000);
    }

    private int num = 0;
    private long lastTime=0;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            long interval = SystemClock.uptimeMillis() - lastTime;
            System.out.println("interval="+interval);
            lastTime = SystemClock.uptimeMillis();
            isLongPress = true;
            System.out.println("runrun");
            handler.postDelayed(runnable, 333);
        }
    };


    @Override
    public void onClick(View v) {
        long currentPraiseTime = SystemClock.uptimeMillis();
        //首次点赞
        if (praiseTimeList.isEmpty()) {
            continueNum++;
            //播放动画
        } else {
            long lastPraiseTime = praiseTimeList.get(praiseTimeList.size() - 1);
            long interval = currentPraiseTime - lastPraiseTime;
            //大于1秒不算连续点赞l
            if (interval > 1000) {
                continueNum = 0;
                praiseTimeList.clear();
                continueNum++;
                //播放动画
            } else {
                continueNum++;
                if (currentPraiseTime - praiseTimeList.get(0) > 5000) {
                    //push总数
                    praiseTimeList.clear();


                }
                //圆圈内直接计数
                if (interval < 500) {

                } else {//计数动画


                }

            }
        }
        praiseTimeList.add(currentPraiseTime);
        System.out.println("点赞" + continueNum);

    }
}


package com.weishop.test.animator;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.Pools;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weishop.test.R;

import java.util.LinkedList;
import java.util.List;

/**
 * 垂直滚动弹幕
 */
public class VerticalBarrageView extends LinearLayout implements Handler.Callback {

    private final int barrageCount = 4;

    private LinkedList<String> barrageQueue = new LinkedList<>();


    private final LayoutInflater inflater;

    private Handler handler = new Handler(Looper.getMainLooper(), this);

    private int currentBarrage = 0;

    private Pools.SimplePool<TextView> textViewPool = new Pools.SimplePool<>(4);

    public VerticalBarrageView(Context context) {
        this(context, null);
    }

    public VerticalBarrageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_filpper, this);

        setOrientation(LinearLayout.VERTICAL);

        LayoutTransition transition = new LayoutTransition();
        ObjectAnimator appearAnimator = ObjectAnimator.ofFloat(null, "alpha", 0f, 1f);
        appearAnimator.setDuration(transition.getDuration(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.APPEARING, appearAnimator);


        ObjectAnimator disappearAnimator = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        disappearAnimator.setDuration(LayoutTransition
                .DISAPPEARING);
        transition.setAnimator(LayoutTransition.DISAPPEARING, disappearAnimator);

        this.setLayoutTransition(transition);
    }

    public void addBarrages(List<String> barrages) {
        barrageQueue.addAll(barrages);
    }

    public void start() {
        handler.sendEmptyMessage(0);


    }

    public void stop() {
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (!barrageQueue.isEmpty()) {
            if (this.getChildCount() >= barrageCount) {
                this.removeViewAt(0);
            }
            TextView textView = obtainTextView();
            textView.setText(barrageQueue.poll());
            this.addView(textView);
            handler.sendEmptyMessageDelayed(0, 1000);
        } else {
            handler.removeMessages(0);
        }
        return true;
    }

    private TextView obtainTextView() {
        TextView textView = textViewPool.acquire();
        if (textView == null) {
            textView = new TextView(getContext());
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            params.topMargin = (int) getContext().getResources().getDimension(R.dimen.fab_margin);
            textView.setLayoutParams(params);
            textView.setTextColor(0xffffffff);
            textView.setTextSize(18);
        }
        return textView;
    }
}

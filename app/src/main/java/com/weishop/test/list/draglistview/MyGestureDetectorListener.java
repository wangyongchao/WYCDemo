package com.weishop.test.list.draglistview;


import android.view.GestureDetector;
import android.view.MotionEvent;

import com.weishop.test.util.TestUtils;

import java.util.HashMap;

/**
 * Created by wangyongchao on 17/4/10.
 */

public class MyGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {


    /**
     * 只接受action down事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onDown(MotionEvent ev) {
        TestUtils.printEvent(ev, "onDown",null);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent ev) {
        TestUtils.printEvent(ev, "onShowPress",null);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent ev) {
        TestUtils.printEvent(ev, "onSingleTapUp",null);
        return false;
    }

    /**
     *
     * @param e1 第一次按下时候的事件，action down事件。
     * @param e2 每次滚动时候的move事件
     * @param distanceX 本次onScroll移动的X轴距离,正数代表向左滚动，负数代表向右滚动
     * @param distanceY 本次onScroll为移动的Y轴距离,正数代表向上滚动，负数代表向下滚动
     * @return
     *
     * 移动的距离是相对于上一次onScroll事件的移动距离，而不是当前点和按下点的距离。
     *
     *
     * onScroll是在down事件时候触发的.达到滚动距离后就调用此方法。
     */

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        HashMap<String, String> map = new HashMap<>();
        map.put("distanceX", distanceX+"");
        map.put("distanceY", distanceY+"");
//        TestUtils.printEvent(e1, "onScroll e1",map);
        TestUtils.printEvent(e2, "onScroll e2",map);

        return false;
    }

    @Override
    public void onLongPress(MotionEvent ev) {
        TestUtils.printEvent(ev, "onLongPress",null);

    }

    /**
     * 此方法在action up事件后触发，
     *
     * @param e1 第一次按下时候的事件，action down事件。
     * @param e2 action up事件
     * @param velocityX 初始速度 负值代表向左滑动 即(e1.getx-e2.getx)>0
     * @param velocityY 初始速度 正值代表向下滑动 即(e1.gety-e2.gety)<0
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        HashMap<String, String> map = new HashMap<>();
        map.put("velocityX", velocityX+"");
        map.put("velocityY", velocityY+"");
        TestUtils.printEvent(e1, "onFling e1",map);
        TestUtils.printEvent(e2, "onFling e2",map);
        return false;
    }

    @Override
    public boolean onContextClick(MotionEvent ev) {
        TestUtils.printEvent(ev, "onContextClick",null);
        return super.onContextClick(ev);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        TestUtils.printEvent(e, "onDoubleTap",null);
        return super.onDoubleTap(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        TestUtils.printEvent(e, "onSingleTapConfirmed",null);
        return super.onSingleTapConfirmed(e);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        TestUtils.printEvent(e, "onDoubleTapEvent",null);
        return super.onDoubleTapEvent(e);
    }

}

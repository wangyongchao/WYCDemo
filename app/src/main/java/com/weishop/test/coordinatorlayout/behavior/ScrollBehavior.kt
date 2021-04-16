package com.weishop.test.coordinatorlayout.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import com.weishop.test.util.LogUtils

class ScrollBehavior : CoordinatorLayout.Behavior<View> {

    constructor(ctx: Context, attributeSet: AttributeSet?) : super(ctx, attributeSet) {

    }


    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        return super.onTouchEvent(parent, child, ev)
    }

    /**
     * CoordinatorLayout的子view尝试去初始化嵌套滚动的时候调用
     *
     * coordinatorLayout:这个behavior所关联的CoordinatorLayout
     * child:这个behavior所关联的CoordinatorLayout的子view
     * directTargetChild：CoordinatorLayout的子view，它是或包含嵌套滚动
     * target:哪个CoordinatorLayout的子view初始化的滚动,就是我们当前滑动的是哪个view
     * axes：嵌套滚动所有使用的轴 2 SCROLL_AXIS_VERTICAL 代表Y轴，1 SCROLL_AXIS_HORIZONTAL 代表x轴
     * type：哪种输入事件导致的滚动
     *
     * 如果返回true，代表此behavior接收这个事件
     */
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View,
                                     directTargetChild: View, target: View, axes: Int, type: Int): Boolean {

        LogUtils.d("onStartNestedScroll coordinatorLayout=${coordinatorLayout},child=${child}," +
                "directTargetChild=${directTargetChild},target=${target},axes=${axes},type=${type}")
        return (axes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, type: Int) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type)
    }


    /**
     * 当正在进行的嵌套滚动即将更新时候调用，在target消耗任何滚动距离之前调用
     * dx：水平方向的像素数
     * dy:垂直方向的像素数
     *
     */
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        LogUtils.d("onNestedPreScroll coordinatorLayout=${coordinatorLayout},child=${child}," +
                "target=${target},dx=${dx},dy=${dy},consumed=${consumed},type=${type}")
        child.scrollY = target.scrollY
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }

    override fun onNestedScrollAccepted(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type)
    }


    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float): Boolean {
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }


    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: View, target: View, velocityX: Float,
                               velocityY: Float, consumed: Boolean): Boolean {
        (child as NestedScrollView).fling(velocityY.toInt())
        return true
    }


}
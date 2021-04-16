package com.weishop.test.coordinatorlayout.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.weishop.test.R
import com.weishop.test.util.LogUtils
import kotlinx.android.synthetic.main.activity_coordinatorlayout_behavior.view.*

open class DependentBehavior : CoordinatorLayout.Behavior<View> {

    constructor(context: Context) : this(context, null) {
    }

    constructor(context: Context, attr: AttributeSet?) : super(context, attr) {
        LogUtils.d("constructor")

    }

    /**
     * parent:代表CoordinatorLayout
     * child 代表设置behavior的View
     * dependency:依赖的view，coordinatorlayout布局中没有设置behavior的view
     * 作用：确定依赖项
     * 在layout过程中，这个方法至少会调用一次，如果返回true，CoordinatorLayout会认为是child和dependency是一对
     * 依赖的view。coordinatorlayout会先对dependency view进行布局，然后再对child进行布局。dependency view的大小或者位置
     * 发生变化后，会调用onDependentViewChanged方法。
     */
    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val layoutDependsOn = dependency.id == R.id.dependentView1

        LogUtils.d("layoutDependsOn parent=${parent},child=${child},dependency=${dependency},layoutDependsOn=${layoutDependsOn}")
        return layoutDependsOn
    }

    /**
     * 依赖view发生变化以后，childview会在此方法中响应。
     * 当依赖view的位置或者大小发生变化后，这个方法会被调用。然后在此方法中更新child view的大小或者位置，如果改变了child view的大小
     * 或者位置，则返回true
     */
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        LogUtils.d("onDependentViewChanged parent=${parent},child=${child},dependency=${dependency}")
        val offset = dependency.top - child.top
        ViewCompat.offsetTopAndBottom(child, offset)
        return true
    }

}

package com.weishop.test.custom.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.weishop.test.util.TestUtils;

/**
 * 自定义viewpager
 */
public class PageView extends ViewGroup {

    private final int mTouchSlop;

    private final Scroller mScroller;

    private int mScreenWidth;

    private float mLastMotionX;

    private float mLastMotionY;
    private VelocityTracker mVelocityTracker;
    private int curScreen;

    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private int mTouchState = TOUCH_STATE_REST;

    public static int SNAP_VELOCITY = 600;

    public PageView(Context context) {
        this(context, null);
    }

    public PageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScreenWidth = TestUtils.getScreenWidth((Activity) this.getContext());

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        mScroller = new Scroller(context);

    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        float x = ev.getX();
        float y = ev.getY();
        int action = ev.getAction();
        String strAction = null;

        if ((action == MotionEvent.ACTION_MOVE) && (mTouchState != TOUCH_STATE_REST)) {
            return true;
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                strAction = "ACTION_DOWN";
                mLastMotionX = x;
                mLastMotionY = y;

                break;
            case MotionEvent.ACTION_MOVE:
                strAction = "ACTION_MOVE";

                final int xDiff = (int) Math.abs(mLastMotionX - x);
                if (xDiff > mTouchSlop) {
                    mTouchState = TOUCH_STATE_SCROLLING;
                }
                break;
            case MotionEvent.ACTION_UP:
                strAction = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                strAction = "ACTION_CANCEL";
                break;
        }
        System.out.println("onInterceptTouchEvent action=" + strAction + ",x="
                + x + ",y=" + y);


        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        int action = ev.getAction();
        String strAction = null;
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }

        mVelocityTracker.addMovement(ev);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                strAction = "ACTION_DOWN";
                if (mScroller != null) {
                    if (!mScroller.isFinished()) {
                        mScroller.abortAnimation();
                    }
                }

                mLastMotionX = x;
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                strAction = "ACTION_MOVE";
                int deltx = (int) (mLastMotionX - x);
                scrollBy(deltx, 0);
                mLastMotionX = x;


                break;
            case MotionEvent.ACTION_UP:
                strAction = "ACTION_UP";
                mVelocityTracker.computeCurrentVelocity(1000);
                int velocityX = (int) mVelocityTracker.getXVelocity();

                if (velocityX > SNAP_VELOCITY && curScreen > 0) {
                    snapToScreen(curScreen - 1);
                } else if (velocityX < -SNAP_VELOCITY && curScreen < (getChildCount() - 1)) {
                    snapToScreen(curScreen + 1);
                }
                //以上为快速移动的 ，强制切换屏幕
                else {
                    //我们是缓慢移动的，因此先判断是保留在本屏幕还是到下一屏幕
                    snapToDestination();
                }

                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                mTouchState = TOUCH_STATE_REST;


                break;
            case MotionEvent.ACTION_CANCEL:
                strAction = "ACTION_CANCEL";
                mTouchState = TOUCH_STATE_REST;
                break;
        }
        System.out.println("onTouchEvent action=" + strAction + ",x=" + x
                + ",y=" + y);

        return true;
    }

    private void snapToDestination() {

        int destScreen = (getScrollX() + getWidth() / 2) / getWidth();

        snapToScreen(destScreen);
    }

    private void snapToScreen(int whichScreen) {

        curScreen = whichScreen;

        if (curScreen > getChildCount() - 1)
            curScreen = getChildCount() - 1;

        int dx = curScreen * getWidth() - getScrollX();


        mScroller.startScroll(getScrollX(), 0, dx, 0, Math.abs(dx) * 2);

        invalidate();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        int maxWidthSize = 0;
        int maxHeightSize = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            maxWidthSize = Math.max(maxWidthSize, child.getMeasuredWidth());
            maxHeightSize = Math.max(maxHeightSize, child.getMeasuredHeight());

        }

        setMeasuredDimension(Math.min(maxWidthSize, widthSize), Math.min(maxHeightSize, heightSize));

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("left=" + left);

        if (changed) {
            int childCount = getChildCount();
            int childLeft = 0;
            int childTop = 0;
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                System.out.println("childleft=" + childLeft);
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                child.layout(childLeft + layoutParams.leftMargin,
                        childTop + layoutParams.topMargin, childLeft + layoutParams.leftMargin
                                + child.getMeasuredWidth(), childTop + layoutParams.topMargin
                                + child.getMeasuredHeight());
                childLeft = childLeft + getWidth();
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

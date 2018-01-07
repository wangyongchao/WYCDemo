
package com.weishop.test.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;


/**
 * 自动换行
 */
public class AutoLineLayout extends ViewGroup {

    private static float DEFAULT_HORITION_INTERVAL = 10f;
    private static float DEFAULT_VERTICAL_INTERVAL = 10f;
    private int mHoritionInterval;//水平间隔
    private int mVerticalInterval;//垂直间隔

    public AutoLineLayout(Context context) {
        this(context, null);
    }

    public AutoLineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoLine);

        mHoritionInterval = (int) a.getDimension(R.styleable.AutoLine_horition_interval, DEFAULT_HORITION_INTERVAL);
        mVerticalInterval = (int) a.getDimension(R.styleable.AutoLine_vertical_interval, DEFAULT_VERTICAL_INTERVAL);

        a.recycle();
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    /**
     * @param widthMeasureSpec  父元素允许此布局的最大宽度
     * @param heightMeasureSpec 父元素允许此布局的最大最高度
     *                          父元素传递过来的测量值已经去掉了margin,padding
     *                          <p>
     *                          父元素已经根据子元素的width和height属性，设置对应的mode，参考viewgroup 中的getChildMeasureSpec方法.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int withdmode = MeasureSpec.getMode(widthMeasureSpec);
        int withdsize = MeasureSpec.getSize(widthMeasureSpec);
        System.out.println("withdmode=" + TestUtils.getStringMeasureMod(withdmode) + ",withdsize=" + withdsize);

        int heightdmode = MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        System.out.println("heightdmode=" + TestUtils.getStringMeasureMod(heightdmode) + ",heightsize=" + heightsize);

        int lineWidth = 0;
        int maxWidth = 0;
        int lineHeight = 0;
        int maxHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);//测量所有的子view的宽高.
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                int childWithMargin = layoutParams.leftMargin + child.getMeasuredWidth() + layoutParams.rightMargin;
                int childHeightMargin = layoutParams.topMargin + child.getMeasuredHeight() + layoutParams.bottomMargin;
                if (lineWidth + childWithMargin <= withdsize) {
                    lineWidth = lineWidth + childWithMargin+mHoritionInterval;
                    lineHeight = Math.max(childHeightMargin, lineHeight);//每次取最高的子view的高度。
                } else {
                    maxWidth = Math.max(lineWidth-mHoritionInterval, maxWidth);//每次换行要取最大宽度,最右边元素不需要间隔减去
                    lineWidth = childWithMargin;
                    maxHeight = maxHeight + lineHeight + mVerticalInterval;//每次换行要取最大行高和间隔，然后相加.
                    lineHeight = childHeightMargin;

                }
                if (i == childCount - 1) { //最后一行也得算上高度
                    maxHeight = maxHeight + lineHeight;//加上最后一行高度,没有间距
                    maxWidth = Math.max(maxWidth, lineWidth);//取以前行数最大宽度和最后一行宽度比较，取最大值
                }
            }
        }
        setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec), resolveSize(maxHeight, heightMeasureSpec));

//      setMeasuredDimension(withdsize,heightsize);//不能这样设置，如果这样设置的话，
// 此控件的宽度和高度设置始终充满父元素给予的空间,
// 倘若父元素给的值是1080，但是实际此控件所有子view加起来不足以这么宽，也会填充屏幕

    }

    /**
     * Called from layout when this view should
     * assign a size and position to each of its children.
     * <p>
     * Derived classes with children should override
     * this method and call layout on each of
     * their children.
     *
     * @param changed This is a new size or position for this view
     * @param left    Left position, relative to parent
     * @param top     Top position, relative to parent
     * @param right   Right position, relative to parent
     * @param bottom  Bottom position, relative to parent
     */

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        int childLeft = 0;
        int childTop = 0;
        int maxLineHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                int childWithMargin = layoutParams.leftMargin + child.getMeasuredWidth() + layoutParams.rightMargin;
                int childHeightMargin = layoutParams.topMargin + child.getMeasuredHeight() + layoutParams.bottomMargin;
                if (childLeft + childWithMargin <= right) {
                    child.layout(childLeft + layoutParams.leftMargin, childTop + layoutParams.topMargin,
                            childLeft + layoutParams.leftMargin + child.getMeasuredWidth(), childTop + layoutParams.topMargin + child.getMeasuredHeight());
                    childLeft = childLeft + childWithMargin+mHoritionInterval;
                    maxLineHeight = Math.max(maxLineHeight, childHeightMargin);
                } else {
                    childLeft = 0;
                    childTop = childTop + maxLineHeight + mVerticalInterval;
                    child.layout(childLeft + layoutParams.leftMargin, childTop + layoutParams.topMargin,
                            childLeft + layoutParams.leftMargin + child.getMeasuredWidth(), childTop + layoutParams.topMargin + child.getMeasuredHeight());
                    childLeft = childLeft + childWithMargin+mHoritionInterval;
                    maxLineHeight = childHeightMargin;
                }
            }

        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

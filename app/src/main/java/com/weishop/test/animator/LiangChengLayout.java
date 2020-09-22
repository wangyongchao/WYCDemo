
package com.weishop.test.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * 连乘布局及动效
 */
public class LiangChengLayout extends RelativeLayout {
    private Context mContext;
    private LayoutInflater inflater;
    private int key;
    private LayoutTransition mLayoutTransition;
    private int itemInterval;
    private int itemHeight;
    private ArrayList<ItemInfo> displayElements = new ArrayList<>();//界面上显示的顺序
    private AnimatorActivity animatorActivity;
    private boolean isMoveAnimaEnd = false;

    public LiangChengLayout(Context context) {
        this(context, null);
    }

    public LiangChengLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public LiangChengLayout(@NonNull Context context, @Nullable AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        animatorActivity = (AnimatorActivity) mContext;

        inflater = LayoutInflater.from(mContext);
        mLayoutTransition = new LayoutTransition();
        setLayoutTransition(mLayoutTransition);
        //添加view时候动效
        mLayoutTransition.disableTransitionType(LayoutTransition.APPEARING);

        //删除view时候动效
        mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, removeAnimation());
        mLayoutTransition.setDuration(LayoutTransition.DISAPPEARING, 400);

        itemInterval = TestUtils.dip2px(this.getContext(), 4);
        itemHeight = TestUtils.dip2px(this.getContext(), 30);
        setChildrenDrawingOrderEnabled(true);


    }

    public void handleNewData(String key, int number) {
        if (!displayElements.isEmpty()) {
            boolean isExist = false;
            for (int i = 0; i < displayElements.size(); i++) {
                ItemInfo itemInfo = displayElements.get(i);
                if (itemInfo.key.equals(key)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                updateElement(key, number);
            } else {
                //如果小于4个直接插入
                if (displayElements.size() < 4) {
                    insertElement(key, number);
                } else {
                    //大于4个，和最后一个比较是否有资格显示在屏幕上
                    ItemInfo itemInfo = displayElements.get(displayElements.size() - 1);
                    if (itemInfo.number < number) {
                        removeLastElement();
                        insertElement(key, number);
                    } else {
                        notifyNextData();
                    }

                }

            }
        } else {
            //没有元素走插入新元素逻辑
            insertElement(key, number);
        }

    }


    /**
     * 插入一个元素
     */
    public void insertElement(String key, int number) {
        View view = inflater.inflate(R.layout.view_liancheng_item, null);
        TextView keyTextView = view.findViewById(R.id.item_key);
        TextView numTextView = view.findViewById(R.id.item_number);
        keyTextView.setText(String.valueOf(key));
        numTextView.setText("x" + number);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, itemHeight);
        if (getChildCount() != 0) {
            //根据top margin控制在屏幕上的显示位置
            layoutParams.topMargin = getChildCount() * (itemHeight + itemInterval);
        }
        addView(view, layoutParams);
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.key = key;
        itemInfo.mMarginTop = layoutParams.topMargin;
        itemInfo.view = view;
        itemInfo.number = number;
        itemInfo.numTextView = numTextView;
        view.setTag(itemInfo);
        displayElements.add(itemInfo);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.setPivotX(view.getWidth());
                view.setPivotY(view.getHeight());
//                decideDrawOrder();
                insertAnimation(view);
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


    }

    private void decideDrawOrder() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            ItemInfo tag = (ItemInfo) view.getTag();
            tag.drawOrder = i;
        }
    }

    /**
     * 移除最后一个元素
     */
    public void removeLastElement() {
        if (!displayElements.isEmpty()) {
            ItemInfo itemInfo = displayElements.get(displayElements.size() - 1);
            LogUtils.d("key=" + itemInfo.key + ",marginTop=" + itemInfo.mMarginTop);
            removeView(itemInfo.view);
            displayElements.remove(itemInfo);
        }

    }

    /**
     * 更新以后，需要重新排序
     *
     * @param key
     * @param number
     */
    public void updateElement(String key, int number) {
        ItemInfo itemInfo = null;
        int from = -1, to = -1;
        for (int i = 0; i < displayElements.size(); i++) {
            ItemInfo temptInfo = displayElements.get(i);
            if (temptInfo.key.equals(key)) {
                itemInfo = temptInfo;
                to = i;
                break;
            }
        }
        if (itemInfo != null) {
            itemInfo.number = number;
            itemInfo.numTextView.setText("x" + itemInfo.number);
            for (int j = 0; j < to; j++) {
                ItemInfo temptInfo = displayElements.get(j);
                if (temptInfo.number < itemInfo.number) {
                    from = j;
                    break;
                }
            }
        }

        if (from != -1) {
            buildAndStartMoveAnimator(itemInfo, from, to);
        } else {
            //更新，不需要交换位置，因此不需要执行动效
            notifyNextData();

        }


    }

    @Override
    protected int getChildDrawingOrder(int childCount, int drawingPosition) {
        int childDrawingOrder = super.getChildDrawingOrder(childCount, drawingPosition);
        if(isMoveAnimaEnd){
            childDrawingOrder =
                    displayElements.get(childCount - 1 - drawingPosition).drawOrder;
            LogUtils.d("getChildDrawingOrder childCount=" + childCount
                    + ",drawingPosition=" + drawingPosition + ",childDrawingOrder=" + childDrawingOrder);
        }

        return super.getChildDrawingOrder(childCount, drawingPosition);
    }

    private void notifyNextData() {
        animatorActivity.notifyNext();
    }

    /**
     * 构造并启动上下移动的动效
     *
     * @param needMoveItem
     * @param from
     * @param to
     */
    private void buildAndStartMoveAnimator(ItemInfo needMoveItem, int from, int to) {
        isMoveAnimaEnd = false;
        AnimatorSet animatorSet = new AnimatorSet();
        List<ItemInfo> itemInfos = displayElements.subList(from, to);
        List<Animator> animators = new ArrayList<>();

        for (int i = 0; i < itemInfos.size(); i++) {
            ItemInfo itemInfo = itemInfos.get(i);
            if (i == 0) {
                //插入的新元素或者元素连乘数发生变化，大于顶部元素，需要往上面移动
                Animator moveUpAnimation =
                        needMoveItem.smoothMarginTopAnmitor(itemInfo.mMarginTop);
                needMoveItem.drawOrder = itemInfo.drawOrder;
                animators.add(moveUpAnimation);
            }

            Animator moveDownAnimation =
                    itemInfo.smoothMarginTopAnmitor(itemInfo.mMarginTop + itemHeight + itemInterval);
            itemInfo.drawOrder = itemInfo.drawOrder - 1;
            animators.add(moveDownAnimation);

        }
        animatorSet.playTogether(animators);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //动效执行完成，需要对元素按照从上到下的位置重新排序
                LogUtils.d("onAnimationEnd");
                Collections.sort(displayElements);
                notifyNextData();
                isMoveAnimaEnd = true;
            }
        });
        animatorSet.setDuration(400);
        animatorSet.start();
    }


    /**
     * 数字放大效果
     */
    private void startNumberScale(View view) {
        ObjectAnimator scalexObjectAnimator = ObjectAnimator.ofFloat(view, "scaleX",
                0f, 1.26f, 1f);
        ObjectAnimator scaleyObjectAnimator = ObjectAnimator.ofFloat(view, "scaleY",
                0f, 1.26f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });
        animatorSet.setDuration(160);
        animatorSet.play(scalexObjectAnimator).with(scaleyObjectAnimator);
        animatorSet.start();
    }

    /**
     * item 删除效果
     *
     * @return
     */
    private Animator removeAnimation() {
        ObjectAnimator scalexObjectAnimator = ObjectAnimator.ofFloat(null, "scaleX",
                1f,
                0f);
        ObjectAnimator scaleyObjectAnimator = ObjectAnimator.ofFloat(null, "scaleY",
                1f,
                0f);
        ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.playTogether(scalexObjectAnimator, scaleyObjectAnimator, alphaObjectAnimator);
        return animatorSet;

    }


    /**
     * item 进场效果
     *
     * @return
     */
    private Animator insertAnimation(View view) {
        ObjectAnimator scalexObjectAnimator = ObjectAnimator.ofFloat(view, "scaleX",
                0f, 1f);
        ObjectAnimator scaleyObjectAnimator = ObjectAnimator.ofFloat(view, "scaleY",
                0f, 1f);
        ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.playTogether(scalexObjectAnimator, scaleyObjectAnimator, alphaObjectAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //插入动效完成以后需要判断是否需要交换
                afterInsertCompare();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        animatorSet.start();
        return animatorSet;

    }

    /**
     * 插入新的连乘以后，需要按照连乘多少执行移动动效，进行排序
     */
    private void afterInsertCompare() {
        ItemInfo lastItemInfo = displayElements.get(displayElements.size() - 1);
        int from = -1;
        //插入元素后，查找需要移动的顶部元素的位置
        for (int i = 0; i < displayElements.size() - 1; i++) {
            ItemInfo itemInfo = displayElements.get(i);
            if (lastItemInfo.number > itemInfo.number) {
                from = i;
                break;
            }

        }
        if (from != -1) {
            buildAndStartMoveAnimator(lastItemInfo, from, displayElements.size() - 1);
        } else {
            //不需要移动元素
            notifyNextData();
        }


    }

    /**
     * 按照margintop排序，margtop最大的排在最下面
     */

    class ItemInfo implements Comparable<ItemInfo> {
        public View view;
        private int mMarginTop;
        public String key;
        public int number;
        public TextView numTextView;
        public int drawOrder;


        public void setMarginTop(int marginTop) {
            RelativeLayout.LayoutParams layoutParams =
                    (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.topMargin = marginTop;
            view.setLayoutParams(layoutParams);
        }


        public Animator smoothMarginTopAnmitor(int marginTop) {
            ObjectAnimator animation = ObjectAnimator.ofInt(this, "marginTop", mMarginTop,
                    marginTop);
            //先加速在减速
            this.mMarginTop = marginTop;
            return animation;
        }

        public int getMarginTop() {
            LogUtils.d("getMarginTop");
            return mMarginTop;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ItemInfo itemInfo = (ItemInfo) o;
            return key.equals(itemInfo.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public int compareTo(ItemInfo other) {
            if (this.mMarginTop > other.mMarginTop) {
                return 1;
            } else if (this.mMarginTop < other.mMarginTop) {
                return -1;
            }
            return 0;
        }
    }


}

package com.weishop.test.performance;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.weishop.test.R;

import java.util.ArrayList;
import java.util.List;

public class DroidCardsView extends View {
    //图片与图片之间的间距
    private int mCardSpacing = 100;
    //图片与左侧距离的记录
    private int mCardLeft = 10;

    private List<DroidCard> mDroidCards = new ArrayList<DroidCard>();

    private Paint paint = new Paint();

    public DroidCardsView(Context context) {
        super(context);
        initCards();
    }

    public DroidCardsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCards();
    }


    /**
     * 初始化卡片集合
     */
    protected void initCards() {
        Resources res = getResources();
        mDroidCards.add(new DroidCard(res, R.drawable.goods_shop_share_qq, mCardLeft));

        mCardLeft += mCardSpacing;
        mDroidCards.add(new DroidCard(res, R.drawable.goods_shop_share_qzone, mCardLeft));

        mCardLeft += mCardSpacing;
        mDroidCards.add(new DroidCard(res, R.drawable.goods_shop_share_wechat, mCardLeft));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mDroidCards.size() - 1; i++) {
            DroidCard droidCard = mDroidCards.get(i);
            drawDroidCard(canvas, droidCard, i);
        }
        int last = mDroidCards.size() - 1;
        if (last >= 0) {
            drawDroidCard(canvas, mDroidCards.get(last));
        }

//        for (int i = 0; i < mDroidCards.size(); i++) {
//            DroidCard droidCard = mDroidCards.get(i);
//            drawDroidCard(canvas, droidCard);
//        }

        invalidate();
    }

    /**
     * 绘制DroidCard
     *
     * @param canvas
     * @param c
     */
    private void drawDroidCard(Canvas canvas, DroidCard c, int i) {
        //  canvas.drawBitmap(c.bitmap,c.x,0f,paint);
        canvas.save();
        canvas.clipRect(c.x, 0.0f, mDroidCards.get(i + 1).x, c.height);
        canvas.drawBitmap(c.bitmap, c.x, 0f, paint);
        canvas.restore();//裁剪和绘制完毕后恢复画布
    }


    /**
     * 绘制最后一张
     *
     * @param canvas
     * @param c
     */
    private void drawDroidCard(Canvas canvas, DroidCard c) {
        canvas.drawBitmap(c.bitmap, c.x, 0f, paint);

    }
}
package com.weishop.test.performance;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DroidCard {
    public int x;
    public Bitmap bitmap;
    public int height;

    public DroidCard(Resources res, int app_to_customer, int mCardLeft) {
        this.x = mCardLeft;
        this.bitmap = BitmapFactory.decodeResource(res, app_to_customer);
        height = this.bitmap.getHeight();

    }
}

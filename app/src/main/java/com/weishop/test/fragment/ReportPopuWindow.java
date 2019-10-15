package com.weishop.test.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

public class ReportPopuWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private View mMenuView;
    private LayoutInflater inflater;

    public ReportPopuWindow(final Context context) {
        super(context);
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.item_view, null);


        this.setContentView(mMenuView);
        //sdk > 21 解决 标题栏没有办法遮罩的问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setClippingEnabled(false);
        }
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);//屏幕的高
//        this.setHeight(TestUtils.getScreenHeight(context));//屏幕的高
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);
    }


    @Override
    public void onClick(View arg0) {
    }


}
package com.weishop.test.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.weishop.test.R;

/**
 * Created by wangyongchao on 2018/5/8.
 */

public class FeedBackWindowManager {
    private final static FeedBackWindowManager windowManagerWrapper = new FeedBackWindowManager();
    private boolean isShown;
    private final LayoutInflater inflater;
    private WindowManager mWindowManager;
    private View feedBackView;
    private ImageView imageView;//要分享的图片
    private WindowManager.LayoutParams wmParams;

    private FeedBackWindowManager() {
        mWindowManager = (WindowManager) TestUtils.applicationContext.getSystemService(Context
                .WINDOW_SERVICE);
        inflater = LayoutInflater.from(TestUtils.applicationContext);

        initParmas();

    }

    private void initParmas() {
        wmParams = new WindowManager.LayoutParams();
//        wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        if (Build.VERSION.SDK_INT >= 24) { /*android7.0不能用TYPE_TOAST*/
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        } else { /*以下代码块使得android6.0之后的用户不必再去手动开启悬浮窗权限*/
            String packname = TestUtils.applicationContext.getPackageName();
            PackageManager pm = TestUtils.applicationContext.getPackageManager();
            boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.SYSTEM_ALERT_WINDOW", packname));
            if (permission) {
                wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {
                wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
        }

        //设置图片格式，效果为背景透明
//        wmParams.format =PixelFormat.TRANSPARENT;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;

        DisplayMetrics dm = new DisplayMetrics();
        //以屏幕左上角为原点，设置x、y初始值，相对于gravity
//        wmParams.x = SizeUtils.Dp2Px(ContextManager.getContext(), 20);
//        wmParams.y = SizeUtils.Dp2Px(ContextManager.getContext(), 120);
        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

    }

    public static FeedBackWindowManager getInstance() {
        return windowManagerWrapper;
    }

    private void setUpFeedBackView(String imagePath) {
        if (feedBackView == null) {
            feedBackView = inflater.inflate(R.layout.activity_a, null);
        }

    }


    public void showWindow(String imagePath) {
        setUpFeedBackView(imagePath);
        isShown = true;
        mWindowManager.addView(feedBackView, wmParams);
    }

    /**
     * 隐藏弹出框
     */
    public void hidePopupWindow() {
        if (isShown && null != feedBackView) {
            mWindowManager.removeView(feedBackView);
            isShown = false;
        }

    }


}

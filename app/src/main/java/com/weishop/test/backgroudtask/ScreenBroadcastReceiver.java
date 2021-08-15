package com.weishop.test.backgroudtask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.weishop.test.activitycharacter.CActivity;
import com.weishop.test.util.LogUtils;

import static com.weishop.test.util.NotificationUitlsKt.createIntent;

public class ScreenBroadcastReceiver extends BroadcastReceiver {

    private final static String TAG = "ScreenBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtils.d("广播Action = " + action);
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            LogUtils.d("锁屏");
        } else if (action.equals(Intent.ACTION_SCREEN_ON)) {
            LogUtils.d("解锁");
            createIntent(context);
        } else if (action.equals(Intent.ACTION_USER_PRESENT)) {
            LogUtils.d("开屏");
        }
    }

    private void start(Context context){
        Intent activityIntent = new Intent(context, CActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
    }


}
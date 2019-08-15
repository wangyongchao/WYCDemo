/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weishop.test.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.weishop.test.R;
import com.weishop.test.activitycharacter.AActivity;

public class LocalService extends Service {
    private NotificationManager mNM;

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private int NOTIFICATION = R.string.local_service_started;

    Binder binder = new LocalBinder();

    /**
     * Class for clients to access. Because we know this service always runs in
     * the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }

        public void startDownload() {
            System.out.println("startDownload:" + Thread.currentThread().getName());
        }
    }

    public LocalService() {
        System.out.println("LocalService");
    }

    @Override
    public void onCreate() {
        System.out.println("LocalService onCreate");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Display a notification about us starting. We put an icon in the
        // status bar.
//        showNotification();
    }


    MyAIDLService.Stub stub = new MyAIDLService.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            System.out.println("nullll");
        } else {
            System.out.println("LocalService onStartCommand intent=" + intent + ",flags=" + flags
                    + ",startId=" + startId + ",name=" + intent.getStringExtra("name"));
        }

        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        System.out.println("LocalService onDestroy");
        // Cancel the persistent notification.
//        mNM.cancel(NOTIFICATION);

        // Tell the user we stopped.
//        Toast.makeText(this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        System.out.println("LocalService onRebind");
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("LocalService onBind binder="+binder);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("LocalService onUnbind");
        return true;
    }

    // This is the object that receives interactions from clients. See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the
        // expanded notification
        CharSequence text = getText(R.string.local_service_started);

        // The PendingIntent to launch our activity if the user selects this
        // notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                AActivity.class), 0);

        Notification noti = new Notification.Builder(this).setContentTitle(text)
                .setContentText(getText(R.string.hello_blank_fragment))
                .setSmallIcon(R.drawable.ic_launcher).setWhen(System.currentTimeMillis()).build();

        // Send the notification.
        mNM.notify(NOTIFICATION, noti);

        startForeground(NOTIFICATION, noti);
    }

}

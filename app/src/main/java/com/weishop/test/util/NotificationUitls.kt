package com.weishop.test.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.weishop.test.R
import com.weishop.test.activitycharacter.CActivity
import java.lang.Exception

val NOTIFICATION_CHANNEL_NORMAL = "normal"

fun createIntent(context: Context) {
    val fullScreenIntent = Intent(context, CActivity::class.java)
    fullScreenIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    fullScreenIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    val fullScreenPendingIntent = PendingIntent.getActivity(
        context, 25, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT
    )
    val notificationBuilder =
        NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_NORMAL).setSmallIcon(R.drawable.stat_notify_calendar)
            .setContentTitle("Incoming call").setContentText("(919) 555-1234")
            .setPriority(NotificationCompat.PRIORITY_HIGH).setCategory(NotificationCompat.CATEGORY_CALL)
            .setFullScreenIntent(fullScreenPendingIntent, true)
    val incomingCallNotification = notificationBuilder.build()
    val notificationManager = context.getSystemService(NotificationManager::class.java)
    initChannel(context)
    notificationManager.notify(55, incomingCallNotification)
}

private fun initChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        try {
            val notificationManager =
                context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            if (notificationManager != null) {
                var normalChannel = notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_NORMAL)
                if (normalChannel == null) {
                    normalChannel = NotificationChannel(
                        NOTIFICATION_CHANNEL_NORMAL,
                        context.getString(R.string.app_name),
                        NotificationManager.IMPORTANCE_LOW
                    )
                    notificationManager.createNotificationChannel(normalChannel)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


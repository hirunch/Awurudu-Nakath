package com.s22010120.timetest;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class NakathNotificationReceiver extends BroadcastReceiver {

    static final String EXTRA_TITLE = "extra_title";
    static final String EXTRA_BODY = "extra_body";
    static final String EXTRA_NOTIFICATION_ID = "extra_notification_id";

    private static final String CHANNEL_ID = "nakath_alerts";

    @Override
    public void onReceive(Context context, Intent intent) {
        createChannel(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        String title = intent.getStringExtra(EXTRA_TITLE);
        String body = intent.getStringExtra(EXTRA_BODY);
        int notificationId = intent.getIntExtra(EXTRA_NOTIFICATION_ID, 0);

        if (title == null) {
            title = "Awurudu Nakath";
        }
        if (body == null) {
            body = "Nakath reminder";
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat.from(context).notify(notificationId, builder.build());
    }

    private void createChannel(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Nakath Alerts",
                NotificationManager.IMPORTANCE_HIGH
        );
        channel.setDescription("Nakath reminder and start alerts");

        NotificationManager manager = context.getSystemService(NotificationManager.class);
        if (manager != null) {
            manager.createNotificationChannel(channel);
        }
    }
}



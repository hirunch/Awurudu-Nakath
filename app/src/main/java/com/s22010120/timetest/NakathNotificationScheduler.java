package com.s22010120.timetest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class NakathNotificationScheduler {

    private static final long REMINDER_OFFSET_MS = 60_000L;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String EXTRA_TITLE = "extra_title";
    private static final String EXTRA_BODY = "extra_body";
    private static final String EXTRA_NOTIFICATION_ID = "extra_notification_id";

    private NakathNotificationScheduler() {
    }

    public static void scheduleAll(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) {
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
        long now = System.currentTimeMillis();

        for (int i = 0; i < NakathSchedule.TIMES.length; i++) {
            try {
                Date eventDate = dateFormat.parse(NakathSchedule.TIMES[i]);
                if (eventDate == null) {
                    continue;
                }

                long eventTimeMs = eventDate.getTime();
                long reminderTimeMs = eventTimeMs - REMINDER_OFFSET_MS;

                scheduleAlarm(context, alarmManager, i, true, reminderTimeMs, now);
                scheduleAlarm(context, alarmManager, i, false, eventTimeMs, now);
            } catch (Exception ignored) {
            }
        }
    }

    private static void scheduleAlarm(
            Context context,
            AlarmManager alarmManager,
            int index,
            boolean isReminder,
            long triggerAtMillis,
            long now
    ) {
        if (triggerAtMillis <= now) {
            return;
        }

        String title = NakathSchedule.TITLES[index];
        String body = isReminder ?
                title + " starts in 60 seconds." :
                title + " has started now.";

        int requestCode = index * 10 + (isReminder ? 1 : 2);

        Intent intent = new Intent();
        intent.setClassName(context, "com.s22010120.timetest.NakathNotificationReceiver");
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_BODY, body);
        intent.putExtra(EXTRA_NOTIFICATION_ID, requestCode);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        alarmManager.cancel(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
            return;
        }

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }
}



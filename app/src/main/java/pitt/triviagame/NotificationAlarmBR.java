package pitt.triviagame;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class NotificationAlarmBR extends BroadcastReceiver {

    @Override
    /** When alarm broadcast is received, wake and send notification */
    public void onReceive(Context context, Intent intent) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Trivia Lock");
        wakeLock.acquire();
        new TriviaGame().sendNotification(context);
        wakeLock.release();
    }

    /** Create notification alarm at specified time and time interval */
    public void SetAlarm(Context context) {
        int hour = 10;  // TODO: implement to change in settings

        // Calendar setup
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (calendar.get(Calendar.HOUR_OF_DAY) >= hour) // If current time has passed alarm time, set for tomorrow
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);

        // Alarm Manager setup
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationAlarmBR.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // Start at the set notification hour and repeat every 24 hours
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), (1000 * 60 * 60 * 24), pendingIntent);
    }

    // TODO turn off alarm if notification disabled in settings
    /** Disables the notification alarm */
    public void CancelAlarm(Context context) {
        Intent intent = new Intent(context, NotificationAlarmBR.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}

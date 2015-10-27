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
    // When alarm broadcast is received...
    public void onReceive(Context context, Intent intent) {
        // Create a temporary partial wake from lock to push notification through
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Trivia Lock");
        wakeLock.acquire(); // Acquire the lock
        new TriviaGame().sendNotification(context); // Trigger sending notification, pass context
        wakeLock.release(); // Release the lock
    }

    // Creates the notification alarm
    public void SetAlarm(Context context) {
        int hour = 10;  // TODO implement to change in settings
        GregorianCalendar calendar = new GregorianCalendar(); // Create gregorian calendar
        calendar.setTimeInMillis(System.currentTimeMillis()); // Set date to current system time
        // If the current time is past the alarm time then set alarm for tomorrow,
        // otherwise the alarm will think it has passed and end up triggering on the next minute
        if (calendar.get(Calendar.HOUR_OF_DAY) >= hour)
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
        // Now set calendar time to the set notification hour at 0 minutes 0 seconds
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // Setup the alarm manager
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationAlarmBR.class);
        // Set the intent to broadcast
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        // Start at the set notification hour and repeat every 24 hours
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), (1000 * 60 * 60 * 24), pendingIntent);
    }

    // TODO turn off alarm if notification disabled in settings
    // Disables the notification alarm
    public void CancelAlarm(Context context) {
        Intent intent = new Intent(context, NotificationAlarmBR.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}

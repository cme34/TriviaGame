package pitt.triviagame;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

public class NotificationAlarmBR extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Create a temporary partial wake from lock to push notification through
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Trivia Lock");
        wakeLock.acquire();
        Toast.makeText(context, "Test", Toast.LENGTH_LONG).show();  // Test due to object reference error in notification instance
        //	new TriviaGame().sendNotification();
        wakeLock.release();
    }

    public void SetAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationAlarmBR.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000, pendingIntent);
    }

    public void CancelAlarm(Context context) {
        Intent intent = new Intent(context, NotificationAlarmBR.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}

    /*
    //// RUN FROM TRIVIAGAME.JAVA
    // Start 1 minute background alarm
    Context context = this.getApplicationContext();
    alarm.CancelAlarm(context);
    // Cancel background alarm
    Context context = this.getApplicationContext();
    alarm.SetAlarm(context);
    */

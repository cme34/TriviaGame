package pitt.triviagame;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Cory on 10/8/2015.
 * This is the main menu page of the game
 */

public class TriviaGame extends AppCompatActivity {
    /**
     * Creates the main menu screen and all of its components
     */
    private NotificationAlarmBR alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);
        alarm = new NotificationAlarmBR();
    }

    /**
     * Populates the options when clicked
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trivia_game, menu);
        return true;
    }

    /**
     * Auto generated, no yet implemented
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Takes the user from the main menu to the quiz screen to answer questions
     */
    public void onClickPlayButton(View view) {
        Intent getQuizScreenIntent = new Intent(this, QuizScreen.class);
        startActivity(getQuizScreenIntent);
    }

    /**
     * Ends the program when the QUIT button is clicked
     */
    public void onClickQuitButton(View view) {
        sendNotification();
        finish();
    }

    public void sendNotification() {
        // Notification Tap
        Intent intent = new Intent(this, TriviaGame.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Construct Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setContentTitle("New Questions Available!");
        builder.setContentText("Tap to go answer them!");
        builder.setSubText("");

        // Push Notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}

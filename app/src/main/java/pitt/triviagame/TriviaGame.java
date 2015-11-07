package pitt.triviagame;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Cory on 10/8/2015.
 * This is the main menu page of the game
 */

public class TriviaGame extends AppCompatActivity {
    private Button playButton, leaderBoardButton, quitButton;
    private ImageButton profileButton;
    private TextView usernameView;

    /**
     * This method is essentially a constructor. It initializes the trivia game (main menu screen)
     * Also it unpacks some data from the sign in screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);
        new NotificationAlarmBR().SetAlarm(this.getApplicationContext());
        Intent activityCalled = getIntent();
        playButton = (Button) findViewById(R.id.triviaGamePlayButton);
        leaderBoardButton = (Button) findViewById(R.id.triviaGameLeaderBoardButton);
        quitButton = (Button) findViewById(R.id.triviaGameQuitButton);
        profileButton = (ImageButton) findViewById(R.id.triviaGameProfileButton);
        usernameView = (TextView) findViewById(R.id.triviaGameUsernameTextView);
        usernameView.setText(User.loggedInUser.getUsername());
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
     * On Play Button clicked, takes the user from the main menu to the quiz screen to answer questions
     */
    public void onClickPlayButton(View view) {
        Intent getQuizScreenIntent = new Intent(this, QuizScreen.class);
        startActivity(getQuizScreenIntent);
    }

    /**
     * On Leader Board Button clicked, takes the user from the main menu to the leader board to see other peoples scores
     */
    public void onClickLeaderBoardButton(View view) {
        Intent getLeaderBoardScreenIntent = new Intent(this, LeaderBoardScreen.class);
        startActivity(getLeaderBoardScreenIntent);
    }

    /**
     * On Settings Button clicked, takes the user from the main menu to settings to configure settings
     */
    public void onClickSettingsButton(View view) {
        Intent getSettingsScreen = new Intent(this, SettingsScreen.class);
        startActivity(getSettingsScreen);
    }

    /**
     * On Quit Button Clicked, ends the program
     */
    public void onClickQuitButton(View view) {
        finish();
    }

    /**
     * Push notification from timed alarm
     * @param context
     */
    public void sendNotification(Context context) {
        // Notification Tap
        Intent intent = new Intent(context, TriviaGame.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Construct Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setContentTitle("New Questions Available!");
        builder.setContentText("Tap to go answer them!");
        builder.setSubText("");

        // Push Notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}

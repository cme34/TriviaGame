package pitt.triviagame;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Cory on 11/5/2015.
 * This page allows the user to view their points as well as the top five people with the most points
 */
public class LeaderBoardScreen extends Activity {
    private TextView currentUserScore;
    private TextView[] usernameView;
    private TextView[] scoreView;
    private Button okayButton;

    /**
     * This method is essentially a constructor. It initializes the leader board screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        usernameView = new TextView[5];
        scoreView = new TextView[5];
        currentUserScore = (TextView) findViewById(R.id.leaderBoardScreenScoreTextView);
        usernameView[0] = (TextView) findViewById(R.id.leaderBoardScreenUser1TextView);
        usernameView[1] = (TextView) findViewById(R.id.leaderBoardScreenUser2TextView);
        usernameView[2] = (TextView) findViewById(R.id.leaderBoardScreenUser3TextView);
        usernameView[3] = (TextView) findViewById(R.id.leaderBoardScreenUser4TextView);
        usernameView[4] = (TextView) findViewById(R.id.leaderBoardScreenUser5TextView);
        scoreView[0] = (TextView) findViewById(R.id.leaderBoardScreenScore1TextView);
        scoreView[1] = (TextView) findViewById(R.id.leaderBoardScreenScore2TextView);
        scoreView[2] = (TextView) findViewById(R.id.leaderBoardScreenScore3TextView);
        scoreView[3] = (TextView) findViewById(R.id.leaderBoardScreenScore4TextView);
        scoreView[4] = (TextView) findViewById(R.id.leaderBoardScreenScore5TextView);
        okayButton = (Button) findViewById(R.id.leaderBoardScreenOkayButton);

        currentUserScore.setText("" + User.loggedInUser.getPoints());

        obtainLeaderBoardValues();
    }

    /**
     * On Okay Button clicked, returns to the main menu (Trivia Game Activity)
     */
    public void onClickOkayButton(View view) { finish(); }

    /**
     * This method gets the current leader board results for the database and sets all the text views accordingly
     */
    private void obtainLeaderBoardValues() { // TODO: DATABASE NEEDED

    }
}

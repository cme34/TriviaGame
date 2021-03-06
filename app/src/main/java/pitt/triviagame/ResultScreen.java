package pitt.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Cory on 10/8/2015.
 * This is the page of the game where the user can see their score and can choose to go back to the main menu or review their answers
 */
public class ResultScreen extends AppCompatActivity {
    private TextView scoreView;
    private Button okayButton, reviewButton;

    private int score;
    private Question[] questions;//TRANSFERS TO REVIEW SCREEN
    /** Used to keep track of what questions the user got right */
    private boolean[] gotQuestionRight;//TRANSFERS TO REVIEW SCREEN
    /** Used to keep track of the answers the user selected */
    private String[] usersAnswer;//TRANSFERS TO REVIEW SCREEN

    /**
     * This method is essentially a constructor. It initializes the result screen
     * Also, it unpacks the data sent from the quiz activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent activityCalled = getIntent();

        questions = new Question[QuizScreen.QUESTION_LIMIT];
        gotQuestionRight = new boolean[QuizScreen.QUESTION_LIMIT];
        usersAnswer = new String[QuizScreen.QUESTION_LIMIT];

        //Begin unpacking data
        for (int i = 0; i < QuizScreen.QUESTION_LIMIT; i++)
        {
            questions[i] = new Question();
            questions[i].setQuestion(activityCalled.getExtras().getString("questions[" + i + "].getQuestion()"));
            questions[i].setAnswer(activityCalled.getExtras().getString("questions[" + i + "].getAnswer()"));
            questions[i].setFakeAnswer1(activityCalled.getExtras().getString("questions[" + i + "].getFakeAnswer1()"));
            questions[i].setFakeAnswer2(activityCalled.getExtras().getString("questions[" + i + "].getFakeAnswer2()"));
            questions[i].setFakeAnswer3(activityCalled.getExtras().getString("questions[" + i + "].getFakeAnswer3()"));
            questions[i].setCategory(Question.convertStringToCategory(activityCalled.getExtras().getString("questions[" + i + "].getCategory()")));
            gotQuestionRight[i] = activityCalled.getExtras().getBoolean("gotQuestionRight" + i);
            usersAnswer[i] = activityCalled.getExtras().getString("userAnswer" + i);
        }
        score = activityCalled.getExtras().getInt("points");
        //End unpacking data

        scoreView = (TextView) findViewById(R.id.resultScreenPointsTextView);
        scoreView.setText(score + "/" + QuizScreen.QUESTION_LIMIT);
        okayButton = (Button) findViewById(R.id.resultScreenOkayButton);
        reviewButton = (Button) findViewById(R.id.resultScreenReviewButton);

        updateUsersTotalPoints();
    }

    /**
     * On Okay Button clicked, takes the user back to Trivia Game (main menu)
     */
    public void onClickOkayButton(View view)
    {
        finish();
    }

    /**
     * On Review Button clicked, takes the user to the review screen
     * Also, it packages data and sends it to the review screen
     */
    public void onClickReviewButton(View view)
    {
        Intent getResultScreenIntent = new Intent(this, ReviewScreen.class);
        //Begin packing data
        for (int i = 0; i < QuizScreen.QUESTION_LIMIT; i++) {
            getResultScreenIntent.putExtra("questions[" + i + "].getQuestion()", questions[i].getQuestion());
            getResultScreenIntent.putExtra("questions[" + i + "].getAnswer()", questions[i].getAnswer());
            getResultScreenIntent.putExtra("questions[" + i + "].getFakeAnswer1()", questions[i].getFakeAnswer1());
            getResultScreenIntent.putExtra("questions[" + i + "].getFakeAnswer2()", questions[i].getFakeAnswer2());
            getResultScreenIntent.putExtra("questions[" + i + "].getFakeAnswer3()", questions[i].getFakeAnswer3());
            getResultScreenIntent.putExtra("questions[" + i + "].getCategory()", questions[i].getCategory().toString());
            getResultScreenIntent.putExtra("gotQuestionRight" + i, gotQuestionRight[i]);
            getResultScreenIntent.putExtra("userAnswer" + i, usersAnswer[i]);
        }
        //End packing data
        startActivity(getResultScreenIntent);
        finish();
    }

    /**
     * This method updates the users total points by adding the points they got from the quiz they just took to their stored total
     */
    private void updateUsersTotalPoints() {
        //Update score client side
        User.loggedInUser.setPoints(User.loggedInUser.getPoints() + score);

        //Make call to server
        DatabaseHandler task = new DatabaseHandler();
        task.execute(DatabaseHandler.SEND_POINTS, "?" + User.loggedInUser.getUsername() + "?" + User.loggedInUser.getPoints());//Param for sending updated score

        //Wait for server response
        String jsonString = null;
        while (jsonString == null)
            jsonString = task.getJsonString();

        //Create Json object
        JSONObject jsonFile = null;
        boolean sentSuccessful = false;
        try {
            jsonFile = new JSONObject(jsonString);
            //Get if sign in successful from json file
            sentSuccessful = jsonFile.getBoolean("Value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

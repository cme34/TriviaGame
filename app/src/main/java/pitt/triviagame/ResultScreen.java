package pitt.triviagame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Cory on 10/8/2015.
 * This is the page of the game where the user can see their score and can choose to go back to the main menu or review their answers
 */
public class ResultScreen extends Activity {
    private TextView scoreView;
    private Button okayButton;

    private Question[] questions;//TRANSFERS TO REVIEW SCREEN
    /** Used to keep track of what questions the user got right */
    private boolean[] gotQuestionRight;//TRANSFERS TO REVIEW SCREEN
    /** Used to keep track of the answers the user selected */
    private String[] usersAnswer;//TRANSFERS TO REVIEW SCREEN

    /**
     * Creates the result screen and initializes all of its components
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
            System.out.println(questions[i].getQuestion());
            System.out.println("Got Right: " + gotQuestionRight[i]);
            System.out.println("Your Answer: " + usersAnswer[i]);
        }
        //End unpacking data

        scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(activityCalled.getExtras().getInt("points") + "/" + QuizScreen.QUESTION_LIMIT);
        okayButton = (Button) findViewById(R.id.okayButton);
    }

    /** Takes the user back to the main menu when the OKAY button clicked */
    public void onClickOkayButton(View view)
    {
        finish();
    }

    /**
     * Takes the user to the review screen when click
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
}

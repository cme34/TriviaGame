package pitt.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Cory on 10/23/2015.
 */
public class ReviewScreen extends AppCompatActivity {
    private TextView questionView, correctAnswerView, yourAnswerView;
    private Button okayButton, prevButton, nextButton;
    private View answerBackground;

    private int currentQuestion;
    private Question[] questions;
    /** Used to keep track of what questions the user got right */
    private boolean[] gotQuestionRight;
    /** Used to keep track of the answers the user selected */
    private String[] usersAnswer;

    /**
     * This method is essentially a constructor. It initializes the review screen
     * Also, it unpacks the data sent from the result activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent activityCalled = getIntent();

        currentQuestion = 0;

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

        questionView = (TextView) findViewById(R.id.reviewScreenQuestionTextView);
        correctAnswerView = (TextView) findViewById(R.id.reviewScreenCorrectAnswerTextView);
        yourAnswerView = (TextView) findViewById(R.id.reviewScreenYourAnswerTextView);
        answerBackground = (View) findViewById(R.id.reviewScreenAnswerBackground);
        prevButton = (Button) findViewById(R.id.reviewScreenPrevButton);
        nextButton = (Button) findViewById(R.id.reviewScreenNextButton);
        okayButton = (Button) findViewById(R.id.reviewScreenOkayButton);

        changeQuestion();
    }

    /** Takes the user back to the main menu when the OKAY button clicked */
    public void onClickOkayButton(View view)
    {
        finish();
    }

    /**
     * Takes the user to the previous question
     * If on the first question, then nothing happens
     */
    public void onClickPrevButton(View view)
    {
        if (currentQuestion > 0)
        {
            currentQuestion--;
            changeQuestion();
        }
    }

    /**
     * Takes the user to the mext question
     * If on the last question, then nothing happens
     */
    public void onClickNextButton(View view)
    {
        if (currentQuestion < QuizScreen.QUESTION_LIMIT - 1)
        {
            currentQuestion++;
            changeQuestion();
        }
    }

    /** Updates question if the previous or next button is clicked */
    private void changeQuestion()
    {
        questionView.setText(questions[currentQuestion].getQuestion());
        correctAnswerView.setText(questions[currentQuestion].getAnswer());
        yourAnswerView.setText(usersAnswer[currentQuestion]);
        //Sets the background color of the answer to red or green depending on if the answer is right or not
        if (questions[currentQuestion].getAnswer().intern().equals(usersAnswer[currentQuestion].intern()))
            answerBackground.setBackgroundColor(0xff77ff77);
        else
            answerBackground.setBackgroundColor(0xffff0000);
    }
}

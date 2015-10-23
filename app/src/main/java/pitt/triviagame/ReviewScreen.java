package pitt.triviagame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Cory on 10/23/2015.
 */
public class ReviewScreen extends Activity {
    private TextView questionView;
    private TextView correctAnswerView;
    private TextView yourAnswerView;
    private View answerBackground;

    private int currentQuestion;
    private Question[] questions;
    /** Used to keep track of what questions the user got right */
    private boolean[] gotQuestionRight;
    /** Used to keep track of the answers the user selected */
    private String[] usersAnswer;

    /**
     * Creates the review screen and initializes all of its components
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

        questionView = (TextView) findViewById(R.id.questionView);
        correctAnswerView = (TextView) findViewById(R.id.correctAnswerView);
        yourAnswerView = (TextView) findViewById(R.id.yourAnswerView);
        answerBackground = (View) findViewById(R.id.answerBackground);

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
        if (questions[currentQuestion].getAnswer().intern() == usersAnswer[currentQuestion].intern())
            answerBackground.setBackgroundColor(0xff77ff77);
        else
            answerBackground.setBackgroundColor(0xffff0000);
    }
}

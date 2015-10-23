package pitt.triviagame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Cory on 10/8/2015.
 * This is the page of the game where the user answers trivia questions
 */
public class QuizScreen extends Activity {
    /** The amount of questions that need to be answered, obtained from the server, etc. */
    public static final int QUESTION_LIMIT = 5;

    private RadioGroup answerButtonGroup;
    private TextView timeLeftView;
    private TextView questionView;
    private RadioButton[] answerButtons = new RadioButton[4];
    private Button submitButton;

    private int points;//TRANSFERS TO RESULT SCREEN
    private int currentQuestion;
    private boolean outOfTime;
    private QuizTimer timer;
    private Random rand = new Random();

    private Question[] questions;//TRANSFERS TO RESULT SCREEN
    /** Used to keep track of what questions the user got right */
    private boolean[] gotQuestionRight;//TRANSFERS TO RESULT SCREEN
    /** Used to keep track of the answers the user selected */
    private String[] usersAnswer;//TRANSFERS TO RESULT SCREEN

    /**
     * Creates the quiz screen and initializes all of its components
     * Also, it obtains the day's current questions from the database and randomizes their order and creates sets up the first question
     * @param savedInstanceState - Data transfered from the calling activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questions = new Question[QUESTION_LIMIT];
        gotQuestionRight = new boolean[QUESTION_LIMIT];
        usersAnswer = new String[QUESTION_LIMIT];
        for (int i = 0; i < QUESTION_LIMIT; i++)
        {
            questions[i] = new Question();
            gotQuestionRight[i] = false;
            usersAnswer[i] = "";
        }

        obtainQuestionsFromDatabase();
        shuffleQuestions();

        points = 0;
        currentQuestion = 0;

        answerButtonGroup = (RadioGroup) findViewById(R.id.answerButtonGroup);
        answerButtonGroup.clearCheck();

        timeLeftView = (TextView) findViewById(R.id.timeLeftView);
        questionView = (TextView) findViewById(R.id.questionView);
        answerButtons[0] = (RadioButton) findViewById(R.id.answerButton1);
        answerButtons[1] = (RadioButton) findViewById(R.id.answerButton2);
        answerButtons[2] = (RadioButton) findViewById(R.id.answerButton3);
        answerButtons[3] = (RadioButton) findViewById(R.id.answerButton4);

        timeLeftView.setText("60");
        questionView.setText(questions[0].getQuestion());
        shuffleAnswers(0);

        timer = new QuizTimer(60000, 1000, this);
        timer.start();
    }

    /**
     * Called when the submit button on the quiz page is pressed OR when the timer reaches zero
     * It also checks if the user answered the question correctly and sets variables appropriately
     * Also when answering the last question, it goes to the results screen and transfers the appropriate data to it
     * If called by pressing the submit button, if an answer is not selected then the user is prompted to select one
     * If called by the timer running out, if an answer is not selected then the question is marked wrong
     */
    public void onClickSubmitButton(View view) {
        RadioButton rb = (RadioButton) answerButtonGroup.findViewById(answerButtonGroup.getCheckedRadioButtonId());
        if (rb != null || outOfTime) {
            timer.cancel();
            if (rb != null) { //Only runs if an answer was selected
                String selectedAnswer = (String) rb.getText();
                usersAnswer[currentQuestion] = selectedAnswer.intern();
                if (selectedAnswer.intern() == questions[currentQuestion].getAnswer())
                    gotQuestionRight[currentQuestion] = true;
                answerButtonGroup.clearCheck();
            }
            outOfTime = false;
            currentQuestion++;
            if (currentQuestion == QUESTION_LIMIT) { //Runs only if it is the final question
                //This block of code finishes the quiz and sends the appropriate data to the results page
                for (int i = 0; i < QUESTION_LIMIT; i++)
                    if (gotQuestionRight[i]) points++;
                Intent getResultScreenIntent = new Intent(this, ResultScreen.class);

                //Begin packing data
                getResultScreenIntent.putExtra("points", points);
                for (int i = 0; i < QUESTION_LIMIT; i++) {
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
            else
            {
                //This block of code sets up the next question
                timeLeftView.setText("60");
                questionView.setText(questions[currentQuestion].getQuestion());
                shuffleAnswers(currentQuestion);
                timer = new QuizTimer(60000, 1000, this);
                timer.start();
            }
        } else { //Only runs if an answer was not selected AND the submit button was pressed to run the method
            Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Obtains questions from the database. The amount obtained it based on QUESTION_LIMIT (NOT FULLY IMPLEMENTED)
     */
    private void obtainQuestionsFromDatabase()
    {
        Question dummyQuestion1 = new Question("2 + 2 = ?", "4", "1", "2", "3", Category.OTHER);
        Question dummyQuestion2 = new Question("2 * 2 = ?", "4", "1", "2", "3", Category.OTHER);
        Question dummyQuestion3 = new Question("2 - 2 = ?", "0", "1", "2", "3", Category.OTHER);
        Question dummyQuestion4 = new Question("2 / 2 = ?", "1", "4", "2", "3", Category.OTHER);
        Question dummyQuestion5 = new Question("2 ^ 2 = ?", "4", "1", "2", "3", Category.OTHER);

        questions[0].setQuestion(dummyQuestion1);
        questions[1].setQuestion(dummyQuestion2);
        questions[2].setQuestion(dummyQuestion3);
        questions[3].setQuestion(dummyQuestion4);
        questions[4].setQuestion(dummyQuestion5);
    }

    /** Randomizes the order of the questions */
    private void shuffleQuestions()
    {
        for (int i = QUESTION_LIMIT - 1; i >= 0; i--)
        {
            int j = rand.nextInt(i + 1);
            Question temp = questions[j];
            questions[j] = questions[i];
            questions[i] = temp;
        }
    }

    /** Randomizes the order of the answers */
    private void shuffleAnswers(int questionNum)
    {
        int[] answers = {0, 1, 2, 3};
        for (int i = 3; i >= 0; i--)
        {
            int j = rand.nextInt(i + 1);
            int temp = answers[j];
            answers[j] = answers[i];
            answers[i] = temp;
        }
        answerButtons[answers[0]].setText(questions[currentQuestion].getAnswer());
        answerButtons[answers[1]].setText(questions[currentQuestion].getFakeAnswer1());
        answerButtons[answers[2]].setText(questions[currentQuestion].getFakeAnswer2());
        answerButtons[answers[3]].setText(questions[currentQuestion].getFakeAnswer3());
    }

    public class QuizTimer extends CountDownTimer
    {
        private Activity parentActivity;

        QuizTimer(long l1, long l2, Activity a)
        {
            super(l1, l2);
            parentActivity = a;
        }

        /** Called every second */
        @Override
        public void onTick(long millisUntilFinished) {
            timeLeftView.setText("" + millisUntilFinished / 1000);
        }

        /** Called when the timer reaches zero */
        @Override
        public void onFinish() {
            outOfTime = true;
            onClickSubmitButton(null);
        }
    }
}

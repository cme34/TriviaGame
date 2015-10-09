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

/**
 * Created by Cory on 10/8/2015.
 */
public class QuizScreen extends Activity {
    private RadioGroup answerButtonGroup;
    private TextView timeLeftView;
    private TextView questionView;
    private RadioButton answerButton1;
    private RadioButton answerButton2;
    private RadioButton answerButton3;
    private RadioButton answerButton4;
    private Button submitButton;

    private int points;
    private QuizTimer timer;

    /*Dummy variables that represent questions that will obtain
      questions from a database */
    private String dummyQuestion = "What is 2 + 2?";
    private String dummyAnswer1 = "4";
    private String dummyAnswer2 = "pi";
    private String dummyAnswer3 = "x";
    private String dummyAnswer4 = "none of the above";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        points = 0;

        answerButtonGroup = (RadioGroup) findViewById(R.id.answerButtonGroup);
        answerButtonGroup.clearCheck();

        timeLeftView = (TextView) findViewById(R.id.timeLeftView);
        timeLeftView.setText("60");
        questionView = (TextView) findViewById(R.id.questionView);
        questionView.setText(dummyQuestion);

        answerButton1 = (RadioButton) findViewById(R.id.answerButton1);
        answerButton1.setText(dummyAnswer1);
        answerButton2 = (RadioButton) findViewById(R.id.answerButton2);
        answerButton2.setText(dummyAnswer2);
        answerButton3 = (RadioButton) findViewById(R.id.answerButton3);
        answerButton3.setText(dummyAnswer3);
        answerButton4 = (RadioButton) findViewById(R.id.answerButton4);
        answerButton4.setText(dummyAnswer4);

        timer = new QuizTimer(20000, 1000, this);
        timer.start();
    }

    public void onClickSubmitButton(View view) {
        RadioButton rb = (RadioButton) answerButtonGroup.findViewById(answerButtonGroup.getCheckedRadioButtonId());
        if (rb != null) {
            timer.cancel();
            String selectedAnswer = (String) rb.getText();
            if (selectedAnswer.intern() == dummyAnswer1.intern())
                points++;
            Intent getResultScreenIntent = new Intent(this, ResultScreen.class);
            getResultScreenIntent.putExtra("Points", points);
            startActivity(getResultScreenIntent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }

    public class QuizTimer extends CountDownTimer
    {
        private Activity parentActivity;

        QuizTimer(long l1, long l2, Activity a)
        {
            super(l1, l2);
            parentActivity = a;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timeLeftView.setText("" + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            timeLeftView.setText("" + 0);
            RadioButton rb = (RadioButton) answerButtonGroup.findViewById(answerButtonGroup.getCheckedRadioButtonId());
            if (rb != null) {
                String selectedAnswer = (String) rb.getText();
                if (selectedAnswer.intern() == dummyAnswer1.intern())
                    points++;
            }
            Intent getResultScreenIntent = new Intent(parentActivity, ResultScreen.class);
            getResultScreenIntent.putExtra("Points", points);
            startActivity(getResultScreenIntent);
            finish();
        }
    }
}

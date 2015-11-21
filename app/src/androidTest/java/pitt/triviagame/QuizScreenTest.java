package pitt.triviagame;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import junit.framework.TestCase;

import pitt.triviagame.R;
import pitt.triviagame.QuizScreen;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Danny on 10/11/2015.
 */
public class QuizScreenTest extends ActivityUnitTestCase<QuizScreen> {
    private RadioGroup answerButtonGroup;

    QuizScreen screen;

    public QuizScreenTest(){
        super(QuizScreen.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        screen = getActivity();
    }



    @SmallTest

    //if no answer is selected... then the submit button should not submit
    public void testSubmitButtonNoAnswerChecked(){
        User.loggedInUser = new User("DannyA", "123", 0, false); //have to simulate a real user to get questions to load
        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), QuizScreen.class);
        startActivity(mLaunchIntent, null, null);



        final Button buttonsubmit = (Button) getActivity().findViewById(R.id.quizScreenSubmitButton);

        //5 questions, so click submit 5 times
        buttonsubmit.performClick();
        buttonsubmit.performClick();
        buttonsubmit.performClick();
        buttonsubmit.performClick();
        buttonsubmit.performClick();

        final Intent launchIntent = getStartedActivityIntent();

        assertNull(launchIntent);





    }

    //selects an answer and submits it for the 5 questions and make sure it submits properly
    @SmallTest
    public void testSubmitButtonAnswerChecked(){
        User.loggedInUser = new User("DannyA", "123", 0, false); //have to simulate a real user to make the questions load
        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), QuizScreen.class);
        startActivity(mLaunchIntent, null, null);



        final Button buttonsubmit = (Button) getActivity().findViewById(R.id.quizScreenSubmitButton);
        final RadioButton answer = (RadioButton) getActivity().findViewById(R.id.quizScreenAnswerButton1);

        final RadioGroup rg = (RadioGroup) getActivity().findViewById(R.id.quizScreenAnswerButtonGroup);

        //do this 5 times... since there are 5 questions
        answer.setChecked(true);
        rg.check(R.id.quizScreenAnswerButton1);
        buttonsubmit.performClick();
        answer.setChecked(true);
        rg.check(R.id.quizScreenAnswerButton1);
        buttonsubmit.performClick();
        answer.setChecked(true);
        rg.check(R.id.quizScreenAnswerButton1);
        buttonsubmit.performClick();
        answer.setChecked(true);
        rg.check(R.id.quizScreenAnswerButton1);
        buttonsubmit.performClick();
        answer.setChecked(true);
        rg.check(R.id.quizScreenAnswerButton1);

        buttonsubmit.performClick();

        final Intent launchIntent = getStartedActivityIntent();

        assertNotNull(launchIntent);





    }


}

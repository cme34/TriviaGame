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

    /*
    @SmallTest
    public void testTextViewNotNull(){
        TextView textView = (TextView)  screen.findViewById(R.id.timeLeftView);
        assertNotNull(textView);
    } */

    @SmallTest

    //if no answer is selected... then the submit button should not submit
    public void testSubmitButton(){
        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), QuizScreen.class);
        startActivity(mLaunchIntent, null, null);



        final Button buttonsubmit = (Button) getActivity().findViewById(R.id.quizScreenSubmitButton);
        buttonsubmit.performClick();

        final Intent launchIntent = getStartedActivityIntent();

        assertFalse(isFinishCalled());





    }

    /* got rid of this test as it no longer applies
    @SmallTest
    public void testThatButton1HasCorrectAnswerAndCanIncrementPoints(){
        int expectedPoints=1;
        int points=0;

        Button button1 = (Button)screen.findViewById(R.id.answerButton1);
        String numString = button1.getText().toString();
        int num = Integer.parseInt(numString);
        if (num==4){
            points++;
        }
        assertEquals(expectedPoints, points);


    }

    */
}

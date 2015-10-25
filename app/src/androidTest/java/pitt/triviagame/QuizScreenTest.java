package pitt.triviagame;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

import junit.framework.TestCase;

import pitt.triviagame.R;
import pitt.triviagame.QuizScreen;

/**
 * Created by Danny on 10/11/2015.
 */
public class QuizScreenTest extends ActivityInstrumentationTestCase2<QuizScreen>{

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
    public void testTextViewNotNull(){
        TextView textView = (TextView)  screen.findViewById(R.id.timeLeftView);
        assertNotNull(textView);
    }

    @SmallTest
    public void testThis(){

    }

    /*
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

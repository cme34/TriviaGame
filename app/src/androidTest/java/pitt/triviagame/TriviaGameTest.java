package pitt.triviagame;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Danny on 10/25/2015.
 */
public class TriviaGameTest extends ActivityUnitTestCase<TriviaGame> {

    public TriviaGameTest(){
        super(TriviaGame.class);
    }




    //When the play button is clicked, does it work?
    @MediumTest
    public void testPlayButton(){

        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), TriviaGame.class);
        startActivity(mLaunchIntent, null, null);
        final Button button = (Button) getActivity().findViewById(R.id.triviaGamePlayButton);



        button.performClick();
        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull(launchIntent);

    }


}

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
        User.loggedInUser = new User("DannyA", "123", 0, false); //have to simulate a real user to make the main page load

        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), TriviaGame.class);
        startActivity(mLaunchIntent, null, null);
        final Button button = (Button) getActivity().findViewById(R.id.triviaGamePlayButton);



        button.performClick();
        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull(launchIntent);

    }

    //When the settings button is clicked, does it work?
    @MediumTest
    public void testSettingsButton(){
        User.loggedInUser = new User("DannyA", "123", 0, false); //have to simulate a real user to make the main page load

        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), TriviaGame.class);
        startActivity(mLaunchIntent, null, null);
        final Button button = (Button) getActivity().findViewById(R.id.triviaGameSettingsButton);



        button.performClick();
        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull(launchIntent);

    }


}

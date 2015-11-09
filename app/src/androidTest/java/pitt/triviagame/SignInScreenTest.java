package pitt.triviagame;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Danny on 11/8/2015.
 */
public class SignInScreenTest extends ActivityUnitTestCase<SignInScreen> {

    SignInScreen screen;
    public SignInScreenTest(){
        super(SignInScreen.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        screen = getActivity();
    }


    @SmallTest
    /*
    Tests the sign in function with the default user name and password.
     */
    public void testSignIn() {
        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), SignInScreen.class);
        startActivity(mLaunchIntent, null, null);

        final EditText user = (EditText) getActivity().findViewById(R.id.signInScreenUsernameTextField);
        user.setText("admin");
        final EditText pass = (EditText) getActivity().findViewById(R.id.signInScreenPasswordTextField);
        pass.setText("admin");
        final Button submitbutton = (Button) getActivity().findViewById(R.id.signInScreenSignInButton);
        submitbutton.performClick();

        final Intent launchIntent = getStartedActivityIntent();

        assertTrue(isFinishCalled());

    }

    @SmallTest
    /*
    Tests the sign in function with wrong password to make sure login is not successful
     */
    public void testSignInWrongPass() {
        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), SignInScreen.class);
        startActivity(mLaunchIntent, null, null);

        final EditText user = (EditText) getActivity().findViewById(R.id.signInScreenUsernameTextField);
        user.setText("admin");
        final EditText pass = (EditText) getActivity().findViewById(R.id.signInScreenPasswordTextField);
        pass.setText("admingfd");
        final Button submitbutton = (Button) getActivity().findViewById(R.id.signInScreenSignInButton);
        submitbutton.performClick();

        final Intent launchIntent = getStartedActivityIntent();

        assertFalse(isFinishCalled());

    }
}

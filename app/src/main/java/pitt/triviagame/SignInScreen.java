package pitt.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Cory on 11/6/2015.
 * This is the page were a user can sign in or choose to go to the create account page
 */
public class SignInScreen extends AppCompatActivity {
    private Button signInButton, createAccountButton;
    private EditText usernameEditText, passwordEditText;
    private String username, password;

    /**
     * This method is essentially a constructor. It initializes the sign in screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        usernameEditText = (EditText) findViewById(R.id.signInScreenUsernameTextField);
        passwordEditText = (EditText) findViewById(R.id.signInScreenPasswordTextField);
        signInButton = (Button) findViewById(R.id.signInScreenSignInButton);
        createAccountButton = (Button) findViewById(R.id.signInScreenCreateAccountButton);
    }

    /**
     * On Sign In Button clicked, takes the user to the main menu if credentials match up
     * Also, checks for error in the text fields as well as packages some data for the main menu
     */
    public void onClickSignInButton(View view) {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        //Is username field empty
        if (username.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a username and password", Toast.LENGTH_SHORT).show();
            passwordEditText.setText("");
            return;
        }
        //Is password field empty
        if (password.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
            passwordEditText.setText("");
            return;
        }
        //Check login credentials
        if (checkSignInCredentials()) {
            Intent getTriviaGame = new Intent(this, TriviaGame.class);
            startActivity(getTriviaGame);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Username or password is not correct", Toast.LENGTH_SHORT).show();
            passwordEditText.setText("");
        }
    }

    /**
     * On Create Account Button clicked, takes the user to the create account screen
     */
    public void onClickCreateAccountButton(View view) {
        usernameEditText.setText("");
        passwordEditText.setText("");
        Intent getCreateAccountScreen = new Intent(this, CreateAccountScreen.class);
        startActivity(getCreateAccountScreen);
    }

    /**
     * Checks the credentials the user entered with the database
     * @return returns true if the credentials match up with what is in the database
     */
    private boolean checkSignInCredentials() {
        //Make call to server
        DatabaseHandler task = new DatabaseHandler();
        task.execute(DatabaseHandler.RECEIVE_SIGN_IN_VALID, "?" + username + "?" + password);//Param for checking if sign in is valid

        //Wait for server response
        String jsonString = null;
        while (jsonString == null)
            jsonString = task.getJsonString();

        //Create Json object
        JSONObject jsonFile = null;
        boolean signInSuccessful = false;
        int userPoints = 0;
        boolean userQuizTaken = false;
        try {
            jsonFile = new JSONObject(jsonString);
            //Get if sign in successful from json file
            signInSuccessful = jsonFile.getBoolean("Value");
            if (signInSuccessful) {
                userPoints = jsonFile.getInt("Points");
                int i = jsonFile.getInt("QuizTaken");
                if (i == 0)
                    userQuizTaken = false;
                else
                    userQuizTaken = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Sign user in
        if (signInSuccessful) {
            User.loggedInUser = new User(username, password, userPoints, userQuizTaken);
            return true;
        }
        else {
            return false;
        }
    }
}

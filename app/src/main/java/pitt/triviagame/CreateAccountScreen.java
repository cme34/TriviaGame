package pitt.triviagame;

import android.app.Activity;
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
 * This page is where a user can create an account
 */
public class CreateAccountScreen extends AppCompatActivity {
    Button createAccountButton, cancelButton;
    EditText usernameEditText, createPasswordEditText, confirmPasswordEditText;
    String username, createPassword, confirmPassword;

    /**
     * This method is essentially a constructor. It initializes the create account screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        usernameEditText = (EditText) findViewById(R.id.createAccountScreenUsernameTextField);
        createPasswordEditText = (EditText) findViewById(R.id.createAccountScreenCreatePasswordTextField);
        confirmPasswordEditText = (EditText) findViewById(R.id.createAccountScreenConfirmPasswordTextField);
        createAccountButton = (Button) findViewById(R.id.createAccountScreenCreateAccountButton);
        cancelButton = (Button) findViewById(R.id.createAccountScreenCancelButton);
    }

    /**
     * On Account Button clicked, check if all fields are valid
     * If they are, then the credentials are sent to the database
     * If not, then user is prompted with a message about what is wrong
     */
    public void onClickCreateAccountButton(View view) {
        username = usernameEditText.getText().toString();
        createPassword = createPasswordEditText.getText().toString();
        confirmPassword = confirmPasswordEditText.getText().toString();
        if(isCredentialsValid()) {
            sendCredentialsToDatabase();
            finish();
        }
    }

    /**
     * On Cancel Button clicked, closes the create account screen
     */
    public void onClickCancelButton(View view) { finish(); }

    /**
     * This method looks at the username and password fields and checks if they are valid
     * @return returns true if all credentials pass
     */
    private boolean isCredentialsValid() {
        //Check if any fields are empty
        if (username.equals("") || createPassword.equals("") || confirmPassword.equals("")) {
            Toast.makeText(getApplicationContext(), "All fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Check if username has all valid characters
        boolean[] validCharacter = new boolean[username.length()];
        char[] c = username.toCharArray();
        for (int i = 0; i < username.length(); i++) {
            //Check letters
            for (int j = 0; j < 26; j++) {
                if (c[i] == 65 + j) {
                    validCharacter[i] = true;
                    break;
                }
                else if (c[i] == 97 + j) {
                    validCharacter[i] = true;
                    break;
                }
            }
            //Check numbers
            for (int j = 0; j < 10; j++) {
                if (c[i] == 48 + j) {
                    validCharacter[i] = true;
                    break;
                }
            }
            if (c[i] == '_')
                validCharacter[i] = true;
        }
        boolean allValidCharactersInUsername = true;
        //If any character in the username is not a valid character, then username is not valid
        for (int i = 0; i < username.length(); i++) {
            if (!validCharacter[i]) {
                allValidCharactersInUsername = false;
                break;
            }
        }
        if (!allValidCharactersInUsername)
        {
            Toast.makeText(getApplicationContext(), "Username can only contain letters, numbers, and underscores", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Is username valid length
        if (username.length() < 6 || username.length() > 18) {
            Toast.makeText(getApplicationContext(), "Username must be between 6-24 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Does someone else have this username
        if (!isUsernameUnique()) {
            Toast.makeText(getApplicationContext(), "Sorry. Username already in use", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Is password valid length
        if (createPassword.length() < 8 || createPassword.length() > 64) {
            Toast.makeText(getApplicationContext(), "Password must be 8-64 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Does create password match up with confirm password
        if (!(createPassword.equals(confirmPassword))) {
            Toast.makeText(getApplicationContext(), "Password fields do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Since all credentials pass, return true
        return true;
    }

    /**
     * This method checks to see if the username in the text field is already in the database
     * @return returns true if username is not in the database
     */
    public boolean isUsernameUnique() {
        //Make call to server
        DatabaseHandler task = new DatabaseHandler();
        task.execute(DatabaseHandler.RECEIVE_USERNAME_VALID, "?" + username);//Param for checking if username is unique

        //Wait for server response
        String jsonString = null;
        while (jsonString == null)
            jsonString = task.getJsonString();

        //Create Json object
        JSONObject jsonFile = null;
        boolean usernameUnique = false;
        try {
            jsonFile = new JSONObject(jsonString);
            //Get if username is unique from json file
            usernameUnique = jsonFile.getBoolean("Value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usernameUnique;
    }

    /**
     * This method adds the users credentials to the database
     */
    private void sendCredentialsToDatabase() {
        //Make call to server
        DatabaseHandler task = new DatabaseHandler();
        task.execute(DatabaseHandler.SEND_CREDENTIALS, "?" + username + "?" + createPassword);//Param for sending credentials

        //Wait for server response
        String jsonString = null;
        while (jsonString == null)
            jsonString = task.getJsonString();

        //Create Json object
        JSONObject jsonFile = null;
        boolean sentSuccessful = false;
        try {
            jsonFile = new JSONObject(jsonString);
            //Get if sending credentials is successful from json file
            sentSuccessful = jsonFile.getBoolean("Value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

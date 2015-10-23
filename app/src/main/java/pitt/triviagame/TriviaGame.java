package pitt.triviagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Cory on 10/8/2015.
 * This is the main menu page of the game
 */
public class TriviaGame extends AppCompatActivity {

    /**
     * Creates the main menu screen and all of its components
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);
    }

    /**
     * Populates the options when clicked
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trivia_game, menu);
        return true;
    }

    /**
     * Auto generated, no yet implemented
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Takes the user from the main menu to the quiz screen to answer questions
     */
    public void onClickPlayButton(View view) {
        Intent getQuizScreenIntent = new Intent(this, QuizScreen.class);
        startActivity(getQuizScreenIntent);
    }

    /**
     * Ends the program when the QUIT button is clicked
     */
    public void onClickQuitButton(View view) {
        finish();
    }
}

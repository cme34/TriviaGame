package pitt.triviagame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Cory on 11/7/2015.
 * This page allows the user to edit app settings
 */
public class SettingsScreen extends AppCompatActivity {

    /**
     * This method is essentially a constructor. It initializes the trivia game (main menu screen)
     * Also it unpacks some data from the sign in screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    /**
     * On Okay Button Clicked, takes the user back to the main menu(Trivia Game)
     */
    public void onClickOkayButton(View view) {
        finish();
    }
}

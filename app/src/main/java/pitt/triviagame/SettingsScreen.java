package pitt.triviagame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

/**
 * Created by Cory on 11/7/2015.
 * This page allows the user to edit app settings
 */
public class SettingsScreen extends AppCompatActivity {

    public static Switch notifySwitch;

    /**
     * This method is essentially a constructor. It initializes the trivia game (main menu screen)
     * Also it unpacks some data from the sign in screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
	notifySwitch = (Switch) findViewById(R.id.notificationSwitch);
        notifySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            /** On notificationSwitch switch, set or cancel notification alarm */
            @Override
            // TODO: Have setting persist when app is closed
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    new NotificationAlarmBR().SetAlarm(getBaseContext());
                else
                    new NotificationAlarmBR().CancelAlarm(getBaseContext());
            }
        });
    }

    /**
     * On Okay Button Clicked, takes the user back to the main menu(Trivia Game)
     */
    public void onClickOkayButton(View view) {
        finish();
    }
}

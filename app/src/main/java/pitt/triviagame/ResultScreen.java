package pitt.triviagame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Cory on 10/8/2015.
 */
public class ResultScreen extends Activity {
    private TextView scoreView;
    private Button okayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent activityCalled = getIntent();

        scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText("" + activityCalled.getExtras().getInt("Points"));
        okayButton = (Button) findViewById(R.id.okayButton);
    }

    public void onClickOkayButton(View view)
    {
        finish();
    }
}

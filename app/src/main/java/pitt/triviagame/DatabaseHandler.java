package pitt.triviagame;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Cory on 11/17/2015.
 * This class manages all calls to the database
 */
public class DatabaseHandler extends AsyncTask<String, Void, String> {
    //String constants that are used to minimize error when making database calls
    public static final String WEBSITE_URL = "http://www.cityquizsubmit.appspot.com/";
    public static final String RECEIVE_DAILY_QUESTIONS = "receiveDailyQuestions";
    public static final String RECEIVE_LEADER_BOARD = "receiveLeaderBoard";
    public static final String RECEIVE_SIGN_IN_VALID = "receiveSignInValid";//?username?password
    public static final String RECEIVE_USERNAME_VALID = "receiveUsernameValid";//?username
    public static final String SEND_CREDENTIALS = "sendCredentials";//?username?password
    public static final String SEND_POINTS = "sendPoints";//?username?points
    public static final String SEND_QUIZ_TAKEN = "sendQuizTaken";//?username

    /**
     * The json string obtained from database calls
     */
    private String jsonString;

    /**
     * Runs when .execute() is called
     * @param params Strings entered in the .execute(params...)
     * @return
     */
    @Override
    protected String doInBackground(String... params) {
        //do your request in here so that you don't interrupt the UI thread
        if (params[0].equals(RECEIVE_DAILY_QUESTIONS))
            return getData(WEBSITE_URL + RECEIVE_DAILY_QUESTIONS);
        else if (params[0].equals(RECEIVE_LEADER_BOARD))
            return getData(WEBSITE_URL + RECEIVE_LEADER_BOARD);
        else if (params[0].equals(RECEIVE_SIGN_IN_VALID))
            return getData(WEBSITE_URL + RECEIVE_SIGN_IN_VALID + params[1]);
        else if (params[0].equals(RECEIVE_USERNAME_VALID))
            return getData(WEBSITE_URL + RECEIVE_USERNAME_VALID + params[1]);
        else if (params[0].equals(SEND_CREDENTIALS))
            return getData(WEBSITE_URL + SEND_CREDENTIALS + params[1]);
        else if (params[0].equals(SEND_POINTS))
            return getData(WEBSITE_URL + SEND_POINTS + params[1]);
        else if (params[0].equals(SEND_QUIZ_TAKEN))
            return getData(WEBSITE_URL + SEND_QUIZ_TAKEN + params[1]);
        else
            return jsonString = null;
    }

    /**
     * This is what is called after .execute() is finished. Does nothing.
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
        //Here you are done with the task
    }

    /**
     * Called in doInBackground(). The method connects to the database and sends and receives necessary data
     * @param myURL the address at where to connect to the database
     * @return returns jsonString obtained from database
     */
    private String getData(String myURL) {
        InputStream is = null;
        jsonString = null;

        try {
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();
            // Convert the InputStream into a string
            jsonString = convertInputStreamToString(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    /**
     * Converts a InputStream to a String
     * @param is InputStream
     * @return String
     */
    private String convertInputStreamToString(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    /**
     * The jsonString obtained from the database call
     * @return jsonString
     */
    public String getJsonString() {
        return jsonString;
    }
}

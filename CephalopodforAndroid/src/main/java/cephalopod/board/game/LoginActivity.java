package cephalopod.board.game;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Login Screen
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Reference to login button.
     */
    private Button login;

    /**
     * Reference to button for sign up.
     */
    private Button signUp;

    /**
     * Reference for TextView for username.
     */
    private TextView username;

    /**
     * Reference to Text view for password.
     */
    private TextView password;

    /**
     * Reference to the EditText View for username input.
     */
    private EditText usernameInsert;

    /**
     * Reference to the EditText View for password input.
     */
    private EditText passwordInsert;

    /**
     * Reference to DBAdapter object.
     */
    private DbAdapter db;

    /**
     * Reference to Session object.
     */
    private Session session;

    /**
     *
     *
     */
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login_button:
                        logIn();
                        break;
                    case R.id.registration_button:
                        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                        break;
                    default:
                }
            }
        };

        /**
         * Initalization of fields.
         */
        db = DbAdapter.getInstance(this);
        session = new Session(this);
        login = (Button) findViewById(R.id.login_button);
        signUp = (Button) findViewById(R.id.registration_button);
        username = (TextView) findViewById(R.id.username_text);
        password = (TextView) findViewById(R.id.password_text);
        usernameInsert = (EditText) findViewById(R.id.username_insert);
        passwordInsert = (EditText) findViewById(R.id.password_insert);


        /**
         * Setting onclickListeners to the buttons.
         */
        login.setOnClickListener(listener);
        signUp.setOnClickListener(listener);

        //TODO: Add Java Doc Comments
        if (session.loggedIn()) {
            Message.message(this, "User is loggedin");
        }
    }

    /**
     * Login method. If user's data is correct and already in the database login is successfull.
     * User data is set as true in SharedPreferences database and redirected to game screen.
     */
    private void logIn() {

        String username = usernameInsert.getText().toString();
        String pass = passwordInsert.getText().toString();


        //TODO: Add Java Doc Comments
        new AsyncTask<String, Void, Boolean>() {
            /**
             * Override this method to perform a computation on a background thread. The
             * specified parameters are the parameters passed to {@link #execute}
             * by the caller of this task.
             * <p>
             * This method can call {@link #publishProgress} to publish updates
             * on the UI thread.
             *
             * @param params The parameters of the task.
             * @return A result, defined by the subclass of this task.
             * @see #onPreExecute()
             * @see #onPostExecute
             * @see #publishProgress
             */

            @Override
            protected Boolean doInBackground(String... params) {
                boolean result = db.getUser(params[0], params[1]);
                return result;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    session.setLoggedin(true);
                    startActivity(new Intent(LoginActivity.this, GameActivity.class));
                    Message.message(LoginActivity.this, "User logged in!");
                } else {
                    Message.message(LoginActivity.this, "User not Logged in");
                }
            }
        }.execute(username, pass);
    }
}


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
 * Registration screen.
 */
public class SignUpActivity extends AppCompatActivity {

    //TODO: Add Java Doc Comments
    private static final int RESULT_CODE_CANCELED = 5;

    //TODO: Add Java Doc Comments
    private static final int RESULT_CODE_SUCCESS = 3;

    /**
     * Reference to TextView object with text username.
     */
    private TextView userName;

    /**
     * Reference to TextView object with text password.
     */
    private TextView password;

    /**
     * Reference for TextView for confirmPass field;
     */
    private TextView confirmPass;

    /**
     * Reference for EditText View object used for access to the username data.
     */
    private EditText usernameInsert;

    /**
     * Reference for EditText View object used for access to the password data.
     */
    private EditText passInsert;

    /**
     * Reference for EditText View object for password verification.
     */
    private EditText confirmPassInsert;

    /**
     * Reference for button object for signing up.
     */
    private Button signUp;

    /**
     * Reference for button object for logging in.
     */
    private Button cancel;

    /**
     * Reference to the Database adapter object.
     */
    private DbAdapter dbhelper;

    //TODO: Add Java Doc Comments
    public static int getResultCodeCanceled() {
        return RESULT_CODE_CANCELED;
    }

    //TODO: Add Java Doc Comments
    public static int getResultCodeSuccess() {
        return RESULT_CODE_SUCCESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /**
         * Initalization of fields.
         */
        dbhelper = DbAdapter.getInstance(this);
        usernameInsert = (EditText) findViewById(R.id.signupusername_insert);
        passInsert = (EditText) findViewById(R.id.signuppassword_insert);
        confirmPassInsert = (EditText) findViewById(R.id.signupconfirmpass_insert);
        signUp = (Button) findViewById(R.id.signupregistration_button);
        cancel = (Button) findViewById(R.id.signupcancel_button);

        /**
         * Setting onclickListeners to the buttons.
         */
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signupregistration_button:
                        addUser();
                        //return added data and a result code
                        Intent intent = new Intent();
                        intent.putExtra("user", usernameInsert.getText().toString());
                        intent.putExtra("pass", passInsert.getText().toString());
                        setResult(RESULT_CODE_SUCCESS, intent);//ok
                        finish();
                        break;
                    case R.id.signupcancel_button:
                        startActivity(new Intent(SignUpActivity.this, WelcomeActivity.class));
                        break;
                    default:
                }
            }
        };
        signUp.setOnClickListener(listener);
        cancel.setOnClickListener(listener);
    }

    /**
     * Method that calls the database object and enters data.
     */
    public boolean addUser() {
        String username, pass, confirm = "";
        username = usernameInsert.getText().toString();
        pass = passInsert.getText().toString();
        confirm = confirmPassInsert.getText().toString();

        if (username.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
            Message.message(this, "Some fields are empty!");
            return false;
        }
        if (!pass.equals(confirm)) {
            Message.message(this, "Two passwords are not identical!");
            return false;
        }

        new AsyncTask<String, Void, Long>() {
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
            protected Long doInBackground(String... params) {
                long id = dbhelper.insertData(params[0], params[1]);
                return id;
            }


            @Override
            protected void onPostExecute(Long id) {
                if (id < 0) {
                    Message.message(SignUpActivity.this, "Registration was not successful!");
                } else {
                    startActivity(new Intent(SignUpActivity.this, GameActivity.class));
                }
            }
        }.execute(username, pass);
        return true;
    }
}
package cephalopod.board.game;

import android.content.Intent;
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
    private Button logIn;

    /**
     * Reference to the Database adapter object.
     */
    private DbAdapter dbhelper;

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
        dbhelper = new DbAdapter(this);
        usernameInsert = (EditText) findViewById(R.id.signupusername_insert);
        passInsert = (EditText) findViewById(R.id.signuppassword_insert);
        confirmPassInsert = (EditText) findViewById(R.id.signupconfirmpass_insert);
        signUp = (Button) findViewById(R.id.signupregistration_button);
        logIn = (Button) findViewById(R.id.signuploggin_button);

        /**
         * Setting onclickListeners to the buttons.
         */
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signupregistration_button:
                        addUser();
                        break;
                    case R.id.signuploggin_button:
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        break;
                    default:
                }
            }
        };
        signUp.setOnClickListener(listener);
        logIn.setOnClickListener(listener);
    }

    /**
     * Method that calls the database object and enters data.
     */
    public void addUser() {
        String username = usernameInsert.getText().toString();
        String pass = passInsert.getText().toString();
        String confirm = confirmPassInsert.getText().toString();

        long id = dbhelper.insertData(username, pass);
        if (id < 0 || username.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
            Message.message(this, "Some fields are empty!");
        } else {
            Message.message(this, "User Registered");
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        }
    }

    //TODO: validation helper method to check if data is valid and confirmPassword is the same is passInsert
}


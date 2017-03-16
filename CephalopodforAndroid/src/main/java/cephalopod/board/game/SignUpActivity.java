package cephalopod.board.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 */
public class SignUpActivity extends AppCompatActivity {
    /**
     *
     */
    private TextView userName;
    /**
     *
     */
    private TextView password;
    /**
     *
     */
    private TextView confirmPass;
    /**
     *
     */
    private EditText usernameInsert;
    /**
     *
     */
    private EditText passInsert;
    /**
     *
     */
    private EditText confirmPassInsert;
    /**
     *
     */
    private Button signUp;
    /**
     *
     */
    private Button logIn;

    /**
     *
     */
    private DbAdapter dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbhelper = new DbAdapter(this);
        usernameInsert = (EditText)findViewById(R.id.username_insert);
        passInsert = (EditText)findViewById(R.id.password_insert);
        confirmPassInsert = (EditText)findViewById(R.id.confirmpass_insert);
        signUp = (Button)findViewById(R.id.registration_button);
        logIn = (Button)findViewById(R.id.loggin_button) ;
        /**
         *
         */
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.registration_button:
                        addUser();
                        break;
                    case R.id.loggin_button:
                        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                        break;
                    default:
                }
            }
        };
        signUp.setOnClickListener(listener);
        logIn.setOnClickListener(listener);
    }

    /**
     *
     */
    public void addUser(){
        String username = usernameInsert.getText().toString();
        String pass = passInsert.getText().toString();
        String confirm = confirmPassInsert.getText().toString();
        long id = dbhelper.insertData(username,pass);
        if( id < 0 || username.isEmpty() || pass.isEmpty()|| confirm.isEmpty()){

            Message.message(this,"Some fields are empty!");
        }
        else{
            Message.message(this,"User Registered");
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        }
    }
}


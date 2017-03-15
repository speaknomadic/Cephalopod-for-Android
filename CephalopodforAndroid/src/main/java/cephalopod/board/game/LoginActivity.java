package cephalopod.board.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button signUp;
    private TextView username;
    private TextView password;
    private EditText usernameInsert;
    private EditText passwordInsert;
    private DbAdapter db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.login_button:
                      logIn();
                        break;
                    case R.id.registration_button:
                        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                        break;
                    default:

                }
            }
        };

        db = new DbAdapter(this);
        session = new Session(this);
        login=(Button)findViewById(R.id.login_button);
        signUp=(Button)findViewById(R.id.registration_button);
        username = (TextView)findViewById(R.id.username_text);
        password = (TextView)findViewById(R.id.password_text);
        usernameInsert = (EditText)findViewById(R.id.username_insert);
        passwordInsert = (EditText)findViewById(R.id.password_insert);

        login.setOnClickListener(listener);
        signUp.setOnClickListener(listener);

        if(session.loggedIn()){

           // Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
        }
    }

   private void logIn(){
        String username = usernameInsert.getText().toString();
        String pass = passwordInsert.getText().toString();
        if(db.getUser(username,pass)){
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this,GameActivity.class));
            Toast.makeText(this, "User Logged in", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Wrong username/password!", Toast.LENGTH_SHORT).show();
        }
    }
}

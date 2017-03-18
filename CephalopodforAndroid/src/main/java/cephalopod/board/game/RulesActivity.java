package cephalopod.board.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/**
 * Rules screen.
 */
public class RulesActivity extends AppCompatActivity {

    private Button back;
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        back = (Button)findViewById(R.id.rulesBack_button);

        View.OnClickListener listener= new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RulesActivity.this,GameActivity.class));
            }
        };

        back.setOnClickListener(listener);
    }
}

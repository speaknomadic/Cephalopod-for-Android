package cephalopod.board.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * About the product screen.
 */
public class AboutActivity extends Activity {

    private Button back;
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        back = (Button)findViewById(R.id.aboutBack_button);

        View.OnClickListener listener= new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this,GameActivity.class));
            }
        };

        back.setOnClickListener(listener);
    }
}

package cephalopod.board.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Rules screen.
 */
public class RulesActivity extends MenuActivity {

    private Button back;
    private Button readMore;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        back = (Button) findViewById(R.id.rulesBack_button);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RulesActivity.this, GameActivity.class));
            }
        };
        back.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

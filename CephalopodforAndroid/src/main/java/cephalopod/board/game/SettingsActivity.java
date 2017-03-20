package cephalopod.board.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Settings screen.
 */
public class SettingsActivity extends Activity {

    RadioGroup radioGroup;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_choose_game);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton buttonChoiceGame = (RadioButton) findViewById(checkedId);
                String choiceOfGame = buttonChoiceGame.getText().toString();
                switch (choiceOfGame) {
                    case "Single Player":
                        startActivity(new Intent(SettingsActivity.this, GameActivity.class));
                        break;
                    case "Multiplayer":
                        Toast.makeText(SettingsActivity.this, "This feature is not available yet. Coming soon", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        startActivity(new Intent(SettingsActivity.this, GameActivity.class));
                }
            }
        });
    }
}

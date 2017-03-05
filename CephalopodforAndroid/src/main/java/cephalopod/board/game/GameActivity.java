package cephalopod.board.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends Activity {

    private ImageView images[][] = {{null, null, null, null, null}, {null, null, null, null, null},
            {null, null, null, null, null}, {null, null, null, null, null}, {null, null, null, null, null},};

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO Call object model
            Toast.makeText(GameActivity.this, "" + v, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        images[0][0] = (ImageView) findViewById(R.id.cell00);
        images[0][1] = (ImageView) findViewById(R.id.cell01);
        images[0][2] = (ImageView) findViewById(R.id.cell02);
        images[0][3] = (ImageView) findViewById(R.id.cell03);
        images[0][4] = (ImageView) findViewById(R.id.cell04);
        images[1][0] = (ImageView) findViewById(R.id.cell10);
        images[1][1] = (ImageView) findViewById(R.id.cell11);
        images[1][2] = (ImageView) findViewById(R.id.cell12);
        images[1][3] = (ImageView) findViewById(R.id.cell13);
        images[1][4] = (ImageView) findViewById(R.id.cell14);
        images[2][0] = (ImageView) findViewById(R.id.cell20);
        images[2][1] = (ImageView) findViewById(R.id.cell21);
        images[2][2] = (ImageView) findViewById(R.id.cell22);
        images[2][3] = (ImageView) findViewById(R.id.cell23);
        images[2][4] = (ImageView) findViewById(R.id.cell24);
        images[3][0] = (ImageView) findViewById(R.id.cell30);
        images[3][1] = (ImageView) findViewById(R.id.cell31);
        images[3][2] = (ImageView) findViewById(R.id.cell32);
        images[3][3] = (ImageView) findViewById(R.id.cell33);
        images[3][4] = (ImageView) findViewById(R.id.cell34);
        images[4][0] = (ImageView) findViewById(R.id.cell40);
        images[4][1] = (ImageView) findViewById(R.id.cell41);
        images[4][2] = (ImageView) findViewById(R.id.cell42);
        images[4][3] = (ImageView) findViewById(R.id.cell43);
        images[4][4] = (ImageView) findViewById(R.id.cell44);

        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                images[i][j].setOnClickListener(click);
            }
        }
    }
}

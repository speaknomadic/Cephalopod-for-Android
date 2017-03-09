package cephalopod.board.game;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import cephalopod.board.game.model.Board;
import cephalopod.board.game.model.Cell;
import cephalopod.board.game.model.ai.ArtificialIntelligence;
import cephalopod.board.game.model.ai.RandomArtificialIntelligence;

//TODO Add Java Doc comments.
public class GameActivity extends Activity {

    //TODO Add Java Doc comments.
    private final Handler handler = new Handler();

    //TODO Add Java Doc comments.
    private SoundPool sounds = null;

    //TODO Add Java Doc comments.
    private int clickId = -1;

    //TODO Add Java Doc comments.
    private int finishId = -1;

    //TODO Add Java Doc comments.

    private Runnable ai = new Runnable() {
        //TODO Add Java Doc comments.
        private ArtificialIntelligence bot = new RandomArtificialIntelligence();

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
			/*
			 * If the game is over there is no need to play.
			 */
            if (board.isGameOver() == true) {
                return;
            }

			/*
			 * Valid turn check. The bot is always second.
			 */
            if (board.getTurn() % 2 != 1) {
                return;
            }

			/*
			 * Generate move.
			 */
            int move[] = bot.move(board.getCells(), Cell.Type.instanceOf(board.getTurn()% 2));

			/*
			 * Play move.
			 */
            boolean result = board.click(move[0], move[1]);
            if (result == true) {
                if (board.hasWinner() == true) {
                    board.setGameOver();
                }
                board.next();
            }
            updateViews();
        }
    };

    //TODO Add Java Doc comments.
    private Board board = new Board();

    //TODO Add Java Doc comments.
    private ImageView images[][] = {{null, null, null, null, null}, {null, null, null, null, null},
            {null, null, null, null, null}, {null, null, null, null, null}, {null, null, null, null, null},};

    //TODO Add Java Doc comments.
    private View.OnClickListener click = new View.OnClickListener() {
        //TODO Add Java Doc comments.
        @Override
        public void onClick(View view) {
/*
             * If the game is over there is nothing bot can do.
			 */
            if (board.isGameOver() == true) {
                return;
            }

			/*
			 * If the human player has turn also nothing to be done.
			 */
            if (board.getTurn() % 2 != 0) {
                return;
            }

			/*
			 * Play a human move.
			 */
            boolean result = false;
            loops:
            for (int i = 0; i < images.length; i++) {
                for (int j = 0; j < images[i].length; j++) {
                    if (images[i][j] == view) {
                        result = board.click(i, j);
                        break loops;
                    }
                }
            }

			/*
			 * Check for winner.
			 */
            if (result == true) {
                if (board.hasWinner() == true) {
                    board.setGameOver();
                } else {
                    sounds.play(clickId, 0.99f, 0.99f, 0, 0, 1);
                    board.next();
                    handler.postDelayed(ai, 500);
                }
            }

			/*
			 * Update user interface.
			 */
            updateViews();

        }
    };

    //TODO Add Java Doc comments.
    private void updateViews() {
		/*
		 * Play sound for game over.
		 */
        if (board.isGameOver() == true) {
            sounds.play(finishId, 0.99f, 0.99f, 0, 0, 1);
        }

		/*
		 * Redraw all cells.
		 */
        Cell cells[][] = board.getCells();
        for (int i = 0; i < cells.length && i < images.length; i++) {
            for (int j = 0; j < cells[i].length && j < images[i].length; j++) {
                switch (cells[i][j].getType()) {
                    case EMPTY:
                        images[i][j].setImageResource(R.drawable.empty);
                        break;
                    case RED:
                        switch (cells[i][j].getSize()) {
                            case ONE:
                                images[i][j].setImageResource(R.drawable.red01);
                                break;
                            case TWO:
                                images[i][j].setImageResource(R.drawable.red02);
                                break;
                            case THREE:
                                images[i][j].setImageResource(R.drawable.red03);
                                break;
                            case FOUR:
                                images[i][j].setImageResource(R.drawable.red04);
                                break;
                            case FIVE:
                                images[i][j].setImageResource(R.drawable.red05);
                                break;
                            case SIX:
                                images[i][j].setImageResource(R.drawable.red06);
                                break;
                            default:
                                images[i][j].setImageResource(R.drawable.empty);
                                break;
                        }
                        break;
                    case BLUE:
                        switch (cells[i][j].getSize()) {
                            case ONE:
                                images[i][j].setImageResource(R.drawable.blue01);
                                break;
                            case TWO:
                                images[i][j].setImageResource(R.drawable.blue02);
                                break;
                            case THREE:
                                images[i][j].setImageResource(R.drawable.blue03);
                                break;
                            case FOUR:
                                images[i][j].setImageResource(R.drawable.blue04);
                                break;
                            case FIVE:
                                images[i][j].setImageResource(R.drawable.blue05);
                                break;
                            case SIX:
                                images[i][j].setImageResource(R.drawable.blue06);
                                break;
                            default:
                                images[i][j].setImageResource(R.drawable.empty);
                                break;
                        }
                        break;
                }
            }
        }

		/*
		 * Report game over.
		 */
        if (board.isGameOver() == true) {
            Toast.makeText(this, getResources().getString(R.string.game_over_message), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

		/*
		 * Load sounds.
		 */


        sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        clickId = sounds.load(this, R.raw.schademans_pipe9, 1);
        finishId = sounds.load(this, R.raw.game_sound_correct, 1);

		/*
		 * Refer all image controls.
		 */
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

    /*
     * Update screen.
     */
        updateViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sounds.release();
        sounds = null;
    }}

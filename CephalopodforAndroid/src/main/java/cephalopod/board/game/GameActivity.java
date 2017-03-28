package cephalopod.board.game;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cephalopod.board.game.model.Board;
import cephalopod.board.game.model.Cell;
import cephalopod.board.game.model.ai.ArtificialIntelligence;
import cephalopod.board.game.model.ai.RandomArtificialIntelligence;

/**
 * Game screen.
 */
public class GameActivity extends MenuActivity {

    /**
     * Handler instance associated with the bot thread
     */
    private final Handler handler = new Handler();

    /**
     * Sounds pool.
     */
    private SoundPool sounds = null;

    /**
     * Click sound identifier.
     */
    private int clickId = -1;

    /**
     * Finish sound identifier.
     */
    private int finishId = -1;

    /**
     * Reference to save game button.
     */
    private Button save;

    /**
     * Reference to load game button.
     */
    private Button load;

    /**
     * Reference to new game button.
     */
    private Button newGame;

    /**
     * Reference to score TextView of human player.
     */
    private TextView yourScore;

    /**
     * Reference to score TextView of bot player.
     */
    private TextView opponentScore;

    /**
     * Reference to dynamic score with points of human player.
     */
    private TextView yourScorePoints;

    /**
     * Reference to the dynamic score with points of bot player.
     */
    private TextView opponentScorePoints;

    /**
     * An instance of a board object.
     */
    private Board board = new Board();

    /**
     * Keep references to all image view components.
     */
    private ImageView images[][] = {{null, null, null, null, null}, {null, null, null, null, null},
            {null, null, null, null, null}, {null, null, null, null, null}, {null, null, null, null, null},};

    /**
     *
     */
    private class SaveDataTask extends AsyncTask<Void, Void, Void> {

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPreExecute() {
            Message.message(GameActivity.this, "Saving game");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Void doInBackground(Void... params) {
            FileOutputStream fileOutputStream = null;
            String FILENAME = "mygame.txt";
            try {
                fileOutputStream = GameActivity.this.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
                os.writeObject(board);
            } catch (FileNotFoundException e) {
                Log.d("FILE", "File not found");
            } catch (IOException e) {
                Log.d("FILE", "IOException" + e.toString());
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.d("FILE", "IOException" + e.toString());
                }
            }
            return null;
        }
    }

    /**
     * Loading game data from a file is done in an AsyncTask.
     */
    private class LoadDataTask extends AsyncTask<String, Void, Void> {

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPreExecute() {
            Message.message(GameActivity.this, "Loading game");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Void doInBackground(String... params) {
            String file = params[0];
            FileInputStream fileInputStream = null;
            ObjectInputStream inputStream = null;

            try {
                fileInputStream = GameActivity.this.openFileInput("mygame.txt");
                inputStream = new ObjectInputStream(fileInputStream);
                board = (Board) inputStream.readObject();

            } catch (FileNotFoundException e1) {
                Log.d("FILE", "File not found");
                ;
            } catch (IOException e1) {
                Log.d("FILE", "IOException" + e1.toString());
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.d("FILE", "IOException" + e.toString());
                }
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    Log.d("FILE", "IOException" + e.toString());
                }
                return null;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    updateViews();
                }
            });
        }
    }

    /**
     * Computer opponent thread.
     */
    private Runnable ai = new Runnable() {

        /**
         * Reference to an object from RandomArtificialIntelligence
         */
        private ArtificialIntelligence bot = new RandomArtificialIntelligence();

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {

            /*
             * If the game is over there is no need bot to play.
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
             * Bot generates move.The int array move contains coordinates x and y of the bot's move.
             */
            int move[] = bot.move(board.getCells(), Cell.Type.play(board.getTurn() % 2));

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
            runOnUiThread(new Runnable() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    updateViews();
                }
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * Cells on click listener.
     */
    private View.OnClickListener click = new View.OnClickListener() {

        /**
         * Listener sets an image with dice number for a cell after a valid move, checks for winner, updates board after a click
         *
         * @param view
         */
        @Override
        public void onClick(View view) {

            /**
             * If the game is over there is nothing bot can do.
             */
            if (board.isGameOver() == true) {
                return;
            }

			/*
             * If the human player has turn also nothing to be done.
			 */
//            if (board.getTurn() % 2 != 0) {
//                return;
//            }

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
                    handler.postDelayed(ai, 300);
                }
            }

			/*
             * Update user interface.
             *
			 */
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateViews();
                }
            });
        }
    };

    /**
     * Update all visual controls.
     */
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
        /**
         * Announce score of players.
         */
        yourScorePoints.setText(board.score().get(Cell.Type.RED) + "");
        opponentScorePoints.setText(board.score().get(Cell.Type.BLUE) + "");

        /*
         * Report game over.
		 */
        if (board.isGameOver() == true) {
            Message.message(this, getResources().getString(R.string.game_over_message));
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

        /**
         * Reference to the the Save button;
         */
        save = (Button) findViewById(R.id.button_save);

        /**
         * As a user navigates out of our app on a click of save button the last game is saved to a file.
         *
         */
        save.setOnClickListener(new View.OnClickListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void onClick(View v) {
                SaveDataTask saveData = new SaveDataTask();
                saveData.execute();
            }
        });

        load = (Button) findViewById(R.id.button_load);

        /**
         * As a user navigates back to our app on a click of load button the last game is restored from a  file.
         *
         */
        load.setOnClickListener(new View.OnClickListener() {

            /**
             }
             * {@inheritDoc}
             */
            @Override
            public void onClick(View v) {
                LoadDataTask loadData = new LoadDataTask();
                loadData.execute("mygame.txt");
            }
        });

        newGame = (Button) findViewById(R.id.button_reset);

        /**
         * Play new game functionality.
         *
         */
        newGame.setOnClickListener(new View.OnClickListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void onClick(View v) {
                board.reset();
                /*
             * Update user interface.
			 */
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateViews();
                    }
                });
            }
        });

        /**
         * TextViews for score set to objects
         */
        yourScore = (TextView) findViewById(R.id.your_score);
        opponentScore = (TextView) findViewById(R.id.opponent_score);

        /**
         * TextViews for score update set to objects
         */
        yourScorePoints = (TextView) findViewById(R.id.your_score_points);
        opponentScorePoints = (TextView) findViewById(R.id.opponent_score_points);

        /**
         * Update Game screen.
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("board", board);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        board = (Board) savedInstanceState.getSerializable("board");
        runOnUiThread(new Runnable() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void run() {
                updateViews();
            }
        });
    }
}


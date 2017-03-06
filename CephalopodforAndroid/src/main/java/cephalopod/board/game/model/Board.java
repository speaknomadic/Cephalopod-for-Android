package cephalopod.board.game.model;

import java.util.HashMap;
import java.util.Map;

import cephalopod.board.game.model.Cell.Size;
import cephalopod.board.game.model.Cell.Type;

//TODO Add Java Doc comments.
public class Board {
    /**
     * Height size in rows.
     */
    public static final int ROWS = 5;

    /**
     * Width size in columns.
     */
    public static final int COLS = 5;

    /**
     * Capture if less than this constant.
     */
    public static final int CAPTURE_IF_LESS = 6;

    /**
     * Innitialize number of turns.
     */
    private int turn = 0;

    /**
     * Innitialize if the game is over.
     */
    private boolean gameOver = false;

    /**
     * Innitialize the cells.
     */
    private Cell cells[][] = initial();

    /**
     * Copy constructor.
     *
     * @param original The original object.
     */
    public Board(Board original) {
        this.turn = original.turn;
        this.gameOver = original.gameOver;

        cells = initial();

        for (int i = 0; i < cells.length && i < original.cells.length; i++) {
            for (int j = 0; j < cells[i].length && j < original.cells[i].length; j++) {
                this.cells[i][j] = original.cells[i][j];
            }
        }
    }

    /**
     * Constructor withoout parameters.
     */
    public Board() {
        super();
    }

    /**
     * Initial board state static factory function.
     *
     * @return Initial board state.
     */
    private static Cell[][] initial() {
        Cell result[][] = {
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO)},
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.RED, Size.FIVE), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO)},
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO)},
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.BLUE, Size.FIVE), new Cell(Type.EMPTY, Size.ZERO)},
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO)},};

        return result;
    }

    /**
     * Check if the move is valid.
     *
     * @return True if it is a valid move false otherwise.
     */
    public boolean isValid(int x, int y) {
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[x].length) {
            return false;
        }

        if (cells[x][y].getType() != Type.EMPTY) {
            return false;
        }

        return true;
    }

    /**
     * Count the size of all neighbours.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return Total size of the neighbours.
     */
    private int neighbours(int x, int y) {
        int amount = 0;

        if (x > 0) {
            amount += cells[x - 1][y].getSize().value();
        }

        if (y > 0) {
            amount += cells[x][y - 1].getSize().value();
        }

        if (x < cells.length - 1) {
            amount += cells[x + 1][y].getSize().value();
        }

        if (y < cells[x].length - 1) {
            amount += cells[x][y + 1].getSize().value();
        }

        return amount;
    }

    /**
     * Remove neighbours.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    private void remove(int x, int y) {
        if (x > 0) {
            cells[x - 1][y].setType(Type.EMPTY);
            cells[x - 1][y].setSize(Size.ZERO);
        }

        if (y > 0) {
            cells[x][y - 1].setType(Type.EMPTY);
            cells[x][y - 1].setSize(Size.ZERO);
        }

        if (x < cells.length - 1) {
            cells[x + 1][y].setType(Type.EMPTY);
            cells[x + 1][y].setSize(Size.ZERO);
        }

        if (y < cells[x].length - 1) {
            cells[x][y + 1].setType(Type.EMPTY);
            cells[x][y + 1].setSize(Size.ZERO);
        }
    }

    /**
     * Initialize the game in the starting conditions.
     */
    public void reset() {
        turn = 0;
        gameOver = false;
        cells = initial();
    }

    /**
     * Handle player's click.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return True if the click was valid move, false otherwise.
     */
    public boolean click(int x, int y) {
        /*
         * Empty cells can not be clicked.
		 */
        if (cells[x][y].getType() != Cell.Type.EMPTY) {
            return false;
        }

        int neighbours = neighbours(x, y);
        if (neighbours == 0 || neighbours >= CAPTURE_IF_LESS) {
            cells[x][y] = new Cell(Cell.Type.play(turn), Size.ONE);
        } else {
            cells[x][y] = new Cell(Cell.Type.play(turn), Size.instanceOf(neighbours));
            remove(x, y);
        }

        return true;
    }

    /**
     * Check for winner on the board.
     *
     * @return True if there is a winner, false otherwise.
     */
    public boolean hasWinner() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getType() == Cell.Type.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Provide winner as cell type value.
     *
     * @return Winner type or empty value if the board is empty.
     */
    public Cell.Type winner() {
        Map<Type, Integer> counters = new HashMap<Type, Integer>();

		/*
         * Initialize counters.
		 */
        for (Type type : Type.values()) {
            counters.put(type, 0);
        }

		/*
         * Score players.
		 */
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Type type = cells[i][j].getType();
                counters.put(type, 1 + counters.get(type));
            }
        }

        /*
         * Empty cells has no information for the winning player.
         */
        counters.remove(Type.EMPTY);

        /*
         * Find the winner.
         */
        int max = 0;
        Type winner = Type.EMPTY;
        for (Type type : Type.values()) {
            if (counters.get(type) < max) {
                continue;
            }

            winner = type;
            max = counters.get(type);
        }

        return winner;
    }

    /**
     * Evaluate scores of different players.
     *
     * @return Counters with the success of the different players on the board.
     */
    public Map<Type, Integer> score() {
        Map<Type, Integer> result = new HashMap<Type, Integer>();

		/*
         * Initialize counters.
		 */
        for (Type type : Type.values()) {
            result.put(type, 0);
        }

		/*
         * Count occupied cells.
		 */
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Type type = cells[i][j].getType();
                result.put(type, 1 + result.get(type));
            }
        }

        return result;
    }

    /**
     * Turn getter.
     *
     * @return Turn number.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Game over flag getter.
     *
     * @return Game over state.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Game over flag setter.
     */
    public void setGameOver() {
        gameOver = true;
    }

    /**
     * Move to next turn.
     */
    public void next() {
        turn++;
    }
}

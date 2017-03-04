package cephalopod.board.game.model;

import java.util.HashMap;
import java.util.Map;

import cephalopod.board.game.model.Cell.Size;
import cephalopod.board.game.model.Cell.Type;

//TODO Add Java Doc comments.
public class Board {
	public static final int ROWS = 5;

	public static final int COLS = 5;

	private static Cell[][] initial() {
		Cell result[][] = {
				{ new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
						new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO) },
				{ new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.RED, Size.FIVE), new Cell(Type.EMPTY, Size.ZERO),
						new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO) },
				{ new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
						new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO) },
				{ new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
						new Cell(Type.BLUE, Size.FIVE), new Cell(Type.EMPTY, Size.ZERO) },
				{ new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
						new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO) }, };

		return result;
	}

	private int turn = 0;

	private boolean gameOver = false;

	private Cell cells[][] = initial();

	/**
	 * Initialize the game in the starting conditions.
	 */
	public void reset() {
		this.cells = initial();
	}

	/**
	 * Handle player's click.
	 * 
	 * @param x
	 *            X coordinate.
	 * @param y
	 *            Y coordinate.
	 * 
	 * @return True if the click was valid move, false otherwise.
	 */
	public boolean click(int x, int y) {
		// TODO
		if (x > 0 && x < 5 && y > 0 && y < 5) {
			return true;
		}

		return false;
	}

	/**
	 * Check for winner on the board.
	 * 
	 * @return True if there is a winner, false otherwise.
	 */
	public boolean hasWinner() {
		if (this.gameOver == true) {
			return true;
		}
		return false;
	}

	/**
	 * Provide winner type.
	 * 
	 * @return Winner type.
	 */
	public Cell.Type winner() {
		int counterForEmpty = 0;
		int counterForRed = 0;
		int counterForBlue = 0;
		if (this.gameOver == true) {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[0].length; j++) {
					if (cells[i][j].getType() == Type.EMPTY) {
						counterForEmpty++;
					}
					if (cells[i][j].getType() == Type.RED) {
						counterForRed++;
					}
					if (cells[i][j].getType() == Type.BLUE) {
						counterForBlue++;
					}
				}
			}
		} else {
			System.out.println("You havent finish the game yet.");
		}
		Type t = Type.EMPTY;
		if (counterForBlue > counterForRed) {
			t = Type.BLUE;
		}
		if (counterForBlue < counterForRed) {
			t = Type.RED;
		} else {
			t = null;
		}

		return t;
	}

	/**
	 * Evaluate scores of different players.
	 * 
	 * @return Counters with the success of the different players on the board.
	 */
	public Map<Type, Integer> score() {
		Map<Type, Integer> result = new HashMap<Type, Integer>();
		int counterForEmpty = 0;
		int counterForRed = 0;
		int counterForBlue = 0;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j].getType() == Type.EMPTY) {
					counterForEmpty++;

				}
				if (cells[i][j].getType() == Type.RED) {
					counterForRed++;
					result.put(Type.RED, counterForRed);
				}
				if (cells[i][j].getType() == Type.BLUE) {
					counterForBlue++;
					result.put(Type.BLUE, counterForBlue);
				}
			}
		}

		return result;
	}

	// TODO Create constructors. - no need of them

	// TODO Create getters and setters.
	public int getTurn() {
		return turn;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	// TODO Create equals and hash code methods. - no need of them
}
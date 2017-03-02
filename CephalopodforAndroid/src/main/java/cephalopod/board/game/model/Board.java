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
		//TODO
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
		//TODO
		
		return false;
	}

	/**
	 * Check for winner on the board.
	 * 
	 * @return True if there is a winner, false otherwise.
	 */
	public boolean hasWinner() {
		//TODO
		
		return false;
	}
	
	/**
	 * Provide winner type.
	 * 
	 * @return Winner type.
	 */
	public Cell.Type winner() {
		//TODO 
		
		return Type.EMPTY;
	}
	
	/**
	 * Evaluate scores of different players.
	 * 
	 * @return Counters with the success of the different players on the board.
	 */
	public Map<Type, Integer> score() {
		Map<Type, Integer> result = new HashMap<Type, Integer>();

		//TODO 

		return result;
	}
	
	//TODO Create constructors.
	//TODO Create getters and setters.
	//TODO Create equals and hash code methods.
}

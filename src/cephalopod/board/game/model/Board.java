package cephalopod.board.game.model;

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
	
	private Cell cells[][] = initial();
	
	//TODO Create constructors.
	//TODO Create getters and setters.
	//TODO Create equals and hash code methods.
}

package cephalopod.board.game.model.ai;

import cephalopod.board.game.model.Cell;

//TODO Add Java Doc comments.
public interface ArtificialIntelligence {
	public int[] move(Cell cells[][], Cell.Type player) throws ImpossibleMoveException;
}

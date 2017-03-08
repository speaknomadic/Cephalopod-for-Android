package cephalopod.board.game.model.ai;

import cephalopod.board.game.model.Cell;

/**
 * An interface which the AI algorithms implement.
 */
public interface ArtificialIntelligence {

    public int[] move(Cell cells[][], Cell.Type player) throws ImpossibleMoveException;
}

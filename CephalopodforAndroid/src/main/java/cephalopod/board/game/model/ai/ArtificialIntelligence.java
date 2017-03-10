package cephalopod.board.game.model.ai;

import cephalopod.board.game.model.Cell;

/**
 * An interface which the AI algorithms implement.
 */
public interface ArtificialIntelligence {

    /**
     * Generate bot`s move
     * @param cells - the board
     * @param player - type of the player
     * @return
     * @throws ImpossibleMoveException
     */
    public int[] move(Cell cells[][], Cell.Type player) throws ImpossibleMoveException;
}

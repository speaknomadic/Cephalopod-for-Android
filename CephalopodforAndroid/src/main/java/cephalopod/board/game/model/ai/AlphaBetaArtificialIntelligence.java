package cephalopod.board.game.model.ai;

import cephalopod.board.game.model.Cell;
import cephalopod.board.game.model.Cell.Type;

/**
 * AlphaBeta pruning search logic for the AI.
 */
public class AlphaBetaArtificialIntelligence implements ArtificialIntelligence {

    /**
     * Generate bot`s move
     *
     * @param cells  - the board
     * @param player - type of the player
     * @return
     * @throws ImpossibleMoveException
     */
    @Override
    public int[] move(Cell[][] cells, Type player) throws ImpossibleMoveException {
        /**
         * It stops completely evaluating a move when at least one possibility has been found
         * that proves the move to be worse than a previously examined move.
         */

		/*
         * Return x and y of the move as two cells 1D array.
		 */
        return new int[]{};
    }

}

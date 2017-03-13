package cephalopod.board.game.model.ai;

import cephalopod.board.game.model.Cell;
import cephalopod.board.game.model.Cell.Type;

/**
 * Monte Carlo search logic for the AI.
 */
public class MonteCarloArtificialIntelligence implements ArtificialIntelligence {

    /**
     * Generate bot`s move
     * @param cells - the board
     * @param player - type of the player
     * @return
     * @throws ImpossibleMoveException
     */
    @Override
    public int[] move(Cell[][] cells, Type player) throws ImpossibleMoveException {
        /**
         *  For each position, all feasible moves are determined: k random games are played out to the very end,
         *  and the scores are recorded.
         *  The move leading to the best score is chosen.
         */

		/*
         * Return x and y of the move as two cells 1D array.
		 */
        return new int[]{};
    }

}

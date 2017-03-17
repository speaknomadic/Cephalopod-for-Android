package cephalopod.board.game.model.ai;

import cephalopod.board.game.model.Cell;
import cephalopod.board.game.model.Cell.Type;

/**
 * MinMax search logic for the AI.
 */
public class MinMaxArtificialIntelligence implements ArtificialIntelligence {

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
         *  If player A can win in one move, his best move is that winning move.
         *  If bot knows that one move will lead to the situation where player A can win in one move,
         *  while another move will lead to the situation where player A can, at best, draw,
         *  then bot's best move is the one leading to a draw.
         */

		/*
         * Return x and y of the move as two cells 1D array.
		 */
        return new int[]{};
    }

}

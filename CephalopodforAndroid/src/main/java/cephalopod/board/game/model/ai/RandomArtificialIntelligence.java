package cephalopod.board.game.model.ai;

import cephalopod.board.game.model.Cell;
import cephalopod.board.game.model.Cell.Type;
import cephalopod.board.game.model.Util;

/**
 * Random search logic for the AI.
 */
public class RandomArtificialIntelligence implements ArtificialIntelligence {

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
         * Generate random move for the bot.
         */

        int x = -1;
        int y = -1;
        do {
            x = Util.PRNG.nextInt(cells.length);
            y = Util.PRNG.nextInt(cells[x].length);
        } while (cells[x][y].getType() != Type.EMPTY);
        return new int[] {x, y
        };
		/*
         * Return x and y of the move as two cells 1D array.
		 */
        //return new int[]{};
    }

}

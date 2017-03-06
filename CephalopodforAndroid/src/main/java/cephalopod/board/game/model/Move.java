package cephalopod.board.game.model;

/**
 * Single game move.
 *
 */
public class Move {

    /**
     * X coordinate of the move.
     */
    private int x;

    /**
     * Y coordinate of the move.
     */
    private int y;

    /**
     * Is the move valid flag.
     */
    private boolean valid;

    /**
     * Constructor with all parameters.
     *
     * @param x
     *            X coordinate of the move.
     * @param y
     *            Y coordinate of the move.
     * @param valid
     *            Is the move valid.
     */
    public Move(int x, int y, boolean valid) {
        super();

        this.x = x;
        this.y = y;
        this.valid = valid;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Set the x.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Set the valid.
     * @param valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (valid ? 1231 : 1237);
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Move other = (Move) obj;
        if (valid != other.valid) {
            return false;
        }
        if (x != other.x) {
            return false;
        }
        if (y != other.y) {
            return false;
        }
        return true;
    }
}

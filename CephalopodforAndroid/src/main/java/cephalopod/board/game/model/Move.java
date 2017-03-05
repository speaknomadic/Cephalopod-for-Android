package cephalopod.board.game.model;

//TODO Add Java Doc comments.
public class Move {

    // TODO Add Java Doc comments.
    private int x;

    // TODO Add Java Doc comments.
    private int y;

    // TODO Add Java Doc comments.
    private boolean valid;

    //TODO Create constructors.
    public Move(int x, int y, boolean valid) {
        super();

        this.x = x;
        this.y = y;
        this.valid = valid;
    }

    // TODO Add Java Doc comments.
    public int getX() {
        return x;
    }

    // TODO Add Java Doc comments.
    public void setX(int x) {
        this.x = x;
    }

    // TODO Add Java Doc comments.
    public int getY() {
        return y;
    }

    // TODO Add Java Doc comments.
    public void setY(int y) {
        this.y = y;
    }

    // TODO Add Java Doc comments.
    public boolean isValid() {
        return valid;
    }

    // TODO Add Java Doc comments.
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    // TODO Add Java Doc comments.
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (valid ? 1231 : 1237);
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    // TODO Add Java Doc comments.
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

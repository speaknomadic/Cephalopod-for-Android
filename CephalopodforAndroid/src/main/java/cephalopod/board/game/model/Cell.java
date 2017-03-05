package cephalopod.board.game.model;

//TODO Add Java Doc comments.
public class Cell {

    // TODO Add Java Doc comments.
    private Type type;

    // TODO Add Java Doc comments.
    private Size size;

    // TODO Add Java Doc comments.
    public Cell(Type type, Size size) {
        this.type = type;
        this.size = size;
    }

    //TODO Create getters and setters.
    public Type getType() {
        return type;
    }

    //TODO Create getters and setters.
    public void setType(Type type) {
        this.type = type;
    }

    //TODO Create getters and setters.
    public Size getSize() {
        return size;
    }

    //TODO Create getters and setters.
    public void setSize(Size size) {
        this.size = size;
    }

    //TODO Create getters and setters.
    public void showType() {
        System.out.println(this.getType());
    }

    // TODO Add Java Doc comments.
    public enum Size {
        ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

        // TODO Add Java Doc comments.
        public static Size instanceOf(int size) {
            switch (size) {
                case 0:
                    return Size.ZERO;
                case 1:
                    return Size.ONE;
                case 2:
                    return Size.TWO;
                case 3:
                    return Size.THREE;
                case 4:
                    return Size.FOUR;
                case 5:
                    return Size.FIVE;
                case 6:
                    return Size.SIX;

                default:
                    throw new RuntimeException("Invalid size of: " + size);
            }
        }

        // TODO Add Java Doc comments.
        private int value = -1;

        // TODO Add Java Doc comments.
        private Size(int id) {
            this.value = id;
        }

        // TODO Add Java Doc comments.
        public int getValue() {
            return value;
        }
    }

    // TODO Add Java Doc comments.
    public enum Type {
        EMPTY(0, " "), RED(1, "R"), BLUE(2, "B");

        // TODO Add Java Doc comments.
        private int id = -1;

        // TODO Add Java Doc comments.
        private String symbol = "";

        // TODO Add Java Doc comments.
        private Type(int id, String symbol) {
            this.id = id;
            this.symbol = symbol;
        }

        // TODO Add Java Doc comments.
        public int id() {
            return id;
        }

        // TODO Add Java Doc comments.
        public String symbol() {
            return symbol;
        }
    }

    // TODO Add Java Doc comments.
    @Override
    public int hashCode() {
        return this.type.hashCode() + this.size.hashCode();
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
        Cell other = (Cell) obj;
        if (size != other.size) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }
}

package cephalopod.board.game.model;

/**
 * Cell class describes single cell.
 */
public class Cell {

    /**
     * Cell type.
     */

    private Type type;

    /**
     * Cell size.
     */
    private Size size;

    /**
     * Constructor.
     *
     * @param type
     *            Cell type.
     * @param size
     *            Cell size.
     */
    public Cell(Type type, Size size) {
        this.type = type;
        this.size = size;
    }

    /**
     * Cell type getter.
     *
     * @return Type of the cell.
     */
    public Type getType() {
        return type;
    }

    /**
     * Cell type setter.
     *
     * sets Type of the cell.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Cell size getter.
     *
     * @return Size of the cell.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Cell size setter.
     *
     * sets Size of the cell.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * shows Type of the cell.
     */
    public void showType() {
        System.out.println(this.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.type.hashCode() + this.size.hashCode();
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
        Cell other = (Cell) obj;
        if (size != other.size) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

    /**
     * Cell size.
     */
    public enum Size {
        ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

        /**
         * Size value of the cell.
         */
        private int value = -1;

        /**
         * Constructor.
         *
         * @param id
         *            Size value of the cell.
         */
        private Size(int id) {
            this.value = id;
        }

        /**
         * Convert from int to Size.
         * @param size
         * @return Size of the cell.
         */
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

        /**
         * Cell value getter
         * @return value.
         */
        public int getValue() {
            return value;
        }
    }

    /**
     * Cell type.
     */
    public enum Type {
        EMPTY(0, " "), RED(1, "R"), BLUE(2, "B");

        /**
         * Size value of the cell.
         */
        private int id = -1;

        /**
         * String symbol value of the cell.
         */
        private String symbol = "";

        /**
         * Constructor.
         *
         * @param id
         *            Object identifier.
         * @param symbol
         *            Symbols used in to string printing.
         */
        private Type(int id, String symbol) {
            this.id = id;
            this.symbol = symbol;
        }

        /**
         * Object identifier getter.
         *
         * @return Identifier.
         */
        public int id() {
            return id;
        }

        /**
         * Object symbol getter.
         *
         * @return String.
         */
        public String symbol() {
            return symbol;
        }
    }
}

package cephalopod.board.game.model;

//TODO Add Java Doc comments.
public class Cell {

	// TODO Add Java Doc comments.
	public enum Size {
		ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

		private int value = -1;

		private Size(int id) {
			this.value = id;
		}
	}

	// TODO Add Java Doc comments.
	public enum Type {
		EMPTY(0, " "), RED(1, "R"), BLUE(2, "B");

		private int id = -1;

		private String symbol = "";

		private Type(int id, String symbol) {
			this.id = id;
			this.symbol = symbol;
		}
	}

	private Type type;

	private Size size;

	public Cell(Type type, Size size) {
		this.type = type;
		this.size = size;
	}

	
	
	//TODO Create getters and setters.
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
	//TODO Create equals and hash code methods.

	@Override
	public int hashCode() {
		return this.type.hashCode() + this.size.hashCode();
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (size != other.size)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
}

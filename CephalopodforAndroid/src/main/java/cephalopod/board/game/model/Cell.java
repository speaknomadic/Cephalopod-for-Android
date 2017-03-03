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
	//TODO Create equals and hash code methods.
}

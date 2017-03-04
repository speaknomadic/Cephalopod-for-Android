package cephalopod.board.game.model;

import java.util.Random;

//TODO Add Java Doc comments.
public class Move {

	private int x;

	private int y;

	private boolean valid;

	
	//TODO Create constructors.
	public Move(int x, int y, boolean valid) {
		if(x > 0 && x < 5 ){
		this.x = x;
		}
		else{
			this.x = new Random().nextInt(5);
		}
		
		if(y > 0 && y < 5 ){
			this.y = y;
			}
			else{
				this.y = new Random().nextInt(5);
			}
		this.valid = valid;
	}


	//TODO Create getters and setters.
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public boolean isValid() {
		return valid;
	}


	public void setValid(boolean valid) {
		this.valid = valid;
	}
	//TODO Create equals and hash code methods.
//not sure if we need this.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (valid ? 1231 : 1237);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (valid != other.valid)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
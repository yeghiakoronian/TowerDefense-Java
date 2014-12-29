package core.domain.waves;

/**
 * <b>this class definded because in java we don't have single pair 
 * to keep only one key value pair, so for keeping the position we crate a Position class</b> 
 * @author Team5
 * @version 0.1
 */
public class Position implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1521288002292900413L;
	
	private int x;
	private int y;
	/**
	 * for initiating this object we need to x , y for create a object as this type 
	 * @param x is the width of the actual position on the grid
	 * @param y is the height of the actual position on the grid
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * it will return the x position of an element in the map
	 * @return x 
	 */
	public int getX() {
		return x;
	}
	/**
	 * setter for x value of an element in the map
	 * @param x is the width of the actual position on the grid
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * getter method for y 
	 * @return y as int
	 */
	public int getY() {
		return y;
	}
	/**
	 * set the y 
	 * @param y as int
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * we need to Override hashCode method to use this code in HashMap 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	/**
	 * to using in a map and compare the positions we need to override the equals method
	 * @return true, if it is done successfully
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	/**
	 * <code>
	 * return "["+ this.x +","+this.y+"]";
	 * </code>
	 * @return String 
	 */
	@Override
	public String toString() {
		return "["+ this.x +","+this.y+"]"; 
	}

}

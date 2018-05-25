package Controller;

/**
* code to manage position
*/
public class Position {
	private int x;
	private int y;
	
	/**
	* constructor
	* @param  x horizontal coordinate
	* @param y vertical coordinate
	*/
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	* @return returns horizontal coorindate positon
	*/
	public int getX() {
		return x;
	}
	
	/**
	* @return returns  vertical coordinate position
	*/
	public int getY() {
		return y;
	}
	
	/**
	* sets position in horizontal direction
	* @param x horizontal coordinate to be set
	*/
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	* sets position in vertical direction
	* @param y veritcal coordinate to be set
	*/
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	* clones a position
	* @return and returns this cloned position
	*/
	public Position clone() {
		return new Position(x, y);
	}

}

package Controller;

/**
* code to manage car movement
*/
public class Move {
	
	private int carId;
	private int direction;
	private int oppDirection;
	
	/**
	* constructor
	* @param carId unique identifier of car
	* @param direction direction to be moved
	*/
	public Move(int carId, int direction) {
		this.carId = carId;
		this.direction = direction;
		setOppDirection(direction);
	}

	/**
	* function to set opposite direction which is used for the undo function to know which way to traverse to back track
	*/
	private void setOppDirection(int direction) {
		if(direction == Constants.DOWN) {
			oppDirection = Constants.UP;
		}
		else if(direction == Constants.UP) {
			oppDirection = Constants.DOWN;
		}
		else if(direction == Constants.LEFT) {
			oppDirection = Constants.RIGHT;
		}
		else if(direction == Constants.RIGHT) {
			oppDirection = Constants.LEFT;
		}
	}

	/**
	* @return return unique identifier of a car
	*/
	public int getCarId() {
		return carId;
	}
	
	/**
	* @return returns direction
	*/
	public int getDirection() {
		return direction;
	}
	
	/**
	* @return returns opposite direction of a move
	*/
	public int getOppDirection() {
		return oppDirection;
	}
}

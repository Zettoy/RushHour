package Controller;

public class Move {
	
	private int carId;
	private int direction;
	private int oppDirection;
	
	public Move(int carId, int direction) {
		this.carId = carId;
		this.direction = direction;
		setOppDirection(direction);
	}

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

	public int getCarId() {
		return carId;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getOppDirection() {
		return oppDirection;
	}
}

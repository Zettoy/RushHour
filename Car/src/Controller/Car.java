package Controller;

/**
* Code for manipulation of the car
* includes checking of direction, position, type of car, length and moving the car
*/
public class Car implements CarInterface {	
	private int id;
	private int length;
	private Position position;
	private Character direction;
	
	/**
	* Constructor
	* @param id unique identified for each car
	* @param length length of the car
	* @param poisition position of the car
	* @param direction direction that the car is facing
	*/
	public Car (int id, int length, Character direction, Position position) {
		this.id = id;
		this.length = length;
		this.position = position;
		this.direction = direction;
	}
	
	/**
	* moves the car in desired direction
	* @param direction desired direction for car to be moved
	*/
	@Override
	public void move(int direction) {
		switch(direction) {
			case Constants.LEFT:
				moveLeft();
				break;
			case Constants.RIGHT:
				moveRight();
				break;
			case Constants.UP:
				moveUp();
				break;
			case Constants.DOWN:
				moveDown();
				break;
		}
	}
	
	/**
	* @return returns current position of car
	*/
	public Position getPosition() {
		return position;
	}
	
	/**
	* @return returns current direction of car
	*/
	@Override
	public Character getDirection() {
		return direction;
	}
	
	/**
	* @return returns true if car is red, otherwise false
	* checks if car is the car needed to get to goal state (i.e red car)
	*/
	@Override
	public boolean isRedCar() {
		if (id == Constants.RED) return true;
		
		return false;
	}

	/**
	* @return returns the unique identified for specified car
	*/
	@Override
	public int getId() {
		return id;
	}
	
	/**
	* @return returns the length of the car
	*/
	@Override
	public int getLength() {
		return length;
	}
	
	/**
	* @return makes a copy of the car
	*/
	@Override
	public CarInterface clone() {
		return new Car(id, length, direction, position.clone());
	}
	
	/**
	* sets the position of the car left on space i.e -1 on x co ordinate
	*/
	private void moveLeft() {
		position.setX(position.getX() - 1);
		
	}
	
	/**
	* sets the position of the car right one space i.e +1 on x co ordinate
	*/
	private void moveRight() {
		position.setX(position.getX() + 1);
	}
	
	/**
	* sets the position of the car up one space i.e -1 on y co ordinate
	*/
	private void moveUp() {
		position.setY(position.getY() - 1);
		
	}
	
	/**
	* sets the position of the car down one space i.e +1 on y co ordinate
	*/
	private void moveDown() {
		position.setY(position.getY() + 1);
		
	}
}

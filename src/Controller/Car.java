package Controller;

public class Car implements CarInterface {	
	private int id;
	private int length;
	private Position position;
	private Character direction;
	
	public Car (int id, int length, Character direction, Position position) {
		this.id = id;
		this.length = length;
		this.position = position;
		this.direction = direction;
	}
	
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
	
	public Position getPosition() {
		return position;
	}
	
	@Override
	public Character getDirection() {
		return direction;
	}
	
	@Override
	public boolean isRedCar() {
		if (id == Constants.RED) return true;
		
		return false;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public int getLength() {
		return length;
	}
	
	@Override
	public CarInterface clone() {
		return new Car(id, length, direction, position.clone());
	}
	
	private void moveLeft() {
		position.setX(position.getX() - 1);
		
	}
	
	private void moveRight() {
		position.setX(position.getX() + 1);
	}
	
	private void moveUp() {
		position.setY(position.getY() - 1);
		
	}
	
	private void moveDown() {
		position.setY(position.getY() + 1);
		
	}
}

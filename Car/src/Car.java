
public class Car implements CarInterface {
	private static final int RED = 1;
	
	private static final int LEFT  = 0;
	private static final int RIGHT = 1;
	private static final int UP    = 2;
	private static final int DOWN  = 3;
	
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
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			case UP:
				moveUp();
				break;
			case DOWN:
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
	
	public int getLength() {
		return length;
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

	@Override
	public boolean isRedCar() {
		if (id == RED) return true;
		
		return false;
	}

	@Override
	public int getId() {
		return id;
	}
	
}

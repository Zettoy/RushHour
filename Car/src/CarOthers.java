
public class CarOthers implements CarInterface {
	private static final int LEFT  = 0;
	private static final int RIGHT = 1;
	private static final int UP    = 2;
	private static final int DOWN  = 3;
	private int length;
	private Position position;
	private Character direction;
	
	public CarOthers (int length, Character direction, Position position) {
		this.length = length;
		this.position = position;
		this.direction = direction;
	}
	
	@Override
	public void move(int command) {
		if (command ==  LEFT) moveLeft();
		if (command == RIGHT) moveRight();
		if (command ==    UP) moveUp();
		if (command ==  DOWN) moveDown();
	}
	
	public Position getPosition() {
		return position;
	}
	
	public Character getDirection() {
		return direction;
	}
	
	public int getLength() {
		return length;
	}
	
	private void moveLeft() {
		
		
	}
	
	private void moveRight() {
		
		
	}
	
	private void moveUp() {
		
		
	}
	
	private void moveDown() {
		
		
	}
	
}

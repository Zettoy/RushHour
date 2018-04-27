
public class CarRed implements CarInterface {
	private static final int LEFT  = 0;
	private static final int RIGHT = 1;
	private Position position;
	
	public CarRed (Position position) {
		this.position = position;
	}
	@Override
	public void move(int command) {
		if (command ==  LEFT) moveLeft();
		if (command == RIGHT) moveRight();
	}
	
	public Position getPosition() {
		return position;
	}
	
	private void moveLeft() {
		
		
	}
	
	private void moveRight() {
		
		
	}
}

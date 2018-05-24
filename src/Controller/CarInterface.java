package Controller;

public interface CarInterface {
	void move(int direction);
	boolean isRedCar();
	int getLength();
	Character getDirection();
	Position getPosition();
	int getId();
	
	CarInterface clone();
}

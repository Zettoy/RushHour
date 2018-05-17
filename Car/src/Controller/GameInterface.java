package Controller;

public interface GameInterface {
	void gameStart();
	void moveCar(int direction);
	void selectCar(int carId);
	void gameRestart();
	MapInterface getMap();
	int getSelectedCar();
	boolean isWin();
	int getMovesMade();
}

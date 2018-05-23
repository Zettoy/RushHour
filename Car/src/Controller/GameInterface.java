package Controller;

public interface GameInterface {
	void gameStart();
	void moveCar(int direction);
	void selectCar(int carId);
	void undo();
	void gameRestart();
	MapInterface getMap();
	int getSelectedCar();
	boolean isWin();
	int getMovesMade();
	void nextLevel();
	void setPanel(GamePanel panel);
}

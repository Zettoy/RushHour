package Controller;

public interface GameInterface {
	void gameStart(int difficulty);
	void moveCar(int direction);
	void selectCar(int carId);
	void undo();
	void gameRestart();
	MapInterface getMap();
	int getSelectedCar();
	boolean isWin();
	boolean isOver();
	int getMovesMade();
	void nextLevel();
	void quit();
	void setPanel(GamePanel panel);
	int getLevel();
}

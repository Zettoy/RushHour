package Controller;

import java.util.LinkedList;

public class Game implements GameInterface {
	private MapInterface activeMap;
	private MapInterface initMap;
	private MapGeneratorInterface mapGenerator;
	private LinkedList<Move> moves;
	private int selectedCar;
	private int movesMade;
	boolean scoreSaved;
	LeaderBoard leaderBoard;
	GamePanel panel;
	
	private BoundedQueue<MapInterface> mapQueue;

	
	public Game (int difficulty) {
		mapQueue = new BoundedQueue<MapInterface>(3);
		moves = new LinkedList<>();
		leaderBoard = new LeaderBoard(3, difficulty);
	}
	
	@Override
	public void gameStart() {
		scoreSaved = false;
		mapGenerator = new MapGenerator(mapQueue, Constants.INTERMEDIATE);
		new Thread(mapGenerator).start();
		generateMap();
		movesMade = 0;
		activeMap = initMap.clone();
		selectedCar = 0;
	}
	
	@Override
	public void moveCar(int direction) {
		if(activeMap.moveCar(selectedCar, direction)) {
			Move newMove = new Move(selectedCar, direction);
			moves.add(newMove);
			movesMade++;
		}

	}
		
	@Override
	public void nextLevel() {
		scoreSaved = false;
		generateMap();
		activeMap = initMap.clone();
		movesMade = 0;
		selectedCar = 0;
	}
	
	@Override
	public void selectCar(int carId) {
		selectedCar = carId;
	}
	
	@Override
	public void undo() {
		if(moves.size() > 0) {
			Move lastMove = moves.removeLast();
			if(activeMap.moveCar(lastMove.getCarId(), lastMove.getOppDirection())) {
				movesMade--;
			}
		}
	}
	
	@Override
	public boolean isWin() {
		if (activeMap.getCar(Constants.RED).getPosition().getX() == 4) {
			if(!scoreSaved) {
				leaderBoard.addScore("test", panel.getTime(), movesMade);
				scoreSaved = true;
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public MapInterface getMap() {
		return activeMap;
	}
	
	@Override
	public int getSelectedCar() {
		return selectedCar;
	}

	@Override
	public void gameRestart() {
		scoreSaved = false;
		movesMade = 0;
		activeMap = initMap.clone();
		selectedCar = 0;
		
	}

	@Override
	public int getMovesMade() {
		return movesMade;
	}

	private void generateMap() {
		try {
			initMap = mapQueue.remove();
		} catch (InterruptedException e) {

		}
	}

	public void setPanel(GamePanel panel) {
		this.panel = panel;
	}
	
	/* Example of using other map generators
	private void generateMapHard() {
		mapGenerator = new MapGeneratorHard();
		mapGenerator.createMap();
		initMap = mapGenerator.getMap();
	}
	 */
}

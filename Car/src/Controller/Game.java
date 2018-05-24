package Controller;

import View.InGameLeaderBoard;

import java.util.LinkedList;

public class Game implements GameInterface {
	private MapInterface activeMap;
	private MapInterface initMap;
	private MapGeneratorInterface mapGenerator;
	private LinkedList<Move> moves;
	private int selectedCar;
	private int movesMade;
	private Thread t;
	boolean leaderBoardShown;
	boolean scoreSaved;
	private LeaderBoard leaderBoard;
	private GamePanel panel;
	private InGameLeaderBoard inGameLeaderBoard;
	
	private BoundedQueue<MapInterface> mapQueue;

	private int level;
	
	public Game () {
		mapQueue = new BoundedQueue<MapInterface>(3);
		moves = new LinkedList<>();
		level = 0;
	}
	
	@Override
	public void gameStart(int difficulty) {
		mapGenerator = new MapGenerator(mapQueue, difficulty);
		t = new Thread(mapGenerator);
		t.start();
		leaderBoardShown = false;
		scoreSaved = false;
		generateMap();
		movesMade = 0;
		activeMap = initMap.clone();
		selectedCar = 0;
		leaderBoard = new LeaderBoard(3, difficulty);
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
		if(leaderBoardShown) {
			inGameLeaderBoard.removeLeaderBoard();
			leaderBoardShown = false;
		}
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
			if(!leaderBoardShown) {
				int position = leaderBoard.addScore("You", panel.getTime(), movesMade);
				Score[] scores = leaderBoard.getScores();
				inGameLeaderBoard.showScores(scores, position);
				leaderBoardShown = true;
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isOver() {
		if (level == 3) return true;
		
		return false;
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
		if(leaderBoardShown) {
			inGameLeaderBoard.removeLeaderBoard();
			leaderBoardShown = false;
		}
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
			level ++;
		} catch (InterruptedException e) {

		}
	}

	public void setPanel(GamePanel panel) {
		this.panel = panel;
		inGameLeaderBoard = new InGameLeaderBoard(this, panel);
	}

	@Override
	public void quit() {
		t.interrupt();
		mapQueue = new BoundedQueue<MapInterface>(3);
	}
	
	/* Example of using other map generators
	private void generateMapHard() {
		mapGenerator = new MapGeneratorHard();
		mapGenerator.createMap();
		initMap = mapGenerator.getMap();
	}
	 */
}

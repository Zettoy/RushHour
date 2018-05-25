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
	private int scorePosition;
	private int difficulty;
	
	public Game () {
		mapQueue = new BoundedQueue<MapInterface>(Constants.MAX_LEVEL);
		moves = new LinkedList<>();
	}
	
	@Override
	public void gameStart(int difficulty) {
		mapGenerator = new MapGenerator(mapQueue, difficulty);
		t = new Thread(mapGenerator);
		t.start();
		leaderBoardShown = false;
		scoreSaved = false;
		level = 0;
		generateMap();
		movesMade = 0;
		activeMap = initMap.clone();
		selectedCar = 0;
		leaderBoard = new LeaderBoard(3, difficulty);
		this.difficulty = difficulty;
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
		if(!scoreSaved) {
			leaderBoard = new LeaderBoard(3, difficulty);
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
				scorePosition = leaderBoard.addScore("You", panel.getTime(), movesMade);
				Score[] scores = leaderBoard.getScores();
				inGameLeaderBoard.showScores(this, panel, scores, scorePosition);
				leaderBoardShown = true;
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isWinMulti() {
		if (activeMap.getCar(Constants.RED).getPosition().getX() == 4) return true;
		
		return false;
	}
	
	@Override
	public boolean isOver() {
		if (level == Constants.MAX_LEVEL) return true;
		
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
			scoreSaved = false;
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

	public void saveScore() {
		String name = inGameLeaderBoard.getName();
		if(name != null) {
			leaderBoard.setName(name, scorePosition);
			inGameLeaderBoard.removeLeaderBoard();
			Score[] scores = leaderBoard.getScores();
			inGameLeaderBoard.showScores(this, panel, scores, 0);
			scoreSaved = true;
		}
	}

	public void setPanel(GamePanel panel) {
		this.panel = panel;
		inGameLeaderBoard = new InGameLeaderBoard();
	}

	@Override
	public void quit() {
		t.interrupt();
		mapQueue = new BoundedQueue<MapInterface>(Constants.MAX_LEVEL);
	}

	@Override
	public int getLevel() {
		return level;
		
	}
	
	@Override
	public void gameClone(MapInterface map) {
		activeMap = map.clone();	
		movesMade = 0;
		selectedCar = 0;
	}
	/* Example of using other map generators
	private void generateMapHard() {
		mapGenerator = new MapGeneratorHard();
		mapGenerator.createMap();
		initMap = mapGenerator.getMap();
	}
	 */
}

package Controller;

import Model.BoundedLinkedQueue;
import Model.LeaderBoard;
import Model.MapGenerator;
import Model.MapGeneratorInterface;
import View.GamePanel;
import View.InGameLeaderBoard;

import java.util.LinkedList;

/**
* code that keeps track of the state of the game 
* including functions to change screens from the game-playing screen to places such as generating the next level
*/
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
	
	private BoundedLinkedQueue<MapInterface> mapQueue;

	private int level;
	private int scorePosition;
	private int difficulty;
	
	/**
	* constructor
	* creates new queue to generate map
	* creates list to store moves for undo function
	*/
	public Game () {
		mapQueue = new BoundedLinkedQueue<MapInterface>(Constants.MAX_LEVEL);
		moves = new LinkedList<>();
	}
	
	/**
	* intialises the game settings including difficulty, level, score, timer, etc.
	* @param difficulty difficulty of the game that has been selected
	*/
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
	
	/**
	* moves the car in desired direction and increments counter that keeps track of moves
	* @param direction direction user would like to move the car in
	*/
	@Override
	public void moveCar(int direction) {
		if(activeMap.moveCar(selectedCar, direction)) {
			Move newMove = new Move(selectedCar, direction);
			moves.add(newMove);
			movesMade++;
		}

	}
	/**
	* moves game to the next level by resetting settings such as moves, adding records to leaderboard appropriately
	* and generates next level
	*/
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
	
	/**
	* sets the car that has been selected by the user and adds to game state
	*/
	@Override
	public void selectCar(int carId) {
		selectedCar = carId;
	}
	
	/**
	* function to undo a move made by a user by accessing linked list that stores moves
	*/
	@Override
	public void undo() {
		if(moves.size() > 0) {
			Move lastMove = moves.removeLast();
			if(activeMap.moveCar(lastMove.getCarId(), lastMove.getOppDirection())) {
				movesMade--;
			}
		}
	}
	
	/**
	* @return returns true if the car is in the winning state 
	* by checking to position of the car on the x axis (since the red car may only move horizontally)
	*/
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
	
	/**
	* @return returns true if car is in winning state of car in multiplayer
	*/
	@Override
	public boolean isWinMulti() {
		if (activeMap.getCar(Constants.RED).getPosition().getX() == 4) return true;
		
		return false;
	}
	
	/**
	* @return returns true if user has completed all levels in the game else false
	*/
	@Override
	public boolean isOver() {
		if (level == Constants.MAX_LEVEL) return true;
		
		return false;
	}
	
	/**
	* @return returns current map in use in game
	*/
	@Override
	public MapInterface getMap() {
		return activeMap;
	}
	
	/**
	* @return returns currently selected car by use
	*/
	@Override
	public int getSelectedCar() {
		return selectedCar;
	}

	/**
	* resets the level in the game by re creating the original map
	* sets appropriate values i.e moves made to 0, etc.
	*/
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

	/**
	* @return returns the number of moves made
	*/
	@Override
	public int getMovesMade() {
		return movesMade;
	}

	/**
	* generates a new map for the game (random generation of cars spawned)
	*/
	private void generateMap() {
		try {
			initMap = mapQueue.remove();
			level ++;
		} catch (InterruptedException e) {

		}
	}

	/**
	* function to save the score of the user to leaderboard
	*/
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

	/**
	* sets the game panel and initialises new leaderboard
	*/
	public void setPanel(GamePanel panel) {
		this.panel = panel;
		inGameLeaderBoard = new InGameLeaderBoard();
	}

	/**
	* exits game
	*/
	@Override
	public void quit() {
		t.interrupt();
		mapQueue = new BoundedLinkedQueue<MapInterface>(Constants.MAX_LEVEL);
	}

	/**
	* @return returns the current level the user is on
	*/
	@Override
	public int getLevel() {
		return level;
		
	}
	
	/**
	* clones current map of a game
	*/
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

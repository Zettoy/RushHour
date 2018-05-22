package Controller;

import java.util.LinkedList;
import java.util.Queue;

public class Game implements GameInterface {
	private MapInterface activeMap;
	private MapInterface initMap;
	private MapGeneratorInterface mapGenerator;
	private LinkedList<Move> moves;
	private int selectedCar;
	private int movesMade;
	
	private Queue<MapInterface> mapQueue;

	
	public Game () {
		mapQueue = new LinkedList<>();
		moves = new LinkedList<>();
	}
	
	@Override
	public void gameStart() {
		generateMap();
		movesMade = 0;
		activeMap = initMap.clone();
		selectedCar = 0;
		
		new Thread() {
			public void run() {
				while (mapQueue.size() < 3) {
					MapGeneratorInterface m = new MapGenerator();
					m.createMap();
					mapQueue.add(m.getMap());
				}
			}
		}.start();
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
		initMap = mapQueue.poll();
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
		if (activeMap.getCar(Constants.RED).getPosition().getX() == 4) return true;
		
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
		movesMade = 0;
		activeMap = initMap.clone();
		selectedCar = 0;
		
	}

	@Override
	public int getMovesMade() {
		return movesMade;
	}

	private void generateMap() {
		mapGenerator = new MapGenerator();
		mapGenerator.createMap();
		initMap = mapGenerator.getMap();
	}

	
	/* Example of using other map generators
	private void generateMapHard() {
		mapGenerator = new MapGeneratorHard();
		mapGenerator.createMap();
		initMap = mapGenerator.getMap();
	}
	 */
}

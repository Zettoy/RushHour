
import java.util.Random;
import Controller.Constants;

public class MapGenerator implements MapGeneratorInterface, Runnable {	

	private Map map;
	private BoundedQueue<MapInterface> mapQueue;
	private int difficulty;
	private static final int FAILURE_THRESHOLD = 8;
	
	public MapGenerator(BoundedQueue<MapInterface> mapQueue, int difficulty) {
		this.mapQueue = mapQueue;
		this.difficulty = difficulty;
	}
	
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				createMap();
				mapQueue.add(map.clone());
			}
		}
		catch (InterruptedException exception) {
		}
	}
	
	private void createMap() {
		int minMovesAllowed = Constants.EASY;
		int maxMovesAllowed = Constants.IMPOSSIBLE;
		switch(difficulty) {
			case Constants.EASY:
				minMovesAllowed = Constants.EASY;
				maxMovesAllowed = Constants.INTERMEDIATE;
				break;
			case Constants.INTERMEDIATE:
				minMovesAllowed = Constants.INTERMEDIATE;
				maxMovesAllowed = Constants.HARD;
				break;
			case Constants.HARD:
				minMovesAllowed = Constants.HARD;
				maxMovesAllowed = Constants.IMPOSSIBLE;
		}
		int movesToSolve = 0;
		while (movesToSolve < minMovesAllowed || movesToSolve > maxMovesAllowed) {
			map = new Map();
			generatePrelimMap();
			MapState initialState = new MapState(map, 0);
			StateSpaceSearch algorithm = new BestFirstSearch(new UnblockCount());
			movesToSolve = algorithm.findTotalDistanceToGoal(initialState);
		}
	}
	
	private void generatePrelimMap() {
		
		Random random = new Random();
		
		int numCarsAdded = 0;
		int startingX = random.nextInt(Constants.MAPSIZE - Constants.SHORT);
		CarInterface redCar = new Car(Constants.RED, Constants.SHORT, Constants.HORIZONTAL, new Position(startingX, 2));
		map.addCar(redCar);
		numCarsAdded++;
		
		int numFailuresInARow = 0;
		while (numFailuresInARow < FAILURE_THRESHOLD) {
			
			int length = random.nextInt(2) == 0 ? Constants.SHORT : Constants.LONG;
			int paraCoord = random.nextInt(Constants.MAPSIZE - length + 1);
			int perpCoord = random.nextInt(Constants.MAPSIZE);
			char orientation = random.nextInt(2) == 0 ? Constants.HORIZONTAL : Constants.VERTICAL;
			boolean carAdded = false;
			
			if (orientation == Constants.HORIZONTAL) {
				if (perpCoord != 2 && !isRowFull(length, paraCoord, perpCoord)) {
					CarInterface car = new Car(Constants.RED + numCarsAdded, length, orientation, new Position(paraCoord, perpCoord));
					carAdded = map.addCar(car);
				}
			}
			else if (orientation == Constants.VERTICAL) {
				if ((!isRedCarPathBlocked(startingX, perpCoord) || canCarAboveBackOut(perpCoord, paraCoord)) && 
					 !isColumnFull(length, perpCoord, paraCoord)) {
					CarInterface car = new Car(Constants.RED + numCarsAdded, length, orientation, new Position(perpCoord, paraCoord));
					carAdded = map.addCar(car);
				}
			}
			
			if (carAdded) { 
				numCarsAdded++;
				numFailuresInARow = 0;
			} else {
				numFailuresInARow++;
			}
		}
	}
	
	private boolean isRowFull(int length, int x, int y) {
		for (int i = 0; i < Constants.MAPSIZE; i++) {
			if (i < x || i >= x + length) {
				if (map.getCarId(i, y) == 0) return false;
				CarInterface car = map.getCar(map.getCarId(i, y));
				if (car.getDirection() == Constants.VERTICAL) return false;
			}
		}
		return true;
	}
	
	private boolean isColumnFull(int length, int x, int y) {
		for (int j = 0; j < Constants.MAPSIZE; j++) {
			if (j < y || j >= y + length) {
				if (map.getCarId(x, j) == 0) return false;
				CarInterface car = map.getCar(map.getCarId(x, j));
				if (car.getDirection() == Constants.HORIZONTAL) return false;
			}
		}
		return true;
	}
	
	private boolean isRedCarPathBlocked(int startingX, int x) {
		if (x < startingX + Constants.SHORT) return false;
		return true;
	}
	
	private boolean canCarAboveBackOut(int x, int y) {
		for (int j = y; j >= 0; j--) {
			if (map.getCarId(x, j) == 0) continue;
			CarInterface car = map.getCar(map.getCarId(x, j));
			if (car.getDirection() == Constants.VERTICAL 
			    && car.getLength() == Constants.LONG) return false;
		}
		return true;
	}
}

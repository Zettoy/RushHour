package Model;
import java.util.Random;
import Controller.*;

/**
 * Producer of MapInterface objects
 */
public class MapGenerator implements MapGeneratorInterface, Runnable {	

	private Map map;
	private BoundedQueue<MapInterface> mapQueue;
	private int difficulty;
	private static final int FAILURE_THRESHOLD = 8;
	/**
	 * Constructs a MapGenerator object
	 * @param mapQueue the BoundedQueue to which newly created Maps are added
	 * @param difficulty the difficulty level of the Maps to be created
	 */
	public MapGenerator(BoundedQueue<MapInterface> mapQueue, int difficulty) {
		this.mapQueue = mapQueue;
		this.difficulty = difficulty;
	}
	/**
	 * Creates Maps and adds them to the queue
	 */
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
	/**
	 * Generates particular arrangements of cars on the grid and tests them for number of moves required to solve them until one such 
	 * arrangement fits within the designated parameters of difficulty
	 */
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
		StateSpaceSearch algorithm = new BestFirstSearch(new UnblockCount());
		while (movesToSolve < minMovesAllowed || movesToSolve > maxMovesAllowed) {
			map = new Map();
			generatePrelimMap();
			movesToSolve = algorithm.findTotalDistanceToGoal(new MapState(map, 0));
		}
	}
	/**
	 * Adds cars of random length, position and orientation to the map
	 */
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
				if (perpCoord != 2 && !map.isRowFull(length, paraCoord, perpCoord)) {
					CarInterface car = new Car(Constants.RED + numCarsAdded, length, orientation, new Position(paraCoord, perpCoord));
					carAdded = map.addCar(car);
				}
			}
			else if (orientation == Constants.VERTICAL) {
				if ((perpCoord < startingX + Constants.SHORT || map.canCarAboveBackOut(perpCoord, paraCoord)) && 
					 !map.isColumnFull(length, perpCoord, paraCoord)) {
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
}

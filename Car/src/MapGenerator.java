import java.util.Random;

public class MapGenerator implements MapGeneratorInterface{	
	private Map map;
	
	public MapGenerator() {
		map = new Map();
	}
	
	public void createMap() {
		int movesToSolve = 0;
		while (movesToSolve < Constants.EASY || movesToSolve > Constants.INTERMEDIATE) {
			map = new Map();
			generatePrelimMap();
			MapState initialState = new MapState(map, 0);
			StateSpaceSearch algorithm = new WeightedAstarSearch(new UnblockCount());
			movesToSolve = algorithm.findTotalDistanceToGoal(initialState);
			//System.out.println(movesToSolve);
		}
	}
	
	private void generatePrelimMap() {
		Random random = new Random();
		int startingX = random.nextInt(Constants.MAPSIZE - Constants.SHORT);
		CarInterface redCar = new Car(Constants.RED, Constants.SHORT, Constants.HORIZONTAL, new Position(startingX, 2));
		map.addCar(redCar);
		int numCarsAdded = 1;
		int numFailuresInARow = 0;
		while (numFailuresInARow < getFailureThreshold()) {
			int length = random.nextInt(2) == 0 ? Constants.SHORT : Constants.LONG;
			int paraCoord = random.nextInt(Constants.MAPSIZE - length + 1);
			int perpCoord = random.nextInt(Constants.MAPSIZE);
			char orientation = random.nextInt(2) == 0 ? Constants.HORIZONTAL : Constants.VERTICAL;
			boolean carAdded = false;
			if (orientation == Constants.HORIZONTAL) {
				if (perpCoord != 2) {
					CarInterface car = new Car(Constants.RED + numCarsAdded, length, orientation, new Position(paraCoord, perpCoord));
					carAdded = map.addCar(car);
				}
			}
			else if (orientation == Constants.VERTICAL) {
				CarInterface car = new Car(Constants.RED + numCarsAdded, length, orientation, new Position(perpCoord, paraCoord));
				carAdded = map.addCar(car);
			}
			if (carAdded) { 
				numCarsAdded++;
				numFailuresInARow = 0;
			} else 
				numFailuresInARow++;
		}
	}

	private int getFailureThreshold () {
		return 6;
	}
	
	@Override
	public MapInterface getMap() {
		return map;
	}
}

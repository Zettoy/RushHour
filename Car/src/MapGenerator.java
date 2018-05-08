

//TODO: algorithm to randomly generate maps
public class MapGenerator implements MapGeneratorInterface{	
	private Map map;
	
	public MapGenerator() {
		map = new Map();
	}
	
	/** 
	 * 	0 0 0 0 2 0
	 *	0 0 0 0 2 3
	 *	1 1 0 0 2 3
	 *	0 0 0 0 0 0
	 *	0 0 0 0 0 0
	 *	0 0 0 0 0 0
	 */
	public void createMap() {		
		addCars();
	}
	
	private void addCars() {
		addCarRed();
		addCarOthers();
	}
	
	private void addCarRed() {
		CarInterface redCar = new Car(Constants.RED, Constants.SHORT, Constants.HORIZONTAL, new Position(0, 2));
		map.addCar(redCar);
	}
	
	private void addCarOthers() {
		CarInterface longCar = new Car(Constants.RED + 1, Constants.LONG, Constants.VERTICAL, new Position(4, 0));
		map.addCar(longCar);
		
		CarInterface shortCar = new Car(Constants.RED + 2, Constants.SHORT, Constants.VERTICAL, new Position(5, 1));
		map.addCar(shortCar);
	}
		
	@Override
	public MapInterface getMap() {
		return map;
	}

	@Override
	public MapInterface copyMap(MapInterface original) {
		MapInterface copy = new Map();
		copy.setMatrix(original.getMatrix());
		copy.setCarList(original.getCarList());		
		return copy;
	}

}


public class MapGenerator implements MapGeneratorInterface{
	private static final int RED = 1;
	
	private static final int SHORT = 2;
	private static final int LONG  = 3;
	private static final Character HORIZONTAL = 'H';
	private static final Character VERTICAL   = 'V';
	
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
		map.print();
	}
	
	private void addCars() {
		addCarRed();
		addCarOthers();
	}
	
	private void addCarRed() {
		CarInterface redCar = new Car(RED, SHORT, HORIZONTAL, new Position(0, 2));
		map.addCar(redCar);
	}
	
	private void addCarOthers() {
		CarInterface longCar = new Car(RED + 1, LONG, VERTICAL, new Position(4, 0));
		map.addCar(longCar);
		
		CarInterface shortCar = new Car(RED + 2, SHORT, VERTICAL, new Position(5, 1));
		map.addCar(shortCar);
	}

	@Override
	public MapInterface getMap() {
		return map;
	}

}

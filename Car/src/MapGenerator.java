import java.util.ArrayList;

public class MapGenerator implements MapInterface {
	private static final int RED = 1;
	private static final int OTHER = 9;
	private static final int MAPSIZE = 6;
	private static final int SHORT = 2;
	private static final int LONG  = 3;
	private static final Character HORIZONTAL = 'H';
	private static final Character VERTICAL   = 'V';
	
	private CarInterface redCar;
	private ArrayList<CarInterface> cars;
	private int[][] map;
	
	public MapGenerator() {
		cars = new ArrayList<CarInterface>();
	}
	
	@Override
	/** 
	 * 	9 0 0 0 0 0
	 *	9 9 0 0 0 0
	 *	9 9 0 0 1 1
	 *	0 0 0 0 0 0
	 *	0 0 0 0 0 0
	 *	0 0 0 0 0 0
	 */
	public void creatMap() {
		map = new int[MAPSIZE][MAPSIZE];
		for (int row = 0; row < MAPSIZE; row ++)
			for (int col = 0; col < MAPSIZE; col ++)
				map[row][col] = 0;
		
		addCars();
	}
	
	private void addCars() {
		addCarRed();
		addCarOthers();
	}
	
	private void addCarRed() {
		redCar = new CarRed(new Position(4, 2));
		putOnMap((CarRed)redCar);
	}
	
	private void addCarOthers() {
		CarInterface longCar = new CarOthers(LONG, HORIZONTAL, new Position(0, 0));
		cars.add(longCar);
		putOnMap((CarOthers)longCar);
		
		CarInterface shortCar = new CarOthers(SHORT, HORIZONTAL, new Position(1, 1));
		cars.add(shortCar);
		putOnMap((CarOthers)shortCar);
	}
	
	private void putOnMap(CarRed car) {
		int start = car.getPosition().getX();
		map[2][start] = RED;
		map[2][start + 1] = RED;
	}
	
	private void putOnMap(CarOthers car) {
		if (car.getDirection() == HORIZONTAL) {
			putOnMapH(car.getPosition(), car.getLength());
		} else if (car.getDirection() == VERTICAL){
			putOnMapV(car.getPosition(), car.getLength());
		}
	}
	
	private void putOnMapV(Position p, int length) {
		int start = p.getY();
		for (int i = 0; i < length; i ++) {
			map[p.getX()][start] = OTHER;
			start ++;
		}
	}
	
	private void putOnMapH(Position p, int length) {
		int start = p.getX();
		for (int i = 0; i < length; i ++) {
			map[start][p.getY()] = OTHER;
			start ++;
		}
	}
	
	public void print() {
		for (int i = 0; i < 6; i ++) {
			for (int j = 0; j < 6; j ++) {
				System.out.print(map[i][j]);
				if (j != 5) System.out.print(" ");
			}
			if (i != 5) System.out.println();
		}
	}

}

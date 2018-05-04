import java.util.ArrayList;

public class Map implements MapInterface{
	private static final int MAPSIZE = 6;
	private static final Character HORIZONTAL = 'H';
	private static final Character VERTICAL   = 'V';
	
	private ArrayList<CarInterface> cars;
	private int[][] map;

	public Map() {
		map = new int[MAPSIZE][MAPSIZE];
		for (int row = 0; row < MAPSIZE; row ++)
			for (int col = 0; col < MAPSIZE; col ++)
				map[row][col] = 0;
		
		cars = new ArrayList<>();
	}

	@Override
	public void addCar(CarInterface car) {
		putOnMap(car);		
		cars.add(car);
	}

	@Override
	public CarInterface getCar(int carId) {
		for (CarInterface c : cars) {
			if (c.getId() == carId) return c;
		}
		
		return null;
	}

	@Override
	public void moveCar(int carId, int direction) {
		CarInterface car = getCar(carId);
		removeFromMap(car);
		car.move(direction);
		putOnMap(car);
	}
	
	private void putOnMap(CarInterface car) {
		if (car.getDirection() == HORIZONTAL) {
			putOnMapH(car);
		} else if (car.getDirection() == VERTICAL){
			putOnMapV(car);
		}
	}
	
	private void putOnMapV(CarInterface car) {
		Position position = car.getPosition();
		int length = car.getLength();
		int start = position.getY();
		for (int i = 0; i < length; i ++) {
			map[start][position.getX()] = car.getId();
			start ++;
		}
	}
	
	private void putOnMapH(CarInterface car) {
		Position position = car.getPosition();
		int length = car.getLength();
		int start = position.getX();
		for (int i = 0; i < length; i ++) {
			map[position.getY()][start] = car.getId();
			start ++;
		}
	}
	
	private void removeFromMap(CarInterface car) {
		if (car.getDirection() == HORIZONTAL) {
			removeFromMapH(car);
		} else if (car.getDirection() == VERTICAL){
			removeFromMapV(car);
		}
	}
	
	private void removeFromMapV(CarInterface car) {
		Position position = car.getPosition();
		int length = car.getLength();
		int start = position.getY();
		for (int i = 0; i < length; i ++) {
			map[start][position.getX()] = 0;
			start ++;
		}
	}
	
	private void removeFromMapH(CarInterface car) {
		Position position = car.getPosition();
		int length = car.getLength();
		int start = position.getX();
		for (int i = 0; i < length; i ++) {
			map[position.getY()][start] = 0;
			start ++;
		}
	}
	
	
	
	@Override
	public void print() {
		for (int i = 0; i < 6; i ++) {
			for (int j = 0; j < 6; j ++) {
				System.out.print(map[i][j]);
				if (j != 5) System.out.print(" ");
			}
			System.out.println();
		}
	}
	
}

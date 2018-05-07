import java.util.ArrayList;

public class Map implements MapInterface{	
	private ArrayList<CarInterface> cars;
	private int[][] map;

	public Map() {
		map = new int[Constants.MAPSIZE][Constants.MAPSIZE];
		for (int row = 0; row < Constants.MAPSIZE; row ++)
			for (int col = 0; col < Constants.MAPSIZE; col ++)
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
		
		if (!isMoveable(car, direction)) return;
		
		removeFromMap(car);
		car.move(direction);
		putOnMap(car);
		
	}
	
	@Override
	public int getNumCars() {
		return cars.size();
	}
	
	private void putOnMap(CarInterface car) {
		if (car.getDirection() == Constants.HORIZONTAL) {
			putOnMapH(car);
		} else if (car.getDirection() == Constants.VERTICAL){
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
		if (car.getDirection() == Constants.HORIZONTAL) {
			removeFromMapH(car);
		} else if (car.getDirection() == Constants.VERTICAL){
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
	
	private boolean isMoveable(CarInterface car, int direction) {
		if (direction == Constants.UP || direction == Constants.DOWN) {
			if (car.getDirection() == Constants.HORIZONTAL) return false;
		} else if (direction == Constants.LEFT || direction == Constants.RIGHT) {
			if (car.getDirection() == Constants.VERTICAL) return false;
		}
		
		if (direction == Constants.UP) {
			int x = car.getPosition().getX();
			int y = car.getPosition().getY() - 1;
			if (y < 0) return false;
			if (map[y][x] != 0) return false;
		} else if (direction == Constants.DOWN) {
			int x = car.getPosition().getX();
			int y = car.getPosition().getY() + car.getLength();
			if (y < Constants.MAPSIZE - 1) return false;
			if (map[y][x] != 0) return false;
		} else if (direction == Constants.LEFT) {
			int x = car.getPosition().getX() - 1;
			int y = car.getPosition().getY();
			if (x < 0) return false;
			if (map[y][x] != 0) return false;
		} else if (direction == Constants.RIGHT) {
			int x = car.getPosition().getX() + car.getLength();
			int y = car.getPosition().getY();
			if (x > Constants.MAPSIZE - 1) return false;
			if (map[y][x] != 0) return false;
		}
		return true;
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

package Controller;
import java.util.ArrayList;
import java.util.HashSet;

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
	public boolean addCar(CarInterface car) {
		boolean carPut = putOnMap(car);	
		if (!carPut) return false;
		cars.add(car);
		return true;
	}

	@Override
	public CarInterface getCar(int carId) {
		for (CarInterface c : cars) {
			if (c.getId() == carId) return c;
		}
		
		return null;
	}

	@Override
	public boolean moveCar(int carId, int direction) {
		CarInterface car = getCar(carId);
		
		if (!isMoveable(car, direction)) return false;
		
		removeFromMap(car);
		car.move(direction);
		putOnMap(car);
		return true;
	}
	
	@Override
	public int getNumCars() {
		return cars.size();
	}
	
	private boolean putOnMap(CarInterface car) {
		boolean carPut = false;
		if (car.getDirection() == Constants.HORIZONTAL) {
			carPut = putOnMapH(car);
		} else if (car.getDirection() == Constants.VERTICAL) {
			carPut = putOnMapV(car);
		}
		return carPut;
	}
	
	private boolean putOnMapV(CarInterface car) {
		Position position = car.getPosition();
		int length = car.getLength();
		int start = position.getY();
		for (int i = 0; i < length; i++) {
			if (map[start][position.getX()] != 0) return false;
			start++;
		}
		start = position.getY();
		for (int i = 0; i < length; i ++) {
			map[start][position.getX()] = car.getId();
			start++;
		}
		return true;
	}
	
	private boolean putOnMapH(CarInterface car) {
		Position position = car.getPosition();
		int length = car.getLength();
		int start = position.getX();
		for (int i = 0; i < length; i++) {
			if (map[position.getY()][start] != 0) return false;
			start++;
		}
		start = position.getX();
		for (int i = 0; i < length; i ++) {
			map[position.getY()][start] = car.getId();
			start ++;
		}
		return true;
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
			if (y > Constants.MAPSIZE - 1) return false;
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
	
	public HashSet<Map> getAdjacentMaps() {
		HashSet<Map> adjMaps = new HashSet<Map>();
		for (CarInterface car: cars) {
			if (isMoveable(car, Constants.UP)) {
				Map adjMap = (Map) this.clone();
				adjMap.moveCar(car.getId(), Constants.UP);
				adjMaps.add(adjMap);
			}
			if (isMoveable(car, Constants.DOWN)) {
				Map adjMap = (Map) this.clone();
				adjMap.moveCar(car.getId(), Constants.DOWN);
				adjMaps.add(adjMap);
			}
			if (isMoveable(car, Constants.LEFT)) {
				Map adjMap = (Map) this.clone();
				adjMap.moveCar(car.getId(), Constants.LEFT);
				adjMaps.add(adjMap);
			}
			if (isMoveable(car, Constants.RIGHT)) {
				Map adjMap = (Map) this.clone();
				adjMap.moveCar(car.getId(), Constants.RIGHT);
				adjMaps.add(adjMap);
			}
		}
		return adjMaps;
	}
	
	@Override
	public int getCarId(int x, int y) {
		return map[y][x];
	}
	
	public boolean equals(Object unknown) {
		if (unknown == this) return true;
		if (unknown == null) return false;
		if (unknown.getClass() != this.getClass()) return false;
		Map other = (Map) unknown;
		for (int row = 0; row < Constants.MAPSIZE; row++)
			for (int col = 0; col < Constants.MAPSIZE; col++)
				if (other.map[row][col] != map[row][col]) return false;
		return true;
	}
	
	@Override
	public MapInterface clone() {
		Map clone = new Map();
		clone.map = new int[Constants.MAPSIZE][Constants.MAPSIZE];
		for (int row = 0; row < Constants.MAPSIZE; row ++)
			for (int col = 0; col < Constants.MAPSIZE; col ++)
				clone.map[row][col] = map[row][col];
		
		clone.cars = new ArrayList<>();
		for (CarInterface c : cars)
			clone.cars.add(c.clone());
		
		return clone;
	}
}

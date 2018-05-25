package Controller;
import java.util.ArrayList;
import java.util.HashSet;

/**
* function to generate map base and add cars/objects to the map as well 
* as some basic user interaction to check for valid move inputs
*/
public class Map implements MapInterface{	
	private ArrayList<CarInterface> cars;
	private int[][] map;

	/**
	* constructor
	* intialises a blank board/map in tabular form
	* intialises array to store list of cars that are available on the map
	*/
	public Map() {
		map = new int[Constants.MAPSIZE][Constants.MAPSIZE];
		for (int row = 0; row < Constants.MAPSIZE; row ++)
			for (int col = 0; col < Constants.MAPSIZE; col ++)
				map[row][col] = 0;
		
		cars = new ArrayList<>();
	}

	/**
	* code to add a car object to the map
	* @return returns true if car was successfully added, else false
	*/
	@Override
	public boolean addCar(CarInterface car) {
		boolean carPut = putOnMap(car);	
		if (!carPut) return false;
		cars.add(car);
		return true;
	}

	/**
	* function to reuturn a car on the map given an id
	* @param carId uniqueue identified to identify a car
	* @return returns the car if car exists on map given an id, else null
	*/
	@Override
	public CarInterface getCar(int carId) {
		for (CarInterface c : cars) {
			if (c.getId() == carId) return c;
		}
		
		return null;
	}

	/**
	* function to move the car if valid input is received 
	* (i.e car is wanted to be moved in place where there are no collisions, is still on map, and is in direction car is facing)
	* @param carId unique identified for car
	* @param direction desired direction to move car in
	* @return returns true if move was successful, else false
	*/
	@Override
	public boolean moveCar(int carId, int direction) {
		CarInterface car = getCar(carId);
		
		if (!isMoveable(car, direction)) return false;
		
		removeFromMap(car);
		car.move(direction);
		putOnMap(car);
		return true;
	}
	
	/**
	* @return returns the number of cars on the map
	*/
	@Override
	public int getNumCars() {
		return cars.size();
	}
	
	/**
	* function to actually put car on map
	* @param car car that you would like to place on map
	* @return returns true if car is placed on map successfully, else false
	*/
	private boolean putOnMap(CarInterface car) {
		boolean carPut = false;
		if (car.getDirection() == Constants.HORIZONTAL) {
			carPut = putOnMapH(car);
		} else if (car.getDirection() == Constants.VERTICAL) {
			carPut = putOnMapV(car);
		}
		return carPut;
	}
	
	/**
	* helper function to putOnMap function to place car if it is facing vertically
	* @param car car object that you would like to place on map
	* @return returns true if car is successfully placed on map, else false
	*/
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

	/**
	* helper functino to putOnMap function to place car if it is facing horizontally
	* @param car car object that you would like to place on map
	* @return returns true if car is successfully placed on map, else false
	*/
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

	/**
	* function to remove car from map
	* @param car car that you would like to remove from map
	* @return returns true if car is removed from map successfully, else false
	*/
	private void removeFromMap(CarInterface car) {
		if (car.getDirection() == Constants.HORIZONTAL) {
			removeFromMapH(car);
		} else if (car.getDirection() == Constants.VERTICAL){
			removeFromMapV(car);
		}
	}
	
	/**
	* helper function to removeFromMap function to place car if it is facing vertically
	* @param car car object that you would like to remove from map
	* @return returns true if car is successfully removed from map, else false
	*/
	private void removeFromMapV(CarInterface car) {
		Position position = car.getPosition();
		int length = car.getLength();
		int start = position.getY();
		for (int i = 0; i < length; i ++) {
			map[start][position.getX()] = 0;
			start ++;
		}
	}
	
	/**
	* helper function to removeFromMap function to place car if it is facing horizontally
	* @param car car object that you would like to remove from map
	* @return returns true if car is successfully removed from map, else false
	*/
	private void removeFromMapH(CarInterface car) {
		Position position = car.getPosition();
		int length = car.getLength();
		int start = position.getX();
		for (int i = 0; i < length; i ++) {
			map[position.getY()][start] = 0;
			start ++;
		}
	}
	
	/**
	* function to check if car is moveable in desired direction
	* checks if move is valid 
	* (intented move leaves car to still be on the map, not in collision with any other cars, in correct direction that car is facing)
	* @param car car to be moved
	* @param direction desired direction for car to be moved in
	* @return returns true if car is moveable in desired direction, else false
	*/
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
	
	/**
	* helper function to check if given row is full
	* @return returns true if row is full, else false
	*/
	public boolean isRowFull(int length, int x, int y) {
		for (int i = 0; i < Constants.MAPSIZE; i++) {
			if (i < x || i >= x + length) {
				if (map[y][i] == 0) return false;
				CarInterface car = getCar(map[y][i]);
				if (car.getDirection() == Constants.VERTICAL) return false;
			}
		}
		return true;
	}
	
	/**
	* helper function to check if given column is full
	* @return returns true if column is full, else false
	*/
	public boolean isColumnFull(int length, int x, int y) {
		for (int j = 0; j < Constants.MAPSIZE; j++) {
			if (j < y || j >= y + length) {
				if (map[j][x] == 0) return false;
				CarInterface car = getCar(map[j][x]);
				if (car.getDirection() == Constants.HORIZONTAL) return false;
			}
		}
		return true;
	}
	
	/**
	* function to check if car vertical and long exists
	* @return returns false if there is, else true
	*/
	public boolean canCarAboveBackOut(int x, int y) {
		for (int j = y; j >= 0; j--) {
			if (map[j][x] == 0) continue;
			CarInterface car = getCar(map[j][x]);
			if (car.getDirection() == Constants.VERTICAL 
			    && car.getLength() == Constants.LONG) return false;
		}
		return true;
	}
	
	/**
	*  @return returns map of new states after car has hyptohetically been moved
	*/
	public HashSet<MapInterface> getAdjacentMaps() {
		HashSet<MapInterface> adjMaps = new HashSet<MapInterface>();
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
	
	/**
	* @return returns id of car given a location or 0 if no car exists
	*/
	public int getCarId(int x, int y) {
		return map[y][x];
	}
	
	/**
	* simple equals method to check co inciding maps
	*/
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
	
	/**
	* simple clone method to clone map
	*/
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

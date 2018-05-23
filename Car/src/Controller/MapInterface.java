package Controller;

import java.util.HashSet;

public interface MapInterface {
	boolean addCar(CarInterface car);
	int getCarId(int x, int y);
	CarInterface getCar(int carId);
	boolean moveCar(int carId, int direction);
	int getNumCars();
	HashSet<MapInterface> getAdjacentMaps();
	boolean equals(Object other);
	MapInterface clone();
}

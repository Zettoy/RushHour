
public interface MapInterface {
	void addCar(CarInterface car);
	CarInterface getCar(int carId);
	boolean moveCar(int carId, int direction);
	int getNumCars();
	
	MapInterface clone();
}

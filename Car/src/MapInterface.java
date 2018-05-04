
public interface MapInterface {
	void addCar(CarInterface car);
	CarInterface getCar(int carId);
	void moveCar(int carId, int direction);
	
	void print();
}

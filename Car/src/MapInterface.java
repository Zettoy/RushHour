import java.util.ArrayList;

public interface MapInterface {
	void addCar(CarInterface car);
	CarInterface getCar(int carId);
	void moveCar(int carId, int direction);
	int getNumCars();
	int[][] getMatrix();
	ArrayList<CarInterface> getCarList();
	void setMatrix(int[][] matrix);
	void setCarList(ArrayList<CarInterface> carList);
	void print();
}

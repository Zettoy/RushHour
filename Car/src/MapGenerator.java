import java.util.ArrayList;

public class MapGenerator {
	private CarInterface redCar;
	private ArrayList<CarInterface> cars;
	private int[][] map;
	
	public MapGenerator() {
		cars = new ArrayList<CarInterface>();
		map = new int[][] {{0, 0, 0, 0, 0, 0},
			   			   {0, 0, 0, 0, 0, 0},
			   			   {0, 0, 0, 0, 0, 0},
			   			   {0, 0, 0, 0, 0, 0},
			   			   {0, 0, 0, 0, 0, 0},
			   			   {0, 0, 0, 0, 0, 0}};
		
	}

	public void addCarRed() {
		CarInterface newCar = new CarRed();
		redCar = newCar;
		map[2][4] = 1;
		map[2][5] = 1;
	}
	
	public void addCarOthers(Character colour) {
		CarInterface newCar = new CarOthers(colour);
		cars.add(newCar);
		map[0][0] = 2;
		map[1][0] = 2;
		map[2][0] = 2;
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


public class Game implements GameInterface {	
	private MapInterface activeMap;
	private MapInterface initMap;
	private MapGeneratorInterface mapGenerator;
	private int selectedCar;
	
	public Game () {

	}
	
	@Override
	public void gameStart() {
		generateMap();
		activeMap = initMap.clone();
		selectedCar = Constants.RED;
	}
	
	@Override
	public void moveCar(int direction) {
		activeMap.moveCar(selectedCar, direction);
	}
	
	@Override
	public void selectCar(int carId) {
		selectedCar = carId;
	}
	
	@Override
	public boolean isWin() {
		if (activeMap.getCar(Constants.RED).getPosition().getX() == 4) return true;
		
		return false;
	}
	
	@Override
	public MapInterface getMap() {
		return activeMap;
	}
	
	@Override
	public int getSelectedCar() {
		return selectedCar;
	}

	@Override
	public void gameRestart() {
		activeMap = initMap.clone();
		selectedCar = Constants.RED;
		
	}
	
	private void generateMap() {
		mapGenerator = new MapGenerator();
		mapGenerator.createMap();
		initMap = mapGenerator.getMap();
	}
	
	/* Example of using other map generators
	private void generateMapHard() {
		mapGenerator = new MapGeneratorHard();
		mapGenerator.createMap();
		initMap = mapGenerator.getMap();
	}
	 */
}

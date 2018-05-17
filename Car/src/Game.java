public class Game implements GameInterface {
	private MapInterface activeMap;
	private MapInterface initMap;
	private MapGeneratorInterface mapGenerator;
	private int selectedCar;
	private int movesMade;
	
	public Game () {

	}
	
	@Override
	public void gameStart() {
		generateMap();
		movesMade = 0;
		activeMap = initMap.clone();
		selectedCar = 0;
	}
	
	@Override
	public void moveCar(int direction) {
		if(activeMap.moveCar(selectedCar, direction)) {
			movesMade++;
		}

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
		movesMade = 0;
		activeMap = initMap.clone();
		selectedCar = 0;
		
	}

	@Override
	public int getMovesMade() {
		return movesMade;
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

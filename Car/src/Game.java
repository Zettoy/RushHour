
public class Game implements GameInterface {	
	private MapInterface map;
	private MapInterface spareMap;
	private MapGeneratorInterface mapGenerator;
	private int selectedCar;
	
	public Game () {
		this.mapGenerator = new MapGenerator();
		this.selectedCar = Constants.RED;
	}
	
	@Override
	public void gameStart() {
		generateMap();
	}
	
	@Override
	public void moveCar(int direction) {
		map.moveCar(selectedCar, direction);
	}
	
	@Override
	public void selectCar(int carId) {
		selectedCar = carId;
	}
	
	@Override
	public boolean isWin() {
		if (map.getCar(Constants.RED).getPosition().getX() == 4) return true;
		
		return false;
	}
	
	@Override
	public MapInterface getMap() {
		return map;
	}
	
	@Override
	public int getSelectedCar() {
		return selectedCar;
	}

	@Override
	public void gameRestart() {
		map = spareMap;
		
	}
	
	private void generateMap() {
		mapGenerator.createMap();
		map = mapGenerator.getMap();
		spareMap = mapGenerator.copyMap(map);
	
	}
}


public class Game implements GameInterface {	
	private MapInterface map;
	private MapGeneratorInterface mapGenerator;
	
	public Game () {
		this.mapGenerator = new MapGenerator();
	}
	
	public void gameStart() {
		generateMap();
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
	
	private void generateMap() {
		mapGenerator.createMap();
		map = mapGenerator.getMap();
	}
}


public class GameEngine {
	private MapInterface map;
	
	public GameEngine() {
		
	}
	
	public void generateMap() {
		map = new MapGenerator();
		map.creatMap();
	}
}

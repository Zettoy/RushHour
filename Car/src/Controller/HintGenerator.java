package Controller;

import java.util.Set;

public class HintGenerator {

	public MapInterface getHint(MapInterface map) {
		
		Set<MapInterface> adjMaps = map.getAdjacentMaps();
		MapInterface bestMap = map;
		UnblockCount heuristic = new UnblockCount();
		int leastMovesToUnblock = -1;
		for (MapInterface adjMap: adjMaps) {
			
			CarInterface redCar = adjMap.getCar(Constants.RED);
			int startingX = redCar.getPosition().getX() + redCar.getLength();
			int movesToUnblock = Constants.MAPSIZE - startingX;
			while (startingX < Constants.MAPSIZE) {
				movesToUnblock += heuristic.getNumMovesToUnblock(map, startingX, 2, 2);
				startingX++;
			}
			
			if (leastMovesToUnblock == -1 || movesToUnblock < leastMovesToUnblock) {
				leastMovesToUnblock = movesToUnblock;
				bestMap = adjMap;
			}
		}
		return bestMap;
	}
}

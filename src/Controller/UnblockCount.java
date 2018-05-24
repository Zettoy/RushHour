package Controller;
public class UnblockCount implements SearchHeuristic {
	
	public UnblockCount() {
	}
	
	public int calculate(State state) {
		
		MapInterface map = ((MapState) state).getMap();
		CarInterface redCar = map.getCar(Constants.RED);
		int startingX = redCar.getPosition().getX() + redCar.getLength();
		int movesToUnblock = Constants.MAPSIZE - startingX;
		while (startingX < Constants.MAPSIZE) {
			movesToUnblock += getNumMovesToUnblock(map, startingX, 2, 2);
			startingX++;
		}
		
		return movesToUnblock;
	}
	
	public int getNumMovesToUnblock(MapInterface map, int x, int y, int iterations) {
		
		if (iterations == 0 || map.getCarId(x, y) == 0) return 0;
		
		CarInterface car = map.getCar(map.getCarId(x, y));
		int movesToUnblock = 0;
		
		if (car.getDirection() == Constants.VERTICAL) {
			
			int movesToUnblockAbove = 0;
			int movesToUnblockBelow = 0;

			if (y - car.getLength() < 0) movesToUnblockAbove = -1;
			else {
				movesToUnblockAbove += car.getPosition().getY() - y + car.getLength();
				for (int j = car.getPosition().getY() - 1; j >= y - car.getLength(); j--)
					movesToUnblockAbove += getNumMovesToUnblock(map, x, j, iterations - 1);
			}
			
			if (y + car.getLength() >= Constants.MAPSIZE) movesToUnblockBelow = -1;
			else {
				movesToUnblockBelow += y - car.getPosition().getY() + 1;
				for (int j = car.getPosition().getY() + car.getLength(); j <= y + car.getLength(); j++)
					movesToUnblockBelow += getNumMovesToUnblock(map, x, j, iterations - 1);
			}
			
			if (movesToUnblockAbove == -1)
				movesToUnblock = (movesToUnblockBelow == -1) ? 0 : movesToUnblockBelow;
			else if (movesToUnblockBelow == -1)
				movesToUnblock = movesToUnblockAbove;
			else
				movesToUnblock = (movesToUnblockAbove < movesToUnblockBelow) ? movesToUnblockAbove : movesToUnblockBelow;
			
		} else if (car.getDirection() == Constants.HORIZONTAL) {
			
			int movesToUnblockLeft = 0;
			int movesToUnblockRight = 0;
			
			if (x - car.getLength() < 0) movesToUnblockLeft = -1;
			else {	
				movesToUnblockLeft += car.getPosition().getX() - x + car.getLength();
				for (int i = car.getPosition().getX() - 1; i >= x - car.getLength(); i--)
					movesToUnblockLeft += getNumMovesToUnblock(map, i, y, iterations - 1);
			}
			
			if (x + car.getLength() >= Constants.MAPSIZE) movesToUnblockRight = -1;
			else {
				movesToUnblockRight += x - car.getPosition().getX() + 1;
				for (int i = car.getPosition().getX() + car.getLength(); i <= x + car.getLength(); i++)
					movesToUnblockRight += getNumMovesToUnblock(map, i, y, iterations - 1);
			}
			
			if (movesToUnblockLeft == -1)
				movesToUnblock = (movesToUnblockRight == -1) ? 0 : movesToUnblockRight;
			else if (movesToUnblockRight == -1)
				movesToUnblock = movesToUnblockLeft;
			else
				movesToUnblock = (movesToUnblockLeft < movesToUnblockRight) ? movesToUnblockLeft : movesToUnblockRight;
		}
		
		return movesToUnblock;
	}
}

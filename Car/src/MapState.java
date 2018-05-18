import java.util.HashSet;

public class MapState implements State {
	private final Map map;
	private final int movesMade;
	private int movesRemaining;
	
	public MapState(Map map, int movesMade) {
		this.map = map;
		this.movesMade = movesMade;
		this.movesRemaining = 0;
	}
	
	@Override
	public boolean isGoal() {
		return map.getCar(Constants.RED).getPosition().getX() == Constants.MAPSIZE - Constants.SHORT;
	}
	
	@Override
	public int getDistanceTravelled() {
		return movesMade;
	}
	
	@Override
	public void setDistanceToGoal(int movesRemaining) {
		this.movesRemaining = movesRemaining;
	}
	
	@Override
	public int getDistanceToGoal() {
		return movesRemaining;
	}
	
	public Map getMap() {
		return map;
	}
	
	@Override
	public HashSet<State> getConnectedStates() {
		HashSet<State> connectedStates = new HashSet<State>();
		HashSet<Map> adjMaps = map.getAdjacentMaps();
		for (Map adjMap: adjMaps) connectedStates.add(new MapState(adjMap, movesMade + 1));
		return connectedStates;
	}

	public boolean equals(Object other) {
		if (other == this) return true;
		if (other == null) return false;
		if (other.getClass() != this.getClass()) return false;
		return map.equals(((MapState) other).map);
	}
}

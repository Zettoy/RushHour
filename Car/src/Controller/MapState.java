package Controller;
import java.util.HashSet;

/**
* code to get details about the current state of the map and associated cars
*
*/
public class MapState implements State {
	private final MapInterface map;
	private final int movesMade;
	private int movesRemaining;
	
	/**
	* constructor
	* @param map board/map of game
	* @param movesMade number of moves made in a game
	*/
	public MapState(MapInterface map, int movesMade) {
		this.map = map;
		this.movesMade = movesMade;
		this.movesRemaining = 0;
	}
	
	/**
	* checks if the game is in the "winning state"
	* @return returns true if red car is in winning location, else false
	*/
	@Override
	public boolean isGoal() {
		return map.getCar(Constants.RED).getPosition().getX() == Constants.MAPSIZE - Constants.SHORT;
	}
	
	/**
	* @return checks and returns number of moves made so far in a game
	*/
	@Override
	public int getDistanceTravelled() {
		return movesMade;
	}
	
	/**
	* keeps track of number of moves needed to reach the goal state (red car in wining position)
	* @param movesRemaining number of moves left til game won
	*/
	@Override
	public void setDistanceToGoal(int movesRemaining) {
		this.movesRemaining = movesRemaining;
	}
	
	/**
	* @return returns number of moves left to reach winning state
	*/
	@Override
	public int getDistanceToGoal() {
		return movesRemaining;
	}
	
	/**
	* @return returns the map
	*/
	public MapInterface getMap() {
		return map;
	}
	
	/**
	* @return connectedStates reutrns a hash set of states that can be made
	*/
	@Override
	public HashSet<State> getConnectedStates() {
		HashSet<State> connectedStates = new HashSet<State>();
		HashSet<MapInterface> adjMaps = map.getAdjacentMaps();
		for (MapInterface adjMap: adjMaps) connectedStates.add(new MapState(adjMap, movesMade + 1));
		return connectedStates;
	}

	/**
	* simple function to check if two mapstates are equal
	* @return returns true if map states are equal, else false
	*/
	public boolean equals(Object other) {
		if (other == this) return true;
		if (other == null) return false;
		if (other.getClass() != this.getClass()) return false;
		return map.equals(((MapState) other).map);
	}
}

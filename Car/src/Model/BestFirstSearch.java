package Model;

import Controller.Constants;
import Controller.State;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
/**
 * Best-first search algorithm to conduct state space search
 */
public class BestFirstSearch implements StateSpaceSearch {
	private SearchHeuristic heuristic;
	/**
	 * Constructs a BestFirstSearch object
	 * @param heuristic concrete implementation of the SearchHeuristic interface
	 */
	public BestFirstSearch(SearchHeuristic heuristic) {
		this.heuristic = heuristic;
	}
	/**
	 * Determines an estimate of the number of states that need to be traversed in order to reach the goal state
	 * @param initialState the State from which the search begins
	 * @return the cost of reaching the goal state from the initial state if all edges have identical unit cost
	 */
	public int findTotalDistanceToGoal(State initialState) {
		PriorityQueue<State> openSet = new PriorityQueue<State>(new Comparator<State>() {
			public int compare(State a, State b) {
				double aF = 0.15 * a.getDistanceTravelled() + a.getDistanceToGoal();
				double bF = 0.15 * b.getDistanceTravelled() + b.getDistanceToGoal();
				return (int) (aF - bF);
			}
		});
		Set<State> closedSet = new HashSet<State>();
		initialState.setDistanceToGoal(heuristic.calculate(initialState));
		openSet.add(initialState);
		int numStatesExplored = 0;
		while (!openSet.isEmpty()) {
			State currentState = openSet.remove();
			if (currentState.isGoal()) return currentState.getDistanceTravelled();
			closedSet.add(currentState);
			numStatesExplored++;
			if (numStatesExplored > Constants.MAX_CONFIGURATIONS) return 0;
			Collection<State> connectedStates = currentState.getConnectedStates();
			for (State state: connectedStates) {
				if (closedSet.contains(state)) continue;
				state.setDistanceToGoal(heuristic.calculate(state));
				openSet.add(state);
			}
		}
		return 0;
	}
}

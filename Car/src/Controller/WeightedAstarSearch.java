package Controller;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class WeightedAstarSearch implements StateSpaceSearch {
	private SearchHeuristic heuristic;
	
	public WeightedAstarSearch(SearchHeuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	public int findTotalDistanceToGoal(State initialState) {
		PriorityQueue<State> openSet = new PriorityQueue<State>(new Comparator<State>() {
			public int compare(State a, State b) {
				double aF = 0.25 * a.getDistanceTravelled() + a.getDistanceToGoal();
				double bF = 0.25 * b.getDistanceTravelled() + b.getDistanceToGoal();
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
			//System.out.println(numStatesExplored);
			if (numStatesExplored > Constants.MAX_CONFIGURATIONS) return 0;
			Collection<State> connectedStates = currentState.getConnectedStates();
			for (State state: connectedStates) {
				if (closedSet.contains(state)) continue;
				state.setDistanceToGoal(heuristic.calculate(state));
				//System.out.println(state.getDistanceTravelled() + " + " + state.getDistanceToGoal());
				openSet.add(state);
			}
		}
		return 0;
	}
}

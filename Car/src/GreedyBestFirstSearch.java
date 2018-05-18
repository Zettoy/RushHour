import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class GreedyBestFirstSearch implements StateSpaceSearch {
	private SearchHeuristic heuristic;
	
	public GreedyBestFirstSearch(SearchHeuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	public int findTotalDistanceToGoal(State initialState) {
		PriorityQueue<State> openSet = new PriorityQueue<State>(new Comparator<State>() {
			public int compare(State a, State b) {
				return a.getDistanceToGoal() - b.getDistanceToGoal();
			}
		});
		Set<State> closedSet = new HashSet<State>();
		initialState.setDistanceToGoal(heuristic.calculate(initialState));
		openSet.add(initialState);
		while (!openSet.isEmpty()) {
			State currentState = openSet.remove();
			closedSet.add(currentState);
			if (currentState.getDistanceTravelled() > Constants.IMPOSSIBLE) return 0;
			if (currentState.isGoal()) return currentState.getDistanceTravelled();
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

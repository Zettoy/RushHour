package Model;

import Controller.State;
/**
 * Interface for state space search
 */
public interface StateSpaceSearch {
	/**
	 * Estimates the cost of reaching a goal state from the specified initial state
	 * @param state the initial State
	 * @return the estimated cost of reaching the goal state from the given state
	 */
	int findTotalDistanceToGoal(State state);
}

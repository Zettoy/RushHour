/**
 * The interface for a search heuristic
 */

package Model;

import Controller.State;

public interface SearchHeuristic {
	/**
	 * Algorithm to compute an estimate of the cost of reaching a specified goal state from a given state
	 * @param state the initial State
	 * @return the value of the heuristic function calculated
	 */
	int calculate(State state);
}

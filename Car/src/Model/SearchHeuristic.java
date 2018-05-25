/**
 * The interface for a search heuristic
 */

package Model;

import Controller.State;

public interface SearchHeuristic {
	int calculate(State state);
}

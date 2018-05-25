/**
 * The interface for a state space search
 */

package Model;

import Controller.State;

public interface StateSpaceSearch {
	int findTotalDistanceToGoal(State state);
}

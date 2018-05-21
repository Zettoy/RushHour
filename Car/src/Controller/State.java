package Controller;
import java.util.Collection;

public interface State {
	boolean isGoal();
	int getDistanceTravelled();
	int getDistanceToGoal();
	void setDistanceToGoal(int distance);
	Collection<State> getConnectedStates();
}

package avs.aldricvs.heuristic;

import avs.aldricvs.node.state.State;

@FunctionalInterface
public interface HeuristicCalculator {
	
	int calculateHeuristic(State state);
}

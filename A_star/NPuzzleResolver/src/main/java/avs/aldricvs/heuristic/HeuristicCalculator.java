package main.java.avs.aldricvs.heuristic;

import main.java.avs.aldricvs.node.state.State;

@FunctionalInterface
public interface HeuristicCalculator {
	
	int calculateHeuristic(State state);
}

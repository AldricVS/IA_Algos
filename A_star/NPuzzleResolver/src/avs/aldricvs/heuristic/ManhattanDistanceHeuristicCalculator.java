package avs.aldricvs.heuristic;

import avs.aldricvs.node.point.Position;
import avs.aldricvs.node.state.State;

public class ManhattanDistanceHeuristicCalculator implements HeuristicCalculator {

	@Override
	public int calculateHeuristic(State state) {
		int size = state.getSize();
		int heuristic = 0;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Position currentPos = new Position(row, col);
				// dont calculate blank box
				if (!state.isBlankBoxPosition(currentPos)) {
					int num = state.getNumberAt(currentPos);

					Position destPos = determineNumberDestination(state, num);
					int distanceBetween = currentPos.distanceBetween(destPos);
					heuristic += distanceBetween;
				}
			}
		}
		return heuristic;
	}

	private Position determineNumberDestination(State state, int num) {
		// we work from 1, not from 0
		num--;
		int size = state.getSize();
		int row = num / size;
		int col = num - row * size;
		return new Position(row, col);
	}
}

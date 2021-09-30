package main.java.avs.aldricvs.node.state;

import main.java.avs.aldricvs.node.point.Position;

public abstract class AbstractState implements State {

	@Override
	public boolean isEndState() {
		int size = this.getSize();
		int counter = 1;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Position position = new Position(row, col);
				int num = this.getNumberAt(position);
				if (num == BLANK_BOX_VALUE || num != counter) {
					return false;
				} else {
					counter++;
					if (counter == 9) {
						break;
					}
				}
			}
		}
		return counter == 9;
	}

	@Override
	public boolean areSameState(State state) {
		if(state == null)
			return false;
		int size = state.getSize();
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Position position = new Position(row, col);
				int num = this.getNumberAt(position);
				int otherNum = state.getNumberAt(position);
				if (num != otherNum) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toStringRepresentation() {
		int size = this.getSize();
		StringBuilder stringBuilder = new StringBuilder();
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Position position = new Position(row, col);
				int num = this.getNumberAt(position);
				// print nothing (if on the blank box) or the number
				stringBuilder.append(num == State.BLANK_BOX_VALUE ? "  " : num + " ");
			}
			stringBuilder.append(System.lineSeparator());
		}
		return stringBuilder.toString();
	}
}

package avs.aldricvs.node.state;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import avs.aldricvs.node.point.Position;

class IntArrayState extends AbstractState {

	private int[][] state;

	// blank box have a -1
	private Position blankBoxPosition;

	public IntArrayState(int[][] state) {
		super();
		this.state = state;
		this.blankBoxPosition = findBlankPosition();
	}

	private Position findBlankPosition() {
		int size = state.length;
		Position blankPos = null;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Position position = new Position(row, col);
				int num = this.getNumberAt(position);
				if (num == BLANK_BOX_VALUE) {
					blankPos = new Position(row, col);
					break;
				}
			}
		}
		Objects.requireNonNull(blankPos,
				"Blank box position not found in array, are you sure you put the " + BLANK_BOX_VALUE + " value ?");
		return blankPos;
	}

	@Override
	public Optional<State> swapBlankBox(Direction direction) {
		Position vector = direction.getVector();
		Position oldPos = blankBoxPosition;
		Position newPos = blankBoxPosition.moveClamped(vector, 0, getSize());
		// check if movement was possible
		if (oldPos.equals(newPos)) {
			return Optional.empty();
		}

		// create a copy of the array
		int[][] newState = Arrays.stream(state)
				.map(int[]::clone)
				.toArray(int[][]::new);

		// swap for real in the array
		int num = this.getNumberAt(newPos);
		newState[oldPos.getRow()][oldPos.getCol()] = num;
		newState[newPos.getRow()][newPos.getCol()] = BLANK_BOX_VALUE;
		return Optional.of(StateFactory.create(newState));
	}

	@Override
	public int getNumberAt(Position position) {
		return state[position.getRow()][position.getCol()];
	}

	@Override
	public int getSize() {
		// it is always a square
		return state.length;
	}

	@Override
	public boolean isBlankBoxPosition(Position position) {
		return new Position(position.getRow(), position.getCol()).equals(blankBoxPosition);
	}

}

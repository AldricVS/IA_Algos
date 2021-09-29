package avs.aldricvs.node.state;

import java.util.Optional;

import avs.aldricvs.node.point.Position;

public interface State {

	public static final int BLANK_BOX_VALUE = 0;

	public int getNumberAt(Position position);

	public int getSize();

	public boolean isBlankBoxPosition(Position position);
	
	public boolean isEndState();

	public boolean areSameState(State state);

	public String toStringRepresentation();

	/**
	 * 
	 * @param direction Where to move the blank box
	 * @return An optional containing the new state layout, or an empty optional if
	 *         movement not possible
	 */
	public Optional<State> swapBlankBox(Direction direction);
	// public
}

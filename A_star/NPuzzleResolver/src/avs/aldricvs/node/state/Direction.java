package avs.aldricvs.node.state;

import avs.aldricvs.node.point.Position;

public enum Direction {
	UP(new Position(-1, 0)),
	DOWN(new Position(1, 0)),
	LEFT(new Position(0, -1)),
	RIGHT(new Position(0, 1));

	private Position vector;

	private Direction(Position vector) {
		this.vector = vector;
	}

	public Position getVector() {
		return vector;
	}

}

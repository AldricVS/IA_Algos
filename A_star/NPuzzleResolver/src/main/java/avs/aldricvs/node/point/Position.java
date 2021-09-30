package main.java.avs.aldricvs.node.point;

public class Position {
	
	private int row;
	
	private int col;

	public Position(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	public int distanceBetween(Position otherPosition) {
		int rowDistance = Math.abs(row - otherPosition.getRow());
		int colDistance = Math.abs(col - otherPosition.getCol());
		return rowDistance + colDistance;
	}
	
	public Position move(Position vector) {
		return new Position(vector.getRow(), vector.getCol());
	}
	
	/**
	 * Restrict the position to the edges of a square (max pos exclued)
	 */
	public Position moveClamped(Position vector, int minPos, int maxPos) {
		int newRow = vector.getRow() + this.row;
		int newCol = vector.getCol() + this.col;
		
		if(newRow < minPos) newRow = minPos;
		if(newCol < minPos) newCol = minPos;
		if(newRow >= maxPos) newRow = maxPos - 1;
		if(newCol >= maxPos) newCol = maxPos - 1;
		
		return new Position(newRow, newCol);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
}

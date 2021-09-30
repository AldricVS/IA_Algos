package main.java.avs.aldricvs.node.state;

public class StateFactory {
	
	public static State create(int[][] content) {
		return new IntArrayState(content);
	}
}

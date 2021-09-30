package main.java.avs.aldricvs.node;

import java.util.List;
import java.util.Optional;

import main.java.avs.aldricvs.node.state.State;

public interface Node {
	
	public State getState();
	
	List<Node> findFullPath();
	
	public int getLevel();
	
	public int getHeuristic();
	
	public Optional<Node> getParent();
	
	public boolean isEndState();
	
	public List<Node> generateChilds();
}

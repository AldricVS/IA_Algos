package main.java.avs.aldricvs.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import main.java.avs.aldricvs.heuristic.HeuristicCalculator;
import main.java.avs.aldricvs.node.state.Direction;
import main.java.avs.aldricvs.node.state.State;

public class NodeImpl implements Node {

	private State state;

	private int heuristic;
	
	private int level;

	private HeuristicCalculator heuristicCalculator;

	/**
	 *  null if root
	 */
	private Node parent;

	public NodeImpl(State state, HeuristicCalculator heuristicCalculator, Node parent) {
		super();
		this.state = Objects.requireNonNull(state);
		this.heuristicCalculator = Objects.requireNonNull(heuristicCalculator);
		this.parent = parent;
		this.level = parent == null ? 0 : parent.getLevel() + 1;
		this.heuristic = heuristicCalculator.calculateHeuristic(state);
	}

	@Override
	public List<Node> generateChilds() {
		return Arrays.stream(Direction.values())
				.map(state::swapBlankBox)
				.flatMap(Optional::stream) // convert stream of optional to "normal" stream (removes empty optionals)
				.filter(s -> !s.areSameState(parent == null ? null : parent.getState()))
				.map(s -> new NodeImpl(s, heuristicCalculator, this))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Node> findFullPath() {
		if(parent == null) {
			return List.of(this);
		}
		
		List<Node> fullPath = new ArrayList<>(level + 1);
		fullPath.addAll(parent.findFullPath());
		fullPath.add(this);
		return fullPath;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public int getHeuristic() {
		return heuristic + level;
	}

	@Override
	public Optional<Node> getParent() {
		return Optional.ofNullable(parent);
	}

	@Override
	public boolean isEndState() {
		return state.isEndState();
	}
	
	@Override
	public int getLevel() {
		return level;
	}
}

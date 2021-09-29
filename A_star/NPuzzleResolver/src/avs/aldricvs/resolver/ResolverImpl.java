package avs.aldricvs.resolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import avs.aldricvs.heuristic.HeuristicCalculator;
import avs.aldricvs.node.Node;
import avs.aldricvs.node.NodeImpl;
import avs.aldricvs.node.point.Position;
import avs.aldricvs.node.state.State;
import avs.aldricvs.resolver.exceptions.NoNodeFoundException;

public class ResolverImpl implements Resolver {

	private Node startNode;

	private List<Node> openList = new ArrayList<>();

	private List<Node> closedList = new ArrayList<>();

	public ResolverImpl(State initialState, HeuristicCalculator heuristicCalculator) {
		super();
		NodeImpl startNode = new NodeImpl(initialState, heuristicCalculator, null);
		this.startNode = startNode;
		this.openList.add(startNode);
	}

	public Optional<Node> findBestPath() {
		if(!isSolvable()) {
			return Optional.empty();
		}
		
		while (!openList.isEmpty()) {
			Node cheapestNode = openList.stream()
			        .min(Comparator.comparingInt(Node::getHeuristic))
			        .orElseThrow(RuntimeException::new);

			if (cheapestNode.isEndState()) {
				return Optional.of(cheapestNode);
			}

			openList.remove(cheapestNode);
			closedList.add(cheapestNode);

			cheapestNode.generateChilds()
			        .stream()
			        .filter(this::hasAlreadyEncounteredThisState)
			        .forEach(openList::add);
		}
		// no solution found
		return Optional.empty();
	}

	private boolean hasAlreadyEncounteredThisState(Node child) {
		return closedList.stream()
		        .map(Node::getState)
		        .noneMatch(n -> n.areSameState(child.getState()));
	}

	@Override
	public boolean isSolvable() {
		// WARN : can only work only if number of tiles is even (3x3 grid with 1 blank
		// space)
		return findInversionsCount() % 2 == 0;
	}

	private int findInversionsCount() {
		int inversionsCount = 0;
		State state = startNode.getState();
		int size = state.getSize();
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				Position posIj = new Position(i, j);
				Position posJi = new Position(j, i);
				if (state.getNumberAt(posJi) != State.BLANK_BOX_VALUE && state.getNumberAt(posJi) > state.getNumberAt(posIj))
					inversionsCount++;
			}
		}
		return inversionsCount;
	}
}

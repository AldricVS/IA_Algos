package avs.aldricvs.resolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import avs.aldricvs.heuristic.HeuristicCalculator;
import avs.aldricvs.node.Node;
import avs.aldricvs.node.NodeImpl;
import avs.aldricvs.node.state.State;

public class ResolverImpl implements Resolver {

	private List<Node> openList = new ArrayList<>();

	private List<Node> closedList = new ArrayList<>();

	public ResolverImpl(State initialState, HeuristicCalculator heuristicCalculator) {
		super();
		Node startNode = new NodeImpl(initialState, heuristicCalculator, null);
		this.openList.add(startNode);
	}

	public Optional<Node> findBestPath() {
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
					.filter(this::hasNeverEncounteredThisState)
					.forEach(openList::add);
		}
		// no solution found
		return Optional.empty();
	}

	private boolean hasNeverEncounteredThisState(Node child) {
		return closedList.stream()
				.map(Node::getState)
				.noneMatch(n -> n.areSameState(child.getState()));
	}
}

package avs.aldricvs.app;

import java.util.List;

import avs.aldricvs.heuristic.HeuristicCalculator;
import avs.aldricvs.heuristic.ManhattanDistanceHeuristicCalculator;
import avs.aldricvs.node.Node;
import avs.aldricvs.node.state.State;
import avs.aldricvs.node.state.StateFactory;
import avs.aldricvs.resolver.Resolver;
import avs.aldricvs.resolver.ResolverImpl;

public class CliApp implements App {

	private HeuristicCalculator heuristicCalculator = new ManhattanDistanceHeuristicCalculator();

	@Override
	public void resolve(int[][] initialLayout) {
		State initialState = StateFactory.create(initialLayout);
		Resolver resolver = new ResolverImpl(initialState, heuristicCalculator);
		resolver.findBestPath().ifPresentOrElse(this::showResult, this::showInsolvable);
	}
	
	private void showResult(Node node) {
		List<Node> fullPath = node.findFullPath();
		fullPath.forEach(this::printNodes);
		System.out.println("Puzzle solved in " + node.getLevel() + " steps");
	}

	private void showInsolvable() {
		System.out.println("The puzzle cannot be solved...");
	}

	private void printNodes(Node currentNode) {
		String stringRepresentation = currentNode.getState().toStringRepresentation();
		System.out.println(stringRepresentation + "\n");
	}

}

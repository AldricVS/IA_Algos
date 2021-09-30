package main.java.avs.aldricvs.app;

import java.util.List;

import main.java.avs.aldricvs.heuristic.HeuristicCalculator;
import main.java.avs.aldricvs.heuristic.ManhattanDistanceHeuristicCalculator;
import main.java.avs.aldricvs.node.Node;
import main.java.avs.aldricvs.node.state.State;
import main.java.avs.aldricvs.node.state.StateFactory;
import main.java.avs.aldricvs.resolver.Resolver;
import main.java.avs.aldricvs.resolver.ResolverImpl;

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

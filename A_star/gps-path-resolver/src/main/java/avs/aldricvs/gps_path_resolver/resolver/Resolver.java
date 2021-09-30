package avs.aldricvs.gps_path_resolver.resolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import avs.aldricvs.gps_path_resolver.node.CityNode;
import avs.aldricvs.gps_path_resolver.node.Edge;

public class Resolver {

	private List<CityNode> openList = new ArrayList<>();
	private List<CityNode> closedList = new ArrayList<>();
	
	public Optional<CityNode> findBestPath(CityNode from, CityNode to) {
		from.calculateF();
		openList.add(from);
		
		while(!openList.isEmpty()) {
			CityNode bestNode = openList.stream()
					.min(Comparator.comparingInt(CityNode::getF))
					.orElseThrow(); // shouldn't happen, as we check with "while"
			
			if(bestNode.isTargetNode()) {
				return Optional.of(bestNode);
			}
			
			// work on all the neighbours ("childs")
			bestNode.getEdges().forEach(handleEdge(bestNode));
			
			openList.remove(bestNode);
			closedList.add(bestNode);
		}
		// nothing found...
		return Optional.empty();
	}
	
	private Consumer<Edge> handleEdge(CityNode parent) {
		return edge -> {
			CityNode node = edge.getNextNode();
			int distance = parent.getG() + edge.getDistance();
			
			// WARN : we don't generate new nodes, we update the same ones as we travel into the algorithm
			
			// 3 cases :
			// New node is not discovered for now ==> nothing unusual
			if(!openList.contains(node) && !closedList.contains(node)) {
				node.setParent(parent);
				node.setGAndRecalculateF(distance);
				openList.add(node);
			} else {
				// New node is better than the older one
				if(distance < node.getG()) {
					node.setParent(parent);
					node.setGAndRecalculateF(distance);
					
					if(closedList.contains(node)){
                        closedList.remove(node);
                        openList.add(node);
                    }
				}
			}
		};
	}
}

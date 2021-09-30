package avs.aldricvs.gps_path_resolver.app;

import java.util.List;
import java.util.Optional;

import avs.aldricvs.gps_path_resolver.node.CityNode;
import avs.aldricvs.gps_path_resolver.resolver.Resolver;

public class CliApp implements App {

	@Override
	public void launch(CityNode from, CityNode to) {
		Resolver resolver = new Resolver();
		Optional<CityNode> endNode = resolver.findBestPath(from, to);
		endNode.ifPresentOrElse(this::handleSuccess, this::handleFailure);
	}

	private void handleSuccess(CityNode endNode) {
		List<CityNode> fullPath = endNode.findFullPath();
		fullPath.stream()
			.map(CityNode::getName)
			.forEach(System.out::println);
		
		System.out.println("Path passing through " + fullPath.size() + " cities");
	}
	
	private void handleFailure() {
		System.out.println("No path found...");
	}
}

package avs.aldricvs.resolver;

import java.util.Optional;

import avs.aldricvs.node.Node;

public interface Resolver {
	
	Optional<Node> findBestPath();
}
package main.java.avs.aldricvs.resolver;

import java.util.Optional;

import main.java.avs.aldricvs.node.Node;

public interface Resolver {
	
	Optional<Node> findBestPath();
}
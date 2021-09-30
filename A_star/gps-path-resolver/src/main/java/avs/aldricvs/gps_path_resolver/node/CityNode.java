package avs.aldricvs.gps_path_resolver.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Aldric Vitali Silvestre
 */
public class CityNode {
	
	private String name;
	
	private List<Edge> edges = new ArrayList<>();
	
	private int distanceToArrival; // this is the heuristic
	
	private int g = 0;
	
	private int f = 0;
	
	private CityNode parent;

	public CityNode(String name, int distanceToArrival) {
		super();
		this.name = name;
		this.distanceToArrival = distanceToArrival;
	}
	
	public void calculateF() {
		this.f = g + distanceToArrival;
	}
	
	public boolean isTargetNode() {
		return distanceToArrival == 0;
	}
	
	/**
	 * Add the edge in both sides !
	 * @param neighbour
	 * @param distance
	 */
	public void addNeighbour(CityNode neighbour, int distance) {
		Edge edge = new Edge(neighbour, distance);
		edges.add(edge);
		// can't call "addNeighbour", infinite recursion !
		neighbour.getEdges().add(new Edge(this, distance));
	}
	
	public List<CityNode> findFullPath() {
		if(parent == null) {
			return List.of();
		}
		List<CityNode> fullPath = new ArrayList<>();
		fullPath.addAll(parent.findFullPath());
		fullPath.add(this);
		return fullPath;
	}

	public String getName() {
		return name;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public int getDistanceToArrival() {
		return distanceToArrival;
	}

	public int getG() {
		return g;
	}

	public void setGAndRecalculateF(int g) {
		this.g = g;
		calculateF();
	}

	public int getF() {
		return f;
	}

	public Optional<CityNode> getParent() {
		return Optional.ofNullable(parent);
	}

	public void setParent(CityNode parent) {
		this.parent = parent;
	}
}

package avs.aldricvs.gps_path_resolver.node;

public class Edge {

	private int distance;
	
	private CityNode nextNode;

	public Edge(CityNode nextNode, int distance) {
		super();
		this.distance = distance;
		this.nextNode = nextNode;
	}

	public int getDistance() {
		return distance;
	}

	public CityNode getNextNode() {
		return nextNode;
	}
}

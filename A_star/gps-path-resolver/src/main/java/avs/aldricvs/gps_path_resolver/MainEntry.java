package avs.aldricvs.gps_path_resolver;

import avs.aldricvs.gps_path_resolver.app.App;
import avs.aldricvs.gps_path_resolver.app.CliApp;
import avs.aldricvs.gps_path_resolver.node.CityNode;

public class MainEntry {

	public static void main(String[] args) {
		
		// Create all the cities
		CityNode arad = new CityNode("Arad", 366);
		CityNode bucharest = new CityNode("Bucharest", 0);
		CityNode craiova = new CityNode("Craiova", 160);
		CityNode dobreta = new CityNode("Dobreta", 242);
		CityNode eforie = new CityNode("Eforie", 161);
		CityNode fagaras = new CityNode("Fagaras", 178);
		CityNode giurgiu = new CityNode("Giurgiu", 77);
		CityNode hirsova = new CityNode("Hirsova", 151);
		CityNode iasi = new CityNode("Iasi", 226);
		CityNode lugoj = new CityNode("Lugoj", 244);
		CityNode mehadia = new CityNode("Mehadia", 241);
		CityNode neamt = new CityNode("Neamt", 234);
		CityNode oradea = new CityNode("Oradea", 380);
		CityNode pitesti = new CityNode("Pitesti", 98);
		CityNode rimnicuVilcea = new CityNode("Rimnicu-Vilcea", 193);
		CityNode sibiu = new CityNode("Sibiu", 253);
		CityNode timisoara = new CityNode("Timisoara", 329);
		CityNode urziceni = new CityNode("Urziceni", 80);
		CityNode vaslui = new CityNode("Vaslui", 199);
		CityNode zerind = new CityNode("Zerind", 374);
		
		// join them !
		oradea.addNeighbour(zerind, 71);
		oradea.addNeighbour(sibiu, 151);
		zerind.addNeighbour(arad, 75);
		arad.addNeighbour(sibiu, 140);
		arad.addNeighbour(timisoara, 118);
		sibiu.addNeighbour(fagaras, 99);
		sibiu.addNeighbour(rimnicuVilcea, 80);
		timisoara.addNeighbour(lugoj, 111);
		lugoj.addNeighbour(mehadia, 70);
		mehadia.addNeighbour(dobreta, 75);
		dobreta.addNeighbour(craiova, 120);
		craiova.addNeighbour(pitesti, 138);
		craiova.addNeighbour(rimnicuVilcea, 146);
		rimnicuVilcea.addNeighbour(pitesti, 97);
		fagaras.addNeighbour(bucharest, 211);
		pitesti.addNeighbour(bucharest, 191);
		bucharest.addNeighbour(giurgiu, 90);
		bucharest.addNeighbour(urziceni, 85);
		urziceni.addNeighbour(hirsova, 98);
		urziceni.addNeighbour(vaslui, 142);
		hirsova.addNeighbour(eforie, 86);
		vaslui.addNeighbour(iasi, 92);
		iasi.addNeighbour(neamt, 87);
		
		App app = new CliApp();
		app.launch(arad, bucharest);
	}
}

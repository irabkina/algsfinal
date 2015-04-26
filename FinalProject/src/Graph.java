import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class Graph {

	private HashMap<Integer, ArrayList<Integer>> edges;
	private Integer numEdges;
	private Integer numVertices;

	public Graph contract(Integer node){
		Graph toReturn = new Graph();
		HashMap<Integer, ArrayList<Integer>> vals = new HashMap<Integer, ArrayList<Integer>>();
		for(Entry<Integer, ArrayList<Integer>> entry : this.getEdges().entrySet()){
			vals.put(entry.getKey(), (ArrayList<Integer>)entry.getValue().clone()); 
		}
		toReturn.setEdges(vals);
		ArrayList<Integer> values = this.getEdges().get(node);
		for(int e :values){
			ArrayList<Integer> eVals = toReturn.getEdges().get(e);
			eVals.remove(node); // remove object vs index
			toReturn.edges.put(e, eVals);
		}
		toReturn.edges.remove(node);
		toReturn.setNumEdges(this.getNumEdges()-values.size());
		toReturn.setNumVertices(this.getNumVertices()-1);
		return toReturn;
	}

	public Graph add(Integer node1, Integer node2){
		Graph toReturn = new Graph();
		HashMap<Integer, ArrayList<Integer>> vals = new HashMap<Integer, ArrayList<Integer>>();
		for(Entry<Integer, ArrayList<Integer>> entry : this.getEdges().entrySet()){
			vals.put(entry.getKey(), (ArrayList<Integer>)entry.getValue().clone()); 
		}
		
		toReturn.setEdges(vals);
		ArrayList<Integer> vals1 = toReturn.getEdges().get(node1);
		vals1.add(node2);
		toReturn.edges.put(node1, vals1);
		ArrayList<Integer> vals2 = toReturn.getEdges().get(node2);
		vals2.add(node1);
		toReturn.edges.put(node2, vals2);
		
		int edges =this.getNumEdges();
		toReturn.setNumEdges(edges+1);
		int vertices = this.getNumVertices();
		toReturn.setNumVertices(vertices);
		
		return toReturn;
	}
	
	public boolean isComplete(){
		return numEdges==(numVertices*(numVertices-1))/2;
	}
	
	public HashMap<Integer, ArrayList<Integer>> getEdges() {
		return edges;
	}

	public void setEdges(HashMap<Integer, ArrayList<Integer>> edges) {
		this.edges = edges;
	}

	public Integer getNumEdges() {
		return numEdges;
	}

	public void setNumEdges(Integer numEdges) {
		this.numEdges = numEdges;
	}

	public Integer getNumVertices() {
		return numVertices;
	}

	public void setNumVertices(Integer numVertices) {
		this.numVertices = numVertices;
	}
	
	/*public static void main(String[] args) {
		Graph test = new Graph();
		test.setNumEdges(4);
		test.setNumVertices(4);
		
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> one = new ArrayList<Integer>();
		one.add(2);
		one.add(4);
		map.put(1,one);
		ArrayList<Integer> two = new ArrayList<Integer>();
		two.add(1);
		two.add(3);
		map.put(2,two);
		ArrayList<Integer> three = new ArrayList<Integer>();
		three.add(2);
		three.add(4);
		map.put(3,three);
		ArrayList<Integer> four = new ArrayList<Integer>();
		four.add(1);
		four.add(3);
		map.put(4,four);
		
		test.setEdges(map);
		
		System.out.println(test.isComplete());
		
		Graph testAdd = test.add(2,4);
		System.out.println(testAdd.isComplete());
		
		Graph testAdd2 = testAdd.add(1,3);
		System.out.println(testAdd2.isComplete());
		
		Graph testContract = test.contract(2);
		System.out.println(testContract.isComplete());

		Graph testContract2 = testContract.contract(1);
		System.out.println(testContract2.isComplete());
//		System.out.println(testContract2.numVertices);
//		System.out.println(testContract2.numEdges);
//		System.out.println(testContract2.edges);ll
		
		
		
		
	}*/
}



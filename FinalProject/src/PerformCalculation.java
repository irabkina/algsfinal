import java.util.ArrayList;
import java.util.HashMap;


public class PerformCalculation extends Thread {
	
	private Graph graph;
	private Object lock = new Object();
	private static  ArrayList<Integer> polyNumbers = new ArrayList<Integer>();
	
	public PerformCalculation(Graph g){
		graph = g;
	}
	
	
	public void run(){
		System.out.println("test");
		Integer currentNode = getNode();
		Graph contracted = graph.contract(currentNode);
		if (!contracted.isComplete()){
			new PerformCalculation(contracted).run();
		}
		else{
			synchronized(lock){
				polyNumbers.add(contracted.getNumVertices());
			}
		}
		
		Graph added = graph.add(getPartnerNode(currentNode),currentNode);
		if (!added.isComplete()){
			this.graph = added;
			this.run();
		}else{
			synchronized(lock){
				polyNumbers.add(added.getNumVertices());
			}
		}
		
	}
	
	public Integer getNode(){
		HashMap<Integer, ArrayList<Integer>> vals = graph.getEdges();
		for (Integer k: vals.keySet()){
			if (vals.get(k).size() < graph.getNumVertices() -1){
				return k;
			}
		}
		return -1;
	}
	
	public Integer getPartnerNode(Integer node1){
		HashMap<Integer, ArrayList<Integer>> vals = graph.getEdges();
		ArrayList<Integer> adjacents = vals.get(node1);
		for (Integer k: vals.keySet()){
			if ( !k.equals(node1) && !adjacents.contains(k)){
				return k;
			}
		}
		return -1;
	}
	
	public static void main(String args[]){
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
		PerformCalculation p = new PerformCalculation(test);
		p.run();
		System.out.println(polyNumbers);
	}
	
}

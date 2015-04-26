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
		test.setNumEdges(5);
		test.setNumVertices(5);
		
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
		three.add(5);
		map.put(3,three);
		ArrayList<Integer> four = new ArrayList<Integer>();
		four.add(1);
		four.add(5);
		map.put(4,four);
		ArrayList<Integer> five = new ArrayList<Integer>();
		five.add(3);
		five.add(4);
		map.put(5,five);
		
		test.setEdges(map);
		
		
		/*Graph test = new Graph();
		test.setNumEdges(12);
		test.setNumVertices(8);
		
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> one = new ArrayList<Integer>();
		one.add(2);
		one.add(5);
		one.add(6);
		map.put(1,one);
		ArrayList<Integer> two = new ArrayList<Integer>();
		two.add(1);
		two.add(7);
		two.add(8);
		map.put(2,two);
		ArrayList<Integer> three = new ArrayList<Integer>();
		three.add(4);
		three.add(7);
		three.add(8);

		map.put(3,three);
		ArrayList<Integer> four = new ArrayList<Integer>();
		four.add(3);
		four.add(5);
		four.add(6);
		map.put(4,four);
		
		
		ArrayList<Integer> five = new ArrayList<Integer>();
		five.add(1);
		five.add(4);
		five.add(7);
		map.put(5,five);
		ArrayList<Integer> six = new ArrayList<Integer>();
		six.add(1);
		six.add(4);
		six.add(8);
		map.put(6,six);
		ArrayList<Integer> seven = new ArrayList<Integer>();
		seven.add(2);
		seven.add(3);
		seven.add(5);
		map.put(7,seven);
		ArrayList<Integer> eight = new ArrayList<Integer>();
		eight.add(2);
		eight.add(6);
		eight.add(3);

		map.put(8,eight);
		
		
		
		test.setEdges(map);*/
		
		PerformCalculation p = new PerformCalculation(test);
		p.run();
		HashMap<Integer, Integer> form3 = new HashMap<Integer,Integer>();
		for(Integer n : polyNumbers){
			if (form3.containsKey(n)){
				int num = form3.get(n)+1;
				form3.put(n, num);
			}
			else{
				form3.put(n,1);
			}
		}
		System.out.println(form3);
	}
	
}

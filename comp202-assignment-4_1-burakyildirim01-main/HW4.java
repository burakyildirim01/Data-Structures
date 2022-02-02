import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class HW4 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Graph graph = new Graph();
				
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] parts = line.split(" ");
			if (parts[0].equals("end")) break;
			String src = parts[0];
			String dst = parts[1];
			int cost = Integer.parseInt(parts[2]);
			int latency = Integer.parseInt(parts[3]);
			
			graph.addVertex(src);
			graph.addVertex(dst);
			Edge edge = new Edge(src, dst, cost, latency);
			graph.addEdge(edge);
		}
		
		//System.out.println(Arrays.deepToString(graph.asArray(false)));
		//System.out.println(Arrays.deepToString(graph.asArray(true)));
		
		HW4 hw4 = new HW4();
		scan.close();
		
		System.out.println(hw4.totalLinkCost(graph));
		System.out.println(hw4.cheapestNetwork(graph.asArray(false)));
		System.out.println(hw4.savedAmount(graph));
	
	}
	
	// You can add any methods you need, both to this file and Graph.java file

	// The method for task 1 
	int totalLinkCost(Graph graph) {
		int result = 0;
		ArrayList<Edge> edges = graph.edges;
		for(int i = 0;i<edges.size();i++) {
			result+=edges.get(i).cost;
		}
		return result;
	}
	int minIndex(int cost[], Boolean mstVisited[], int length) {
		int result = 0;
        	int min = Integer.MAX_VALUE;
        	for (int i = 0; i < length; i++) {
            		if (min > cost[i] && mstVisited[i] == false) {
                		min = cost[i];
                		result = i;
            		}
        	}
        	return result;
    	}
	// The method for task 2 
	int cheapestNetwork(int[][] array_graph) {
		int result = 0;
		int length = array_graph.length;
        	int cost[] = new int[length];
        	int prev[] = new int[length];
        	Boolean mstVisited[] = new Boolean[length];
        	cost[0] = 0;
        	for (int i = 0; i < length; i++) {
            		cost[i] = Integer.MAX_VALUE;
            		mstVisited[i] = false;
       		}
        	int j = 0;
        	while (j<length-1) {
            		int u = minIndex(cost, mstVisited, length);
            		mstVisited[u] = true;
            		for (int z = 0; z < length; z++) {
                		if (cost[z] > array_graph[u][z] && array_graph[u][z] != 0 && mstVisited[z] == false) {
                    			cost[z] = array_graph[u][z];
                    			prev[z] = u;
                		}
            		}
            		j++;
        	}
        	for(int i=1;i<length;i++) {
        		result+=array_graph[i][prev[i]];
        	}
		return result;
	}

	// The method for task 3 
	int savedAmount(Graph graph) {
		return totalLinkCost(graph)-cheapestNetwork(graph.asArray(false));
	}
	
}

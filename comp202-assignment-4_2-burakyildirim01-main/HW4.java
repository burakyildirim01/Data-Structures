import java.util.Arrays;
import java.util.Scanner;

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
		
		System.out.println(hw4.totalTransitTime(graph));
		System.out.println(hw4.cheapestTransitTime(graph));
		System.out.println(hw4.timeIncrease(graph));
	
	}
	
	// You can add any methods you need, both to this file and Graph.java file

	// The method for task 1 
	int totalTransitTime(Graph graph) {
		int result = 0;
		int graph_array[][] = graph.asArray(true);
		int i,j,k;
		int length = graph_array.length;
		for(i = 0; i<length;i++) {
			for(j=0;j<length;j++) {
				if(i!=j && graph_array[i][j]==0) {
					graph_array[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		for(i=0;i<length;i++) {
            		for(j=0;j<length;j++) {
                		for(k=0;k<length;k++) {
                    			if (graph_array[j][i]+graph_array[i][k]<graph_array[j][k] &&
                    				graph_array[j][i]!=Integer.MAX_VALUE &&
                    				graph_array[i][k]!=Integer.MAX_VALUE) {
                    				graph_array[j][k]=graph_array[j][i]+graph_array[i][k];
                    			}
                		}
            		}
        	}
        	for(i=0;i<length;i++) {
        		for(j=0;j<length;j++) {
        			if(i!=j && graph_array[i][j]!=Integer.MAX_VALUE) {
        				result+=graph_array[i][j];
        			}
        		}
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
	int[][] cheapestNetwork(Graph graph) {
		int[][] array_graph = graph.asArray(false);
		int[][] latencies = graph.asArray(true);
		int length = array_graph.length;
        	int cost[] = new int[length];
        	int prev[] = new int[length];
        	int[][] result = new int[length][length];
        	for(int i = 0;i<length;i++) {
        		for(int j = 0;j<length;j++) {
        			if(i!=j) {
        				result[i][j] = Integer.MAX_VALUE;
        			}
        		}
        	}
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
        		result[prev[i]][i] = latencies[i][prev[i]];
        		result[i][prev[i]] = latencies[i][prev[i]];
        	}
		return result;
	}
	// The method for task 2 
	int cheapestTransitTime(Graph graph) {
		int result = 0;
		int[][] graph_array = cheapestNetwork(graph);
		int length = graph_array.length;
		int i,j,k;
		for(i=0;i<length;i++) {
            		for(j=0;j<length;j++) {
                		for(k=0;k<length;k++) {
                   			 if (graph_array[j][i]+graph_array[i][k]<graph_array[j][k] &&
                    				graph_array[j][i]!=Integer.MAX_VALUE &&
                    				graph_array[i][k]!=Integer.MAX_VALUE) {
                    				graph_array[j][k]=graph_array[j][i]+graph_array[i][k];
                    			}
                		}
            		}
        	}
		for(i=0;i<length;i++) {
        		for(j=0;j<length;j++) {
        			if(i!=j && graph_array[i][j]!=Integer.MAX_VALUE) {
        				result+=graph_array[i][j];
        			}
        		}
        	}
		return result;
	}

	// The method for task 3 
	int timeIncrease(Graph graph) {
		return cheapestTransitTime(graph)-totalTransitTime(graph);
	}
	
}

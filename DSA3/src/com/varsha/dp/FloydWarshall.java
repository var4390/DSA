package com.varsha.dp;

//All Pairs Shortest Path algo - Floyd Warshall algo
//O(n^3)

public class FloydWarshall {

	private static final int INF = 99999;
	private int n;
	
	public FloydWarshall(int n) {
		this.n = n;
	}

	public static void main(String[] args) {
		
	/* Let us create the following weighted graph
        10
     (0)------->(3)
     |         /|\
     5 |          |
     |          | 1
     \|/         |
     (1)------->(2)
        3          
     */
		int graph[][] = { { 0, 5, INF, 10 },
                { INF, 0, 3, INF },
                { INF, INF, 0, 1 },
                { INF, INF, INF, 0 } };

		FloydWarshall g = new FloydWarshall(4);
		g.allPairsShortestPath(graph);
		
		/* Let us create the following weighted graph
        1
	(0)----------->(1)
	/|\               |
	 |               |
	-1 |               | -1
	 |                \|/
	(3)<-----------(2)
	    -1     
	    */
     
		int graph2[][] = { {0, 1, INF, INF},
                  {INF, 0, -1, INF},
                  {INF, INF, 0, -1},
                  {-1, INF, INF, 0}};
		
		g.allPairsShortestPath(graph2);
	}

	public void allPairsShortestPath(int[][] dist) {
		
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j =0; j<n; j++) {
					if(dist[i][k]+dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k]+dist[k][j];
					}
				}
			}
		}
		
		System.out.println("All Pairs Shortest Path:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dist[i][j] == INF)
					System.out.print("INF ");
				else
					System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
		
		if(detectNegativeCycle(dist)) {
			System.out.println("Graph has negative cycle.");
		} else {
			System.out.println("Graph doesn't have negative cycle.");
		}
	}

	public boolean detectNegativeCycle(int[][] dist) {
		
		//detect if it has a negative cycle or not --> if weight=-ve cycle-start and end is same if not 0, then -ve cycle
		//coz it doesn't take time to reach its own node
		for(int i=0; i<n; i++) {
			if(dist[i][i] < 0) {
				return true;
			} 
		}
		return false;
	}
}

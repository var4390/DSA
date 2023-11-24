package com.varsha.backtracking;

public class GraphColoring {

	private int n; //no.of vertices
	private int[] color;
	private int m; //no.of colors
	
	public GraphColoring(int n) {
		this.n = n;
		color = new int[n];
		for(int i =0; i<n ;i++) {
			color[i] = 0;
		}
	}
	
	public void printSolution() {
		for(int i =0; i<n ; i++) {
			System.out.print(color[i] + " ");
		}
		System.out.println();
	}
	
	public boolean graphColor(int[][] graph, int curr, int m) {
		//all vertices are colored
		if(curr == n) {
			return true;
		}
		
		for(int c=1; c<=m; c++) {
			
			if(isSafe(graph, curr, c)) {
				color[curr] = c;
				
				if(graphColor(graph, curr+1, m)) {
					return true;
				}
				
				//backtrack - if assigning c to curr vertex doesn't yield result, then remove it - so 0
				color[curr] = 0;
			}
		}		
		
		return false;
	}
	
	public boolean isSafe(int[][] graph, int curr, int c) {
		
		for(int i = 0; i<n; i++) {
			//an edge exists from curr to i and if c is already assigned to those vertices - i, so we can't assign c to curr vertex
			if(graph[curr][i] == 1 && c == color[i]) {
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		
		GraphColoring g = new GraphColoring(4); //n=4
		
		int graph[][] = {{0, 1, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 1}, {1, 0, 1, 0}}; //0-> no edge, 1-->edge
		
		boolean solExists = g.graphColor(graph, 0, 3);
		if(solExists) {
			System.out.println("Solution exists. printing them:");
			g.printSolution();
		} else {
			System.out.println("No solution exists.");
		}
	}

	

}

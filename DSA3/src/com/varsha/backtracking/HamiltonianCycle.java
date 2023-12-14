package com.varsha.backtracking;

public class HamiltonianCycle {

	private int n; // no. of vertices
	private int[] path;

	public HamiltonianCycle(int n) {
		this.n = n;
		path = new int[n];
		for (int i = 0; i < n; i++) {
			path[i] = -1;
		}
		path[0] = 0; // we choose starting vertex as 0
	}

	public void hamCycle(int[][] graph) {

		if (hamCycleUtil(graph, 1)) {
			System.out.println("hamiltonian cycle exists and is below:");
			printSolution();
		} else {
			System.out.println("No solution exists");
		}
	}

	public boolean hamCycleUtil(int[][] graph, int curr) {

		// base case --> all vertices visited, check if edge exists between last vertex
		// and 1st vertex
		if (curr == n) {
			if (graph[path[curr - 1]][0] == 1) {
				return true;
			} else {
				return false;
			}
		}

		// recursively try remaining vertices - 0 is starting vertex, so we will start
		// from 1--> v=1 to n-1
		for (int v = 1; v < n; v++) {
			if (isSafe(graph, v, curr)) {
				path[curr] = v; // check if v can be assigned and assign if so

				if (hamCycleUtil(graph, curr + 1) == true) { // move onto next vertex
					return true;
				}

				path[curr] = -1; // remove and backtrack if assigning v doesn't yield a solution
			}
		}

		return false;
	}

	public boolean isSafe(int[][] graph, int v, int curr) {

		if (graph[path[curr - 1]][v] == 0) { // if no edge between previous vertex - path[curr-1] and v, which we are
												// trying to assign to path[curr]
			return false;
		}

		for (int i = 0; i < curr; i++) {
			if (path[i] == v) {
				return false;
			}
		}

		return true;
	}

	public void printSolution() {
		for (int i = 0; i < n; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.print(path[0]);
		System.out.println();
	}

	public static void main(String[] args) {
		HamiltonianCycle h = new HamiltonianCycle(5);

		 /* Let us create the following graph
        (0)--(1)--(2)
         |   / \   |
         |  /   \  |
         | /     \ |
        (3)-------(4)    */

		int graph[][] = { { 0, 1, 0, 1, 0 }, { 1, 0, 1, 1, 1 }, { 0, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1 },
				{ 0, 1, 1, 1, 0 } };

		h.hamCycle(graph);

	}

}

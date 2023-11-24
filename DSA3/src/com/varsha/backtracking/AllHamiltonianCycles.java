package com.varsha.backtracking;

import java.util.LinkedList;
import java.util.Stack;

//Given an undirected graph, the task is to determine whether the graph contains a Hamiltonian cycle or not. 
//If it contains, then prints the path.

public class AllHamiltonianCycles {

	private int n; //no. of vertices
	private LinkedList<Integer> path;
	
	public AllHamiltonianCycles(int n) {
		this.n = n;
		path = new LinkedList<Integer>();
		path.push(0); //we choose starting vertex as 0
	}
	

	public void hamCycleUtil(int[][] graph, int curr) {
		
		//base case --> all vertices visited, check if edge exists between last vertex and 1st vertex
		if(curr == n) {
			if(graph[path.get(path.size()-1)][path.get(0)] == 1) {
				printSolution();
			} else {
				return;
			}
		}
		
		//recursively try remaining vertices - 0 is starting vertex, so we will start from 1--> v=1 to n-1
		for(int v= 1; v<n; v++) {
			if(isSafe(graph, v, curr)) {
				path.add(v);//check if v can be assigned and assign if so
				
				hamCycleUtil(graph, curr+1);// move onto next vertex

				path.remove(path.size()-1);
				//path.remove(path.size()-1);
				// Remove current vertex from path and process other vertices
			}
		}
		
		return;
	}

	public boolean isSafe(int[][] graph, int v, int curr) {
		
		if(graph[path.get(curr-1)][v] == 0) { //if no edge between previous vertex - path[curr-1] and v, which we are trying to assign to path[curr]
			return false;
		}
		
		for(int i = 0; i<curr; i++) {
			if(path.get(i) == v) {
				return false;
			}
		}
		
		return true;
	}

	public void printSolution() {
		for(int i:path) {
			System.out.print(i+ " ");
		}
		System.out.print(path.get(0));
		System.out.println();
	}
	
	public static void main(String[] args) {
		AllHamiltonianCycles h = new AllHamiltonianCycles(6);

		/* Input Graph:
		(0) - - -- (2)
			| \ / |
			| (1) |
			| / | |
			| / | |
		(5)----(4)--(3)*/

		
		int[][] graph = {
				{ 0, 1, 1, 0, 0, 1 },
				{ 1, 0, 1, 0, 1, 1 },
				{ 1, 1, 0, 1, 0, 0 },
				{ 0, 0, 1, 0, 1, 0 },
				{ 0, 1, 0, 1, 0, 1 },
				{ 1, 1, 0, 0, 1, 0 },
			};
		
		h.hamCycleUtil(graph, 1);
		
	}

}

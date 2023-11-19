package com.varsha.dfs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class DFSGraph {

	private int n;
	private LinkedList<Integer> adj[];
	
	public DFSGraph(int n) {
		this.n = n;
		adj = new LinkedList[n];
		for(int i =0; i<n; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int u, int v) {
		adj[u].add(v);
	}
	
	public void dfs(int source) {
		
		boolean[] visited = new boolean[n];
		
		dfsUtil(source, visited);
	}
	
	public void dfsUtil(int source, boolean[] visited) {
		
		System.out.print(source+ " ");
		visited[source] = true;
		
		ListIterator<Integer> it = adj[source].listIterator();
		while(it.hasNext()) {
			int curr = it.next();
			if(!visited[curr]) { 
				dfsUtil(curr, visited); //recursion - uses stack so it will traverse in DFS order - branch wise
			}
		}
		
	}

	public static void main(String[] args) {
		
		DFSGraph graph = new DFSGraph(5);
		
		graph.addEdge(0, 1); 
		graph.addEdge(1, 0);
		graph.addEdge(0, 2); 
		graph.addEdge(2, 0);
		graph.addEdge(1, 3); 
		graph.addEdge(3, 1);
		graph.addEdge(2, 4); 
		graph.addEdge(4, 2);
		graph.addEdge(3, 4); 
		graph.addEdge(4, 3);
		
		graph.dfs(0);
		
	}

}

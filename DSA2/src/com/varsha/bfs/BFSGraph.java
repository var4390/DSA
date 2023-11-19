package com.varsha.bfs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class BFSGraph {

	private int n; //no. of vertices
	private LinkedList<Integer> adj[]; //adj lists to represent graph
	
	public BFSGraph(int n) {
		this.n = n;
		adj = new LinkedList[n];
		for(int i =0; i<n; i++) {
			adj[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int u, int v) {
		adj[u].add(v);
	}
	
	public void bfs(int source) {
		
		boolean[] visited = new boolean[n]; //true - visited, false - unvisited
		
		Queue<Integer> q = new LinkedList<Integer>(); //Queue to implement BFS

		//add source to Q and mark it as visited 
		q.add(source);
		visited[source] = true;
		
		//now remove front from Q and visit unvisted neighbors of front and push them to Q
		while(!q.isEmpty()) {
			source = q.poll();
			System.out.print(source+ " "); //removes front element
			
			ListIterator<Integer> it = adj[source].listIterator();
			while(it.hasNext()) {
				int curr = it.next(); //neighbors of source
				if(!visited[curr]) { //if unvisisted, mark visited and push to Q
					visited[curr] = true;
					q.add(curr);
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		BFSGraph graph = new BFSGraph(5); //5 vertices - 0 to 4
		
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
		
		
		
		System.out.println("BFS traversal for Graph: ");
		graph.bfs(2);
	}

}

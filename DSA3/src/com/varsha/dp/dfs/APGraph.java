package com.varsha.dp.dfs;

import java.util.Iterator;
import java.util.LinkedList;

public class APGraph {
	
	private int n;
	private LinkedList<Integer> adj[];
	private static int time;
	
	public APGraph(int n) {
		this.n = n;
		adj = new LinkedList[n];
		for(int i =0; i<n; i++) {
			adj[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int u, int v) {
		adj[u].add(v); //u to v
		adj[v].add(u); //v to u since biconnected graph
	}
	
	public void AP() {
		boolean[] visited = new boolean[n];
		int[] low = new int[n];
		int[] dis = new int[n];
		int parent = -1; //root
		boolean[] isAP = new boolean[n];
		int source = 0;
		
		APUtil(visited, low, dis, parent, isAP, source);
		
		for(int i=0; i<n; i++) {
			if(isAP[i] == true) {
				System.out.print(i+ " ");
			}
		}
		System.out.println();
	}
	
	public void APUtil(boolean[] visited,int[] low, int[] dis, int parent, boolean[] isAP, int u) {
		
		int children = 0;
		
		visited[u] = true;
		
		low[u] = dis[u] = ++time;
		
		
		Iterator<Integer> it = adj[u].listIterator();
		while(it.hasNext()) {
			int v = it.next();
			
			if(!visited[v]) { //if not visited take low[v]
				children++;
				APUtil(visited, low, dis, u, isAP, v);
				
				low[u] = Math.min(low[u], low[v]); // if the subtree rooted with v has a connection to one of the ancestors of u - backedge
				
				if(parent != -1 && low[v] >= dis[u]) {
					isAP[u] = true;
				}
			} else if (v != parent) { //if visited, take dis[v]
				low[u] = Math.min(low[u], dis[v]);
			}
		}
		if(parent == -1 && children > 1) { //root
			isAP[u] = true;
		}
		
	}
	
	public static void main(String[] args) {
	
		APGraph g = new APGraph(5); //5 vertices=n
		g.addEdge(1, 0);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(0, 3);
		g.addEdge(3, 4);
		
		g.AP();
		

	}

}

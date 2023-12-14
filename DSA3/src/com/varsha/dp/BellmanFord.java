package com.varsha.dp;

import java.util.ArrayList;
import java.util.Arrays;

//Single source shortest path algo - work for -ve edge weights too unlike Dijkstra's algo
//method: we find shortes distance form source to all vertices by relaxing all edges n-1 times , n=|V|
//doesn't work if graph contains -ve weight cycle - so used to find if a graph contains -ve weight cycle - by relaxing n times
//O(V*E)

public class BellmanFord {

	private ArrayList<Edge> edges;
	private int V;
	private int E;
	private int[] dist;
	
	public BellmanFord(int V, int E) {
		this.V = V;
		this.E = E;
		edges = new ArrayList<Edge>(E);
		dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
	}
	
	static class Edge {
		int src;
		int dst;
		int weight;
		public Edge(int src, int dst, int weight) {
			this.src = src;
			this.dst = dst;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) {
		
		BellmanFord sssp = new BellmanFord(7, 10); //V=7, E=10 no. of vertices and edges

		sssp.edges.add(new Edge(1, 2, 6));
		sssp.edges.add(new Edge(1, 3, 5));
		sssp.edges.add(new Edge(1, 4, 5));
		sssp.edges.add(new Edge(2, 5, -1));
		sssp.edges.add(new Edge(3, 2, -2));
		sssp.edges.add(new Edge(3, 5, 1));
		sssp.edges.add(new Edge(4, 3, -2));
		sssp.edges.add(new Edge(4, 6, -1));
		sssp.edges.add(new Edge(5, 7, 3));
		sssp.edges.add(new Edge(6, 7, 3));
		
		sssp.findSSSP(1); //src-->1
	}

	public void findSSSP(int src) {
		
		dist[src] = 0; //1->1=0
		
		for(int i=2; i<=V; i++) { //n-1 times relaxation of all edges
			for(Edge e: edges) { //relax all edges 
				int u = e.src;
				int v = e.dst;
				
				if(dist[u]+e.weight < dist[v]) {
					dist[v] = dist[u]+e.weight;
				}
			}
		}
		printDist();
		
		//negative weight cycle -- just relax all edges once more - that's it - if we find the dist shorter, then -ve weight cycle exists
		for(Edge e : edges) {
			int u = e.src;
			int v = e.dst;
			if(dist[u]+e.weight < dist[v]) {
				System.out.println("Negative weight cycle exists in this graph.");
				return;
			}
		}		
	}

	public void printDist() {
		
		for(int i = 1; i<=V; i++) {
			System.out.println(i + "-->" + dist[i]);
		}
	}

}

package com.varsha.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * If dist[v] > dist[u] + weight(u, v)
               (i) Update distance of v, i.e., do
                     dist[v] = dist[u] + weight(u, v)
               (i) If v is in set, update its distance
                   in set by removing it first, then
                   inserting with new distance
               (ii) If v is not in set, then insert
                    it in set with new distance
 */

//Time Complexity = O(E logV) using Set DS to implement Dijkstra's algo

public class DijkstrasSet {

	private int n;
	private ArrayList<ArrayList<Node>> adjList;
	
	static class Node {
		int vertex;
		int weight;
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	public DijkstrasSet(int n) {
		this.n = n;
		adjList = new ArrayList<>(n);
		for(int i=0;i<n;i++) {
			//adjList.add(new PriorityQueue<Node>((a,b)-> {return a.weight-b.weight;}));
			adjList.add(new ArrayList<Node>());
		}
	}
	
	public void addEdge(int src, int dst, int weight) {
		adjList.get(src).add(new Node(dst, weight));
		adjList.get(dst).add(new Node(src, weight));
	}
	
	public static void main(String[] args) {
		DijkstrasSet dij = new DijkstrasSet(9);
		dij.addEdge(0, 1, 4);
		dij.addEdge(0, 7, 8);
		dij.addEdge(1, 2, 8);
		dij.addEdge(1, 7, 11);
		dij.addEdge(2, 3, 7);
		dij.addEdge(2, 5, 4);
		dij.addEdge(2, 8, 2);
		dij.addEdge(3, 5, 14);
		dij.addEdge(3, 4, 9);
		dij.addEdge(5, 6, 2);
		dij.addEdge(6, 7, 1);
		dij.addEdge(6, 8, 6);
		dij.addEdge(7, 8, 7);
		dij.addEdge(4, 5, 10);
		
		dij.dijkstrasUsingSet(0); //source=0 vertex
	}

	public void dijkstrasUsingSet(int src) {
		
		int[] dist = new int[n];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		Set<Node> set = new HashSet<Node>();
		dist[src] = 0;
		set.add(new Node(src, dist[src])); //0,0
		
		while(!set.isEmpty()) {
			Node u = set.iterator().next();
			set.remove(u);
			int uVertex = u.vertex;
			
			for(Node v: adjList.get(uVertex)) {
				int vVertex = v.vertex;
				if(dist[uVertex]+v.weight < dist[vVertex]) {
					//if vVertex already present remove and then add with updated distance --> thsi si the only difference in Set
					//when comapred to prirotityqueue - minheap
					set.remove(vVertex);
					dist[vVertex] = dist[uVertex] + v.weight;
					set.add(new Node(vVertex,v.weight));
				}
			}
			
		}
		printDist(dist);
	}

	public void printDist(int[] dist) {
		System.out.println("Single Sourec Shortest Path suing Dijkstra's algo - Set DS:");
		for(int i=0; i<n; i++) {
			System.out.println(i+ "-- "+ dist[i]);
		}
	}

}

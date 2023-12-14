package com.varsha.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//almost same as Prim's - using PriorityQueue - min Heap DS - Time Complexity= O(E logV)
//single source shortest path - from source, we find shortest distance to eacha nd eevry vertex in a graph - dirceted/undirected graphs 
//with positive edges
// for an edge (u, v)--> if d(u)+c(u, v) < d(v) then d(v)=d(u)+c(u, v)
/*
 * If dist[v] > dist[u] + weight(u, v)
               (i) Update distance of v, i.e., do
                     dist[v] = dist[u] + weight(u, v)
               (ii) Insert v into the pq (Even if v is
                    already there)
 */

//Using prirotity queue, we insert the node to Q even if its already there - eg: (5, 10) is already in Q, if w efind dist(5)=8 in 
//anothe riteration, we update d(5) as 8 and add to Q - (5,8). so even when 5,8 <5,10 we will iterate 5,10 too later
//this doesn;t happen in set - if set already has a pair, we remove it and then insert - so iteration will be avoided so betetr compared to
//Prirotiy queue but almost same since deletion also takes logn in set/heap = O(E logV).

public class Dijkstras {

	private int n;
	private ArrayList<ArrayList<Node>> adjList; //list of list of nodes
	
	static class Node {
		int vertex;
		int weight;
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	public Dijkstras(int n) {
		this.n = n;
		adjList = new ArrayList<>(n);
		for(int i=0; i<n; i++) {
			adjList.add(new ArrayList<Node>());
		}
	}
	
	public void addEdge(int src, int dst, int weight) { //undirected edges src-dst & dst-src
		adjList.get(src).add(new Node(dst, weight));
		adjList.get(dst).add(new Node(src, weight));
	}
	
	public static void main(String[] args) {
		
		Dijkstras dij = new Dijkstras(9);
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
		
		dij.dijkstras(0); //source vertex=0
	}

	public void dijkstras(int src) {
		
		int[] dist = new int[n];
		
		PriorityQueue<Node> minHeap = new PriorityQueue<Node>((a, b) -> {return a.weight-b.weight;});
		
		//Initialize - dist of all nodes to infinity
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[src] = 0; //source vertex dist from itself=0, add it to minheap
		minHeap.add(new Node(src, dist[src]));
		
		while(!minHeap.isEmpty()) { //get least min - poll, traverse all adj vertices and update min distance, & add to heap
			Node u = minHeap.poll();
			int uVertex = u.vertex;
			
			for(Node v: adjList.get(uVertex)) {
				int vVertex = v.vertex;
				if(dist[uVertex]+v.weight < dist[vVertex]) {
					dist[vVertex] = dist[uVertex]+v.weight;
					minHeap.add(new Node(vVertex, v.weight));
				}
			}
		}
		printDist(dist);
	}

	public void printDist(int[] dist) {
		System.out.println("Single Source Shortest Path using Dijkstra's Algo: ");
		for(int i=0; i<n; i++) {
			System.out.println(i+"-->"+ dist[i]);
		}
		
	}

}

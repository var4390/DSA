package com.varsha.greedy.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Create a Min Heap of size V where V is the number of vertices in the given graph. Every node of min heap contains vertex number and key value of the vertex. 
Initialize Min Heap with first vertex as root (the key value assigned to first vertex is 0). The key value assigned to all other vertices is INF (infinite). 
While Min Heap is not empty, do following 
Extract the min value node from Min Heap. Let the extracted vertex be u. 
For every adjacent vertex v of u, check if v is in Min Heap (not yet included in MST). If v is in Min Heap and its key value is more than weight of u-v, then 
update the key value of v as weight of u-v.
 */
//Time Complexity = O(E logE)

public class Prims {

	private int n;
	private ArrayList<ArrayList<Node>> adjList; //list of list of nodes - each list will have list of nodes adjacent to it
	
	public Prims(int n) {
		this.n = n;
		adjList = new ArrayList<>(n);
		for(int i=0; i<n;i++) {
			adjList.add(new ArrayList<>());
		}
	}
	
	public void addEdge(int src, int dst, int weight) { //undirected edges src-dst & dst-src
		adjList.get(src).add(new Node(dst, weight));
		adjList.get(dst).add(new Node(src, weight));
	}
	
	static class Node {
		int dst; //its just vertex no.
		int weight; //edge cost
		public Node(int dst, int weight) {
			this.dst = dst;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) {
		
		Prims prims = new Prims(9);
		prims.addEdge(0, 1, 4);
		prims.addEdge(0, 7, 8);
		prims.addEdge(1, 2, 8);
		prims.addEdge(1, 7, 11);
		prims.addEdge(2, 3, 7);
		prims.addEdge(2, 5, 4);
		prims.addEdge(2, 8, 2);
		prims.addEdge(3, 5, 14);
		prims.addEdge(3, 4, 9);
		prims.addEdge(5, 6, 2);
		prims.addEdge(6, 7, 1);
		prims.addEdge(6, 8, 6);
		prims.addEdge(7, 8, 7);
		prims.addEdge(4, 5, 10);
		
		prims.primsMST();
		
	}

	public void primsMST() {
		
		boolean[] isMST = new boolean[n]; //T/F for each node
		int[] key = new int[n]; //key is weight of each node
		int[] parent = new int[n]; //parent of each node to print the MST --> edge= src(parent)-dst
		
		PriorityQueue<Node> minHeap = new PriorityQueue<Node>((a, b) -> {return a.weight-b.weight;}); //based on weights- poll returns least min element always
		
		//initialize key with infinity & parent as -1 & isMST as false for all nodes
		Arrays.fill(isMST, false);
		Arrays.fill(key, Integer.MAX_VALUE);
		Arrays.fill(parent, -1);
		
		//choose source = vertex = 0, its key/weight will be 0 and add to heap
		key[0] = 0;
		minHeap.add(new Node(0, key[0]));
		
		while(!minHeap.isEmpty()) { //while Q is not empty, poll min node and add to MST, gets its adj nodes, if not in MST, and weight<its key then add to heap
			Node u = minHeap.poll();
			int uVertex = u.dst;
			isMST[uVertex] = true; //
			
			for(Node v: adjList.get(uVertex)) { //adj nodes of u vertex - check each and add to heap if weight<key & if not in MST
				int vVertex = v.dst;
				int weight = v.weight;
				
				if(!isMST[vVertex] && weight < key[vVertex]) {
					key[vVertex] = weight;
					parent[vVertex] = uVertex;
					minHeap.add(new Node(vVertex, key[vVertex]));
				}
			}
		}
		
		printMST(parent, key);
	}

	public void printMST(int[] parent, int[] key) {
		int minCost = 0;
		System.out.println("Minimum Cost ST using Prim's algo: ");
		for(int i = 1; i<n; i++) {
			System.out.println(parent[i]+ "--"+i);
			minCost+= key[i];
		}
		System.out.println(minCost);
	}

}

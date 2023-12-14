package com.varsha.greedy.mst;

import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//MST --> is a subgraph of weighted connected undirected graph with all vertices connected together such that it doesn;t form a cycle
//with minimum cost = Minimum cost Spanning Tree - 2algos - prim's & Kruskal's
//Kruskal's algo : always find least edge and add it to MST as long as it doesn't form cycle - repeat this until |V|-1 edges are in MST
//how to find if adding anedge forms a cycle - Disjoint Set DS - we already know find & union methods - union is done only if no cycle formed
//so use Disjoint Set DS 
//Time complexity = O(E log E) or O(E log V) --> to sort alledges = ElogE + unionfind for all edges <n-1 = E*4alpha = O(ElogE+E) = O(ElogE)

public class Kruskals {

	//Each edge has source, destination vertices and weight/cost for the edge
	static class Edge {
		int src;
		int dest;
		int weight;
		
		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}
	
	//each node of a set will have parent and rank associated with it
	static class DisjointSet {
		int parent;
		int rank;
		
		public DisjointSet(int parent, int rank) {
			this.parent = parent;
			this.rank = rank;
		}
	}
	
	public static void main(String[] args) {
		
		int V = 4; //no.of vertices
		//construct the graph
		ArrayList<Edge> list = new ArrayList<Edge>(List.of(new Edge(0, 1, 10), new Edge(0, 2, 6), new Edge(0, 3, 5), 
				new Edge(1, 3, 15), new Edge(2, 3, 4)));
		
		//sort by edge weights
		Collections.sort(list, (a, b) -> {return a.weight-b.weight;});
		
		Kruskals k = new Kruskals();
		k.kruskals(list, V);
		
	}

	public void kruskals(ArrayList<Edge> list, int n) {

		//get 1st min edge and check if by adding it will form a cycle or not, if not, add it to MST 
		//noOfEdges = n-1 in MST 
		int j = 0;
		int noOfEdges = 0;
		int minCost = 0;
		
		Edge[] results = new Edge[n]; //edges added in MST
		DisjointSet[] subSets = new DisjointSet[n]; //to hold disjoint of all nodes=n
		
		//initialize disjoint set DS for each node
		for(int i =0; i< n; i++) {
			subSets[i] = new DisjointSet(i, 0); //each is its own parent, so parent=i and rank initially is 0 for all nodes
		}
		
		while(noOfEdges < n-1) {
			Edge edge = list.get(j); //least edge as list is sorted by weights in ascending order --- gives u the 1st min edge since j=0 for 1st iteration, to get next iterations increment j 
			//now i have least edge, use find & union to check if adding it forms a cycle or not
			int Ux = find(subSets, edge.src);
			int Uy = find(subSets, edge.dest);
			
			if(Ux != Uy) { //not same parent , so do union() & add the edge to MST
				results[noOfEdges] = edge;
				union(subSets, Ux, Uy);
				noOfEdges++;
			}
			j++; //to get next minimum edge in list
		}
		
		System.out.println("Edges added in MST: ");
		for(int i =0; i<noOfEdges; i++) {
			System.out.println(results[i].src+ "--"+ results[i].dest+"= "+ results[i].weight);
			minCost+=results[i].weight;
		}
		System.out.println("Minimum Cost of MST using Kruskal's: "+ minCost);
	}

	public int find(DisjointSet[] subSets, int x) {
		
		if(x == subSets[x].parent) { //parent is itself
			return x;
		}
		int Ux = find(subSets, subSets[x].parent); //find parent's parent recursively
		subSets[x].parent = Ux; //path compression
		
		return subSets[x].parent;
	}

	public void union(DisjointSet[] subSets, int Ux, int Uy) {
		
		if(subSets[Ux].rank < subSets[Uy].rank) {
			subSets[Ux].parent = Uy;
		} else if(subSets[Uy].rank < subSets[Ux].rank) {
			subSets[Uy].parent = Ux;
		} else {
			subSets[Uy].parent = Ux;
			subSets[Ux].rank++;
		}
		
	}
	
}

package com.varsha.unionfind;

import java.util.ArrayList;

//With path compression, Time Complexity = O(4a)~ O(1) --> constant time 4 alpha
//UnionByRank --> rank is more like level is checked, UnionBySize --> size=no.ofnodes is checked
public class DisjointSet{

	private int n;
	private ArrayList<Integer> rank = new ArrayList<Integer>();
	private ArrayList<Integer> parent = new ArrayList<Integer>();
	private ArrayList<Integer> size = new ArrayList<Integer>();
	
	public DisjointSet() {}
	
	public DisjointSet(int n) {
		this.n = n;
		for(int i =0; i<n; i++) {
			rank.add(0); //initially rank of all nodes =0
			parent.add(i);// initially parent of each node is itself
			size.add(1); //initially all nodes will have only 1 node - itself=1
		}
	}
	
	public static void main(String[] args) {
		
		DisjointSet set = new DisjointSet(5);
		
		/*set.unionByRank(0, 2);
		set.unionByRank(4, 2);
		set.unionByRank(3, 1);
		*/
		set.unionBySize(0, 2);
		set.unionBySize(4, 2);
		set.unionBySize(3, 1);
		
		//check if 4 is in same set as 0
		if(set.find(4) == set.find(0)) {
			System.out.println("Yes, both belongs to same set.");
		} else {
			System.out.println("No.");
		}
		//check if 1 is in same set as 0
		if(set.find(1) == set.find(0)) {
			System.out.println("Yes, both belongs to same set.");
		} else {
			System.out.println("No.");
		}
	}

	public int find(int x) { //find ultimate parent of x - main ancestor
		
		if(x == parent.get(x)) { //if x is its own parent, return it
			return x;
		}
		//get ultimate parent of x - Ux
		int Ux = find(parent.get(x));
		//do path compression
		parent.set(x, Ux);
		
		return parent.get(x);
	}

	public void unionByRank(int x, int y) {
		//find ultimate parents of x and y, whosever rank is higher, add lower rank's nodes to that node
		//if rank is equal, choose any one among them as ultimate parent and add othe rnodes to this and increment the rank
		
		int Ux = find(x);
		int Uy = find(y);
		
		if(Ux == Uy) { //both belongs to same parent - means are in same set, so discard
			return;
		}
		
		if(rank.get(Ux) < rank.get(Uy)) {
			parent.set(Ux, Uy); //make Ux's parent as Uy, rank remains same as Uy has higher rank than Ux
		} else if(rank.get(Uy) < rank.get(Ux)) {
			parent.set(Uy, Ux); //make Uy's parent as Ux, rank remains same as Ux has higher rank than Uy
		} else { //rank is same
			parent.set(Uy, Ux); //set Uy's parent as Ux --> add Ux to Uy, rank is incremented since rank was same, adding will increase size of tree
			int r = rank.get(Ux);
			rank.set(Ux, r+1);
		}
	}

	public void unionBySize(int x, int y) { //same as UnionByRank - just use size instead of rank to do union
		int Ux = find(x);
		int Uy = find(y);
		
		if(Ux == Uy) { //same parent - same set - discard
			return;
		}
		
		if(size.get(Ux) < size.get(Uy)) {
			parent.set(Ux, Uy);
			size.set(Uy, size.get(Ux)+size.get(Uy));
		} else {
			parent.set(Uy, Ux);
			size.set(Ux, size.get(Uy)+size.get(Ux));
		}
	}

}

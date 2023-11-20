package com.varsha.unionfind;

public class UF {

	private int[] id;
	
	public UF (int N) {
		//create array with N objects
		id = new int[N];
		
		//initialize array with index value - meaning each is its own component initially
		for(int i =0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	boolean connected(int p, int q) {
		//if values are same, they are connected
		return id[p] == id[q];
	}
	
	void union(int p, int q) {
		
		int pid = id[p];
		int qid = id[q];
		
		//traverse the whole array and assign values of id[q] to id[p]
		for(int i =0; i<id.length;i++) {
			if(id[i] == pid) {
				id[i] = qid;
			}
		}
	}
	
}

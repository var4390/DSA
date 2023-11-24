package com.varsha.backtracking;

import java.util.Stack;

public class SubsetSum {

	public static void findSubset(int[] set, int sum, int curr, Stack<Integer> subset) {
		
		//System.out.println("findSubset: "+ curr+ ", " +sum);
		
		if(sum == 0) {
			printSolution(subset);
			return;
		}
		
		if(curr == set.length) {
			//System.out.println("Length: "+ curr);
			return;
		}
		
		findSubset(set, sum, curr+1, subset);
		
		if(set[curr] <= sum) {
			System.out.println("Curr: "+curr);
			int push = subset.push(set[curr]);
			System.out.println("Pushed: "+ push);
			//subset.add(set[curr]);
			
			findSubset(set, sum-set[curr], curr+1, subset);
			
			int pop = subset.pop();
			System.out.println("Popped:"+ pop);
		}
		
	}
	
	public static void printSolution(Stack<Integer> subset) {
		for(int i =0; i<subset.size(); i++) {
			System.out.print(subset.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		int[] set = {1, 2, 3, 4};
		int sum = 6; // 1,2,3 & 2,4 
		
		Stack<Integer> subset = new Stack<Integer>();
		
		findSubset(set, sum, 0, subset);

	}

}

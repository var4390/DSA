package com.varsha.backtracking;

import java.util.Stack;

// 2 choices - either include current element and check recursively for next remaining items or exclude curr element & recursively check for remianing elements
// Time complexity = O(2^n), Space = O(n)
//base cases: 
//1.sum==0 --> print solution coz everytime we are subtracting when including element
//2. when complete array is traversed, return -> curr==set.length 

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
		
		// exclude current element
		findSubset(set, sum, curr+1, subset);
		
		if(set[curr] <= sum) {
			System.out.println("Curr: "+curr);
			int push = subset.push(set[curr]);
			System.out.println("Pushed: "+ push);
			//subset.add(set[curr]);
			//include curr element
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

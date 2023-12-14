package com.varsha.dp;

// Use 1D DP array instead of 2D DP array 
/*
 * For calculating the current row of the dp[] array we require only previous row, but if we start traversing the rows from right to 
 * left then it can be done with a single row only
 */

//Time = O(n*M), Space = O(W) --> since 1D array

public class KnapsackSpaceOptimized {

	public static void main(String[] args) {
		
		int M = 50;
		
		int[] P = {60, 100, 120};
		int[] W = {10, 20, 30};
		
		knapsack(P.length, M, P, W); //no. of objects = 3-->P.length

	}

	public static void knapsack(int n, int M, int[] P, int[] W) {
		
		int[] dp = new int[M+1];
		
		for(int i=1; i<=n; i++) {
			for(int w=M; w>=0; w--) { //right to left
				
				if(W[i-1] <= w) {
					dp[w] = Math.max(dp[w], dp[w-W[i-1]]+P[i-1]);
				}
			}
		}
		System.out.println("Max profit: "+ dp[M]);
	}

}

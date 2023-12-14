package com.varsha.dp;

import java.util.Arrays;

//Using DP Memoization - we use dp table - 2D array to cache/store results of recursive calls
//repeated calls - will use data from this table
//Time = O(n*M), Space = O(n*M)
//K[i][w] = Max{ K[i-1][w], k[i-1][w-W[i-1]]+P[i-1] }

public class KnapsackMemoization {
	
	public static void main(String[] args) {
		
		int M=50;
		int[] P = {60, 100, 120};
		int[] W = {10, 20, 30};
		int n = P.length;
		
		int[][] dp = new int[n+1][M+1];
		for(int[] a: dp) {
			Arrays.fill(a, -1);
		}
		
		int maxProfit = knapsack(dp, n, M, P, W) ;
		System.out.println(maxProfit);
	}

	public static int knapsack(int[][] dp, int n, int M, int[] P, int[] W) {
		
		if (n==0 || M==0) {
			return 0;
		}
		
		if(dp[n][M] != -1) {
			return dp[n][M];
		}
		
		if(W[n-1] > M) {
			return dp[n][M] = knapsack(dp, n-1, M, P, W);
		} else {
			return dp[n][M] = Math.max(knapsack(dp, n-1, M, P, W), knapsack(dp, n-1, M-W[n-1], P, W)+P[n-1] );
		}
	}

}

package com.varsha.dp;

import java.lang.reflect.Array;
import java.util.Arrays;

//MCM - Matrix Chain Multiplication - Using Memization - use recursion aand cache results of recursive calls in dp:
//O(n^3)
//m[i][j] = min{ m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j]}, k>=i & k<j --> Formula used


public class MCMMemoization {

	private static int n;
	private static int[][] dp;
	private static int[][] s;
	
	public MCMMemoization(int n) {
		this.n = n;
		dp = new int[n][n];
		s = new int[n][n];
		for(int[] arr: dp) {
			Arrays.fill(arr, -1); //fill elements of dp with -1=initial value --> means not computed
		}
		for(int[] a: s) {
			Arrays.fill(a, 0);
		}
	}
	
	public static void main(String[] args) {
		
		int[] p = {1, 2, 3, 4}; // 1x2,2x3,3x4 --> min no. of multiplications to multiple these 3 matrices = m[1][3]
		
		MCMMemoization mcm = new MCMMemoization(p.length); //no.of matrices+1 
		
		int cost = mcm.mcm(p, 1, n-1); //dimensions=p, i=1, j=3 --> we need m[1][3]
		
		System.out.println(cost);
		
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				System.out.print(dp[i][j]+ " ");
			}System.out.println();
		}
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				System.out.print(s[i][j]+ " ");
			}System.out.println();
		}
		
	}

	public int mcm(int[] p, int i, int j) {
		
		//base conditions i==j & if already computed means != -1,s ome value is stored so return that.. else compute it and return that
		if(i == j) { 
			return 0;
		}
		if(dp[i][j] != -1) {
			return dp[i][j];
		}
		dp[i][j] = Integer.MAX_VALUE;
		for(int k =i ; k<j; k++) {//use the formula
			dp[i][j] = Math.min(dp[i][j], mcm(p, i, k)+mcm(p, k+1, j)+p[i-1]*p[k]*p[j]);
			s[i][j] = k;
		}
		
		return dp[i][j];
	}

}

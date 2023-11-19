package com.varsha.dp;

public class LCSMemoization {

	public int lcs(String s1, String s2, int m, int n, int[][] dp) {
		
		if(m == 0 || n == 0 ) {
			return 0; 
		}
		
		//if value already in table, just retrun that, else compute it
		if(dp[m][n] != -1) {
			return dp[m][n];
		}
		
		if(s1.charAt(m-1) == s2.charAt(n-1)) {
			dp[m][n] = 1+lcs(s1, s2, m-1, n-1, dp);
			return dp[m][n]; 
		} else {
			dp[m][n] = Math.max(lcs(s1, s2, m-1, n, dp), lcs(s1, s2, m, n-1, dp));
			return dp[m][n];
		}
		
	}
	
	public static void main(String[] args) {
		
		LCSMemoization lcs = new LCSMemoization();
		
		String s1 = "bd";
		String s2 = "abcd";
		
		int m = s1.length();
		int n = s2.length();
		
		int[][] dp = new int[m+1][n+1];
		
		//lookup table initialization - to store results of recursive calls
		for(int i =0; i<=m;i++) {
			for(int j = 0; j<=n; j++) {
				dp[i][j] = -1;
			}
		}
		
		System.out.println("Length of LCS: "+ lcs.lcs(s1, s2, m, n, dp));
		
		
	}

}

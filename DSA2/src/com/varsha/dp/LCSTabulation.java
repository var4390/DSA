package com.varsha.dp;

public class LCSTabulation {

	public static void main(String[] args) {
		
		String s1 = "bd";
		String s2 = "abcd";
		
		int m = s1.length();
		int n = s2.length();
		
		System.out.println("Length of LCS: "+ LCS(s1, s2, m, n));

	}

	public static int LCS(String s1, String s2, int m, int n) {
		
		int[][] L = new int[m+1][n+1];
		
		for(int i=0; i <= m; i++) {
			for(int j =0; j <= n; j++) {
				
				if(i == 0 || j == 0) { //1st row and 1st column = 0
					L[i][j] = 0;
				} else if (s1.charAt(i-1) == s2.charAt(j-1)) { //if character matches, take diagonal value +1
					L[i][j] = L[i-1][j-1] + 1;
				} else {
					L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
				}
				
			}
		}
		
		return L[m][n];
	}

}

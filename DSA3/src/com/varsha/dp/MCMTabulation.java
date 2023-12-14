package com.varsha.dp;

import java.util.Arrays;

//Using tabulation - Iteration approach 
//O(n^3)
//m[i][j] = min{m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j]}, k>=i & k<j

public class MCMTabulation {

	private static int n;
	private static int[][] m;
	private static int[][] s;
	private static char name = 'A';
	
	public MCMTabulation(int n) {
		this.n = n;
		m = new int[n][n];
		s = new int[n][n];
		for(int[] a: m) {
			Arrays.fill(a, 0);
		}
		for(int[] a: s) {
			Arrays.fill(a, 0);
		}
	}
	
	public static void main(String[] args) {
		
		int[] p = {1, 2, 3, 4};
		
		MCMTabulation mcm = new MCMTabulation(p.length);
		System.out.println(mcm.mcm(p));
		
		System.out.println("Cost matrix:");
		for(int i =0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(m[i][j]+ " ");
			}System.out.println();
		}
		System.out.println();
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				System.out.print(s[i][j]+ " ");
			}System.out.println();
		}
		//System.out.print(name);
		mcm.parenthesization(1, n-1);
	}

	public int mcm(int[] p) {
		
		int j;
		for(int i=0; i<n; i++) {
			m[i][i] = 0;
		}
		
		for(int d = 1; d <= n-1; d++) {
			for(int i = 1; i < n-d; i++) {
				j = i+d;
				m[i][j] = Integer.MAX_VALUE;
				for(int k=i; k<j;k++) {
					m[i][j] = Math.min(m[i][j], m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j]);
					s[i][j] = k;
				}
				
			}
		}
		return m[1][n-1];
	}
	
	public void parenthesization(int i, int j) {
		if(i == j) { //only 1matrix so print
			System.out.print(name++); 
			return;
		}
		
		System.out.print("(");
		parenthesization(i, s[i][j]); //recursively put brackets from i to s[i][j]
		parenthesization(s[i][j]+1, j); //recursively put brackets from s[i][j]+1 to j
		System.out.print(")");
	}

}

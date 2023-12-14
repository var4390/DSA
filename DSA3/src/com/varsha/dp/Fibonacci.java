package com.varsha.dp;

//Fibonacci nos: 0, 1, 1, 2, 3, 5, 8, 13, 21, .....
//Without using DP, Fib(n) = fib(n-2)+fib(n-1) if n>1; else return n; --> Time Complexity = O(2^n)
//using DP, Time complexity = O(n) --> exponential to polynomial 

public class Fibonacci {

	public static void main(String[] args) {
		
		int n = 5;
		System.out.println("Fib(5) without DP: "+ fib(n));
		System.out.println("Fib(5) using DP: "+ fibDP(n));
		
	}

	private static int fib(int n) {
		
		if(n <= 1) {
			return n;
		} else {
			return fib(n-2)+fib(n-1);
		}
	}
	
	private static int fibDP(int n) {
		
		int[] F = new int[n+1];
		F[0] = 0; F[1] = 1;
		
		for(int i =2; i<=n; i++) {
			F[i] = F[i-2] + F[i-1]; 
		}
		
		return F[n];
	}

}

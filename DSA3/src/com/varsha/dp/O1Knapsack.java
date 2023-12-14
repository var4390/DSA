package com.varsha.dp;

//Time complexity = O(n*W)
//Space Complexity = O(n*w)
//Using DP Tabulation approach

//Formula - K[i][w] = Max{K[i-1][w], K[i-1][w-W[i-1]+P[i-1]}

public class O1Knapsack {

	public static void main(String[] args) {
		
		int P[] = {1, 2, 5, 6};
		int W[] = {2, 3, 4, 5};

		int M = 8;
		int n = 4;
		
		int K[][] = new int[n+1][M+1]; //n--> 0 to 4 so 5, M->0 to 8, so 9
		
		for (int i = 0; i <= n; i++) {
			
			for(int w = 0; w <= M; w++) {
				
				if (i == 0 || w == 0) {
					K[i][w] = 0;
				} else if(W[i-1] <= w) { //if weight is less then compute the value
					K[i][w] = Math.max(K[i-1][w], K[i-1][w-W[i-1]] + P[i-1]);
				} else { // if weight is small, copy previous values
					K[i][w] = K[i-1][w];
				}

			}
			
		}
		System.out.println("Optimal - Max profit: "+ K[n][M]);
		
		int i = n, j = K[n][M]; int w = M;
		while (i > 0 && j >=0) {
			if (j == K[i-1][w]) {
				System.out.println(i + " is not included in Knapsack.");
				i--;
			} else {
				System.out.println(i + " is included in Knapsack.");
				j = j - P[i-1]; //subtract profit
				i--;
				w = M - W[i-1]; //subtract weight
			}
		}
		System.out.println();
		//simple bitwise - << - left shift --> val = val*2^n ; n is RHS, val=LHS
		System.out.println((5<<1) +", " + (5<<2) + ", "+( 1<<1)+", " +(1<<5));
		String s1 = "abcdefghij";
		CharSequence str = s1.subSequence(0, s1.length()-1);
		System.out.println(str);
	}

}

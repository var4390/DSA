package com.varsha.dp;

public class TSP {
	
	static int n = 4;
	static int dp[][] = new int[(int) Math.pow(2, n)][n];
	
	public static void main(String[] args) {

		for(int i =0; i<(1<<n); i++) {
			for(int j = 0; j<n;j++) {
				dp[i][j] = -1;
			}
		}
		
		int cost = tsp(1, 0);
		System.out.println("Cost: "+ cost);
	}

	public static int tsp(int mask, int pos) {
		
		int ans = 99999;
		
		int[][] d = {{0, 10, 15, 20},{5, 0, 9, 10},{6, 13, 0, 12},{8, 8, 9, 0}};

		int VISITED_ALL = (1<<n) - 1; //1111 = 16-1=15

		if(mask == VISITED_ALL) { //all cities visited so traverse back to source - current position to source=0
			return d[pos][0];
		}
		//lookup
		if(dp[mask][pos] != -1) {
			return dp[mask][pos];
		}
		
		//visit all cities
		for(int city=0; city < n; city++) {
			
			if((mask & (1<<city)) == 0) { //not visited, so visit and call it recursively
				int newAns = d[pos][city]+tsp(mask|(1<<city), city); //visit city and call tsp
				ans = Math.min(ans, newAns);
			}

		}

		return dp[mask][pos] = ans;
	}

}

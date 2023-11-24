package com.varsha.backtracking;

public class NQueens {

	private int n; //4*4 chessboard
	private int[][] Q;
	
	public NQueens(int n) {
		this.n = n;
		Q = new int[n][n];
		for(int i =0; i<n; i++) {
			for(int j = 0; j<n;j++) {
				Q[i][j] = 0;
			}
		}
	}
	
	public void printSolution() {
		for(int i=0; i<n;i++) {
			for(int j=0; j<n; j++) {
				if(Q[i][j] == 1) {
					System.out.print("Q ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
	}
	
	public void solveNQ() {
		
		boolean solExists = solveNQUtil(0); //start from 0th column
		if(solExists) {
			printSolution();
		} else {
			System.out.println("Solution does nto exist.");
		}
	}

	private boolean solveNQUtil(int col) {
		//we process row-wise for each column
		
		//base case - last col processed
		if(col >= n) {
			return true;
		}
		
		//check if Q can be placed in any row - i and col=0 initially
		for(int i = 0; i< n; i++) {
			//check if its safe - if it is -place the Q and proceed with next Q - col=1 etc , if not, remove the placement of Q and backtrack
			
			if(isSafe(i, col)) {
				
				Q[i][col] = 1;
				if(solveNQUtil(col+1) == true) {
					return true; //proceed with next Q 
				}
				// backtrack if above condition is false 
				Q[i][col] = 0;
			}
			
		}
		return false;
	}

	private boolean isSafe(int row, int col) {

		int i, j;
		
		//left side check - same row
		for(i=0; i<n; i++) {
			if(Q[row][i] == 1) {
				return false;
			}
		}
		
		//left side - upper diagonal check
		for(i=row, j=col; i>=0 && j>=0; i--, j--) {
			if(Q[i][j] == 1) {
				return false;
			}
		}
		
		//left side - lower diagonal check
		for(i=row, j=col; i<n && j>=0; i++, j--) {
			if(Q[i][j] == 1) {
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		
		int n = 4;
		NQueens q = new NQueens(n);
		
		q.solveNQ();
		
	}

	

}

package com.varsha.backtracking;

import java.util.ArrayList;

//print all possible solutions - mirror image too for NQueens problem
/*
 * A modification is that we can find whether we have a previously placed queen in a column or in left diagonal or in right diagonal in O(1) time. We can observe that 
For all cells in a particular left diagonal , their row + col  = constant.
For all cells in a particular right diagonal, their row – col + n – 1 = constant.
 */
//Time = O(n!)
//Space = O(n^2)

public class NQueensAllSolns {

	private int n;
	private static ArrayList<ArrayList<Integer>> result;
	private boolean[] cols;
	private boolean[] leftDiagonal;
	private boolean[] rightDiagonal;
	
	public NQueensAllSolns(int n) {
		this.n = n;
		result = new ArrayList<>();
		cols = new boolean[n];
		leftDiagonal = new boolean[2*n];
		rightDiagonal = new boolean[2*n];
	}
	
	public static void main(String[] args) {
		
		NQueensAllSolns nQueens = new NQueensAllSolns(4);
		nQueens.solveNQ();
		System.out.println(result);
	}

	private void solveNQ() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i=0; i<n; i++) {
			temp.add(0);
		}
		solveNQUtil(0, temp); //row=0
	}

	private void solveNQUtil(int row, ArrayList<Integer> comb) {
		//base case
		if(row >= n) {
			result.add(new ArrayList<Integer>(comb));
		}
		
		for(int col=0; col<n; col++) {
			
			if(cols[col] || leftDiagonal[row+col] || rightDiagonal[row-col+n]) {
				continue;
			}
			
			cols[col] = leftDiagonal[row+col] = rightDiagonal[row-col+n] = true;
			comb.set(col, row+1);
			solveNQUtil(row+1, comb);
			
			cols[col] = leftDiagonal[row+col] = rightDiagonal[row-col+n] = false;
		}		
	}

}

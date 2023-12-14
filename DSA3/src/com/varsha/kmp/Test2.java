package com.varsha.kmp;

//Java program for Naive Pattern Searching
//O(m*n)

public class Test2 {

	static void search(String pat, String txt)
	{
		int m = pat.length();
		int n = txt.length();
		int i = 0, j = m - 1;

		//both i and j are for txt string only i->j+1 gives u pattern characters to match with=4 chars
		//traverse until j<n --> end of txt
		
		while (j < n) { 

			String s = txt.substring(i, j + 1);
			System.out.println(s);
			if (pat.equals(s)) {
				System.out.println("Pattern found at index "
								+ i);
			}
			//slide window by 1 everytime if match or no match
			i++;
			j++;
		}
	}
	
	// Driver's code
	public static void main(String args[])
	{
		String txt = "AABAACAADAABAAABAA";
		String pat = "AABA";
	
		// Function call
		search(pat, txt);
	}
}


package com.varsha.rabinkarp;

public class RabinKarp {

	private String txt;
	private String pat;
	private int m;
	private int n;
	private int hashP;
	private int hashT;
	private int h; //pow(b, m-1)%q
	private int q; //mod no. usually a prime no.
	private static int b = 256; //ASCII - 2^8
	
	public RabinKarp(String txt, String pat, int q) {
		this.txt = txt;
		this.pat = pat;
		this.q = q;
		m = pat.length();
		n = txt.length();
		h = 1;
	}
	
	public void RKSearch() {
		
		int j = 0;
		
		//base - hash value for pattern and 1st substring of text
		for(int i = 0; i < m-1; i++) {
			h = (b * h) % q; //pow(b, m-1)%q
		}
		
		for(int i =0; i< m ; i++) {
			hashP = (b * hashP + pat.charAt(i)) % q;
			hashT = (b * hashT + txt.charAt(i)) % q;
		}

		for(int i =0; i <= n-m; i++) {
			
			//hash value matches - compare char by char
			if(hashP == hashT) {
				for (j = 0; j < m; j++) {
					if(pat.charAt(j) != txt.charAt(i+j)) {
						break;
					}
				}
				
				if(j == m) {
					System.out.println("Pattern found at index: "+ i);
				}
			}
			
			//hash value doesn't match
			if(i < n-m) {
				hashT = (b * (hashT - txt.charAt(i) * h) + txt.charAt(i+m)) % q;
				
				if(hashT < 0) {
					hashT+=q;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		String txt = "GEEKS FOR GEEKS";
		String pat = "GEEKS";
		int q = 101; //prime no. = mod
		
		RabinKarp rk = new RabinKarp(txt, pat, q);
		rk.RKSearch();

	}
}

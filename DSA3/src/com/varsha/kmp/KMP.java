package com.varsha.kmp;

//Time Complexity = O(m+n)
//Space = O(m)

public class KMP {

	private String txt;
	private String pat;
	private int n;
	private int m;
	private int[] lps; //lps array --> longest proper prefix that is also a suffix
	private int len; //length of lps for previous index
	
	public KMP(String txt, String pat) {
		this.txt= txt;
		this.pat = pat;
		n = txt.length();
		m = pat.length();
		lps = new int[m];
		lps[0] = 0; //lps[0] is always 0
		len = 0; //len =0 initially 
	}
	
	public void KMPSearch() {
		
		computeLPSArray();
		
		int i = 0, j = 0;
		
		while ((n-i) >= (m-j)) {
			if(pat.charAt(j) == txt.charAt(i)) { //char matches so increment both
				i++;
				j++;
			} 
			
			if(j == m) {
				System.out.println("Found pattern at index: "+ (i-j));
				j = lps[j-1];
			}
			
			if(i < n && pat.charAt(j) != txt.charAt(i)) {
				if(j != 0) { // Do not match lps[0..lps[j-1]] characters, they will match anyway
					j = lps[j-1];
				} else {
					i++;
				}
			}
		}
	}
	
	public void computeLPSArray() {
		int i = 1;
		
		while (i < m) {
			if(pat.charAt(len) == pat.charAt(i)) {
				len++;
				lps[i] = len;
				i++;;
			} else { //donot match
				if(len != 0) {
					len = lps[len-1];
				} 
				else { //len = 0
					lps[i] = len;
					i++;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		String txt = "ABABDABACDABABCABAB";
		String pat = "ABABCABAB";
		
		KMP kmp = new KMP(txt, pat);
		kmp.KMPSearch();
	}

}

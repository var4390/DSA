package com.varsha.search;

import java.util.Scanner;

public class LinearSearch {

	public static void main(String[] args) {
		
		int key = -1;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter no. of elements:");
		int n = scanner.nextInt();
		
		int[] a = new int[n];
		
		System.out.println("Enter "+ n + " elements: ");
		for(int i =0; i<n;i++) {
			if(scanner.hasNextInt()) {
				a[i] = scanner.nextInt();
			}
		}
		
		for(int e : a) {
			System.out.print(e+" ");
		}
		System.out.println();
		
		System.out.println("Enter key element to search: ");
		if(scanner.hasNextInt()) {
			key = scanner.nextInt();
		}
		
		//linear search
		for(int i =0; i<n; i++) {
			if(key == a[i]) {
				System.out.println("Key: "+ key+ " found at index: "+ i);
			}
		}

		scanner.close();
	}

}

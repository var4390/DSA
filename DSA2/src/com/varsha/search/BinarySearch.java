package com.varsha.search;

import java.util.Scanner;

public class BinarySearch {

	public static int BS(int[] a,int low, int high, int key) {
		
		if(low == high) {
			if(key==a[low]) {
				return low;
			} else {
				return -1;
			}
		}
		else {
			int mid = (int) Math.floor((low+high)/2);
			
			if(key == a[mid]) {
				return mid;
			} 
			if (key < a[mid]) {
				return BS(a, low, mid-1, key);
			} else {
				return BS(a, mid+1, high, key);
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		int n = 0, key = -1;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter no. of elements:");
		if(sc.hasNextInt()) {
			n = sc.nextInt();
		}
		
		int[] a = new int[n];
		
		System.out.println("Enter "+ n + " elements");
		for(int i =0; i<n;i++) {
			if(sc.hasNextInt()) {
				a[i] = sc.nextInt();
			}
		}
		
		for(int e:a) {
			System.out.print(e+ " ");
		} System.out.println();
		
		System.out.println("Enter key element to search:");
		if(sc.hasNextInt()) {
			key = sc.nextInt();
		}
		
		int index = BS(a, 0, n-1, key);
		System.out.println("Key found at index: "+index);
		
		sc.close();
	}

}

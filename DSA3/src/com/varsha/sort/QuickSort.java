package com.varsha.sort;

public class QuickSort {

	public static void main(String[] args) {
		
		int[] a = {10, 16, 8, 12, 15, 6, 3, 9, 5};
		
		QS(a, 0, a.length-1); //low=0, high=n-1

		for(int e: a) {
			System.out.print(e+ " ");
		} System.out.println();
	}

	public static void QS(int[] a, int l, int h) {
		//get partition index, then recursively QuickSort LHS & RHS
		if(l < h) {
			int p = Partition(a, l, h);
			QS(a, l, p-1);
			QS(a, p+1, h);
		}
	}

	public static int Partition(int[] a, int l, int h) {
		
		int i = l;
		int j = h;
		int pivot = a[l]; //choosing 1st element as pivot
		
		while(i < j) {
			
			while(a[i] <= pivot) {
				i++;
			}
			
			while(a[j] > pivot) {
				j--;
			} 
			
			if(i < j) { //swap i and j elements
				swap(a, i, j);
			}
		}
		
		//now, i has crossed j and i > j ==> so swap a[l] and a[j] and return j
		swap(a, l, j);
		
		return j;
	}
	
	public static void swap(int[] a, int e1, int e2) {
		int temp = a[e2];
		a[e2] = a[e1];
		a[e1] = temp;
		
	}
}

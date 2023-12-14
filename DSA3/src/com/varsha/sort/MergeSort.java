package com.varsha.sort;


//a single element in itself is sorted - abse case for recursion - it returns when base case is called.
// so, we call MS recursively dividing problems into sub problems until w ehave 1 element left in each list/array so
//here 8 elememnts - 8 lists/arrays - now w ehave to merge them together recursively - combining solutions of subproblems to obtain
//final sorted list

public class MergeSort {

	public static void main(String[] args) {
		
		int a[] = {9, 3, 7, 5, 6, 4, 8, 2};
		
		MergeSort(a, 0, a.length-1);
		
		for(int e: a) {
			System.out.print(e+ " ");
		} System.out.println();

	}

	public static void MergeSort(int[] a, int l, int h) {
		
		if(l < h) {
			int mid = (l+h)/2;
			MergeSort(a, l, mid);
			MergeSort(a, mid+1, h);
			Merge(a, l, mid, h);
		}
		return;
	}
	
	public static void Merge(int[] a, int l, int mid, int h) {
		
		System.out.println("Merging");
		
		int temp[] = new int[a.length];
		
		int left = l;
		int right = mid+1;
		int i = l;
		
		while(left <= mid && right <= h) {
			if(a[left] <= a[right]) {
				temp[i] = a[left]; 
				i++; left++;
			} else {
				temp[i] = a[right];
				i++; right++;
			}
		}
		
		while(left <= mid) {
			temp[i] = a[left];
			i++; left++;
		}
		
		while(right <= h) {
			temp[i] = a[right];
			i++; right++;
		}
		
		for(int j =l; j<=h;j++) {
			a[j] = temp[j];
		}
		
		
	}
	
}

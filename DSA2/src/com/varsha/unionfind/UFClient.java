package com.varsha.unionfind;

import java.util.Scanner;

public class UFClient {

	public static void main(String[] args) {
		
		System.out.println("Enter no. of objects:");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		System.out.println("No. of objects: "+ n);
		
		UF uf = new UF(n);
		
		
		while(scanner.hasNextInt()) {
			int p = scanner.nextInt();
			int q = scanner.nextInt();
			
			if(!uf.connected(p, q)) {
				uf.union(p, q);
				System.out.println("Connected: "+ p + " "+q);
			}
		}
		
	}

}

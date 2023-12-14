package com.varsha.dp.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	static class Node {
		int data;
		Node left;
		Node right;
		
		public Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}
	
	public static void main(String[] args) {
		
		//Binary Tree
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		
		for(Node n: q) {
			System.out.println("Only root is added to Quue 1st: "+n.data);
		}
		
		//now traverse it
		while(!q.isEmpty()) {
			Node temp = q.poll(); // removes head element - node
			System.out.println(temp.data + " ");
			
			//enqueue left child
			if(temp.left != null) {
				q.add(temp.left);
				System.out.println("added left ele");
			}
			if(temp.right != null) {
				q.add(temp.right);
				System.out.println("added right ele");
			}
		}

	}

}

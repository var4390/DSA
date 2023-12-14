package com.varsha.greedy;

import java.util.PriorityQueue;

//using MinHeap --> Time Complexity= O(nlogn)
//data compression technique - construct Huffman tree and trverse the tree to assign 0's to left and 1's to right
//and print them
/*
 * Input is an array of unique characters along with their frequency of occurrences and output is Huffman Tree. 

Create a leaf node for each unique character and build a min heap of all leaf nodes (Min Heap is used as a priority queue. 
The value of frequency field is used to compare two nodes in min heap. Initially, the least frequent character is at root)
Extract two nodes with the minimum frequency from the min heap.
 
Create a new internal node with a frequency equal to the sum of the two nodes frequencies. Make the first extracted node as its left child 
and the other extracted node as its right child. Add this node to the min heap.
Repeat steps#2 and #3 until the heap contains only one node. The remaining node is the root node and the tree is complete.
 */

class HuffmanNode {
	int data; //frequency
	char c; //character 
	HuffmanNode left;
	HuffmanNode right;
}

public class HuffmanEncoding {

	public static void main(String[] args) {
		
		char[] chars= {'a','b','c','d','e','f'};
		int[] data = {5, 9, 12, 13, 16, 45};

		PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<HuffmanNode>((a, b) -> {return a.data-b.data;});
		
		//construct minheap by adding all leaf nodes - all are leaf at 1st since we are just constructing tree now
		for(int i =0; i<chars.length; i++) {
			
			HuffmanNode node = new HuffmanNode();
			node.data = data[i];
			node.c = chars[i];
			node.left = null;
			node.right = null;
			
			//now add to minHeap
			minHeap.add(node);
		}
		
		HuffmanNode root = null;
		
		//build tree until you reach root=1 node - heap contains only 1 node
		while(minHeap.size() > 1) { //while many nodes, process them until only 1 node remaining - initially all leaf nodes are in minHeap = 6
			//since its minHeap, every deletion from the queue, gives you the least value - minimum
			
			//minHeap.peek(); //1st min
			HuffmanNode x = minHeap.poll();
			//minHeap.peek(); //2nd min
			HuffmanNode y = minHeap.poll();
			
			HuffmanNode internalNode = new HuffmanNode();
			internalNode.data = x.data+y.data; //add both frequenecy and assign to new node and make it as root of these 2 removed nodes, add it to heap.
			internalNode.c = '-';
			internalNode.left = x; 
			internalNode.right = y;
			
			root = internalNode;
			minHeap.add(internalNode);
		}
		//print by traversing the tree from root
		printCode(root, "");
	
	}

	public static void printCode(HuffmanNode root, String s) {
		
		//base case --> if leaf node, just print it
		if(root.left == null && root.right == null && Character.isLetter(root.c)) {
			System.out.println(root.c+ ": "+ s);
			return; //very impt to have this return when traversing recursivley like here - else it throws exception 
		}
		
		printCode(root.left, s+"0");
		printCode(root.right, s+"1");
	}

}

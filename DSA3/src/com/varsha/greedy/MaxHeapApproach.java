package com.varsha.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

//Using MaxHeap - Time Complexity=O(nlogn)
/*Sort the jobs based on their deadlines.
Iterate from the end and calculate the available slots between every two consecutive deadlines. Insert the profit, deadline, and job ID of ith job in the max heap.
While the slots are available and there are jobs left in the max heap, include the job ID with maximum profit and deadline in the result.
Sort the result array based on their deadlines.
 */

public class MaxHeapApproach {

	private char id;
	private int deadline, profit;
	
	public MaxHeapApproach() {}
	
	public MaxHeapApproach(char id, int deadline, int profit) {
		this.id = id;
		this.deadline = deadline;
		this.profit = profit;
	}
	
	public static void main(String[] args) {
		
		ArrayList<MaxHeapApproach> list = new ArrayList<>();
		list.add(new MaxHeapApproach('a', 2, 100));
		list.add(new MaxHeapApproach('b', 1, 19));
		list.add(new MaxHeapApproach('c', 2, 27));
		list.add(new MaxHeapApproach('d', 1, 25));
		list.add(new MaxHeapApproach('e', 3, 15));

		MaxHeapApproach m = new MaxHeapApproach();
		m.scheduleJobs(list);
		
	}

	public void scheduleJobs(ArrayList<MaxHeapApproach> list) {
		
		//to hold the results
		ArrayList<MaxHeapApproach> result = new ArrayList<MaxHeapApproach>();
		//maxheap based on priority
		PriorityQueue<MaxHeapApproach> maxHeap = new PriorityQueue<MaxHeapApproach>((a, b)-> { return b.profit-a.profit;});
		
		//sort the list by deadline
		Collections.sort(list, (a, b) -> {return a.deadline-b.deadline;});
		
		//start iteration from the end
		for(int i = list.size()-1; i>=0; i-- ) {
			
			int slot_available;
			
			if(i==0) { //if only 1 element, get its deadline
				slot_available = list.get(i).deadline;
			} else { //if not, find slot between 2 consecutive deadlines
				slot_available = list.get(i).deadline - list.get(i-1).deadline;
			}
			
			maxHeap.add(list.get(i)); //every time add the job to maxheap --> since its priortity queue, maxheap will always have max prirotity
			//in its root -so each time, u delete, highest priority ele is deleted.
			
			while(slot_available > 0 && maxHeap.size() > 0) {
				
				MaxHeapApproach job = maxHeap.remove();
				slot_available--;
				result.add(job);
				
			}
		}
		
		Collections.sort(result, (a, b) -> {return a.deadline - b.deadline;});
		for(MaxHeapApproach m: result) {
			System.out.print(m.id+ " ");
		} System.out.println();
		
	}

}

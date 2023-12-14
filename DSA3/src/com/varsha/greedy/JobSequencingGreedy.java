package com.varsha.greedy;

import java.util.ArrayList;
import java.util.Collections;

public class JobSequencingGreedy {

	private char id;
	private int profit, deadline;
	
	public JobSequencingGreedy() {
		
	}
	
	public JobSequencingGreedy(char id, int deadline, int profit) {
		this.id = id;
		this.deadline = deadline;
		this.profit = profit;
	}

	public static void main(String[] args) {
		
		ArrayList<JobSequencingGreedy> list = new ArrayList<>();
		list.add(new JobSequencingGreedy('a', 2, 100));
		list.add(new JobSequencingGreedy('b', 1, 19));
		list.add(new JobSequencingGreedy('c', 2, 27));
		list.add(new JobSequencingGreedy('d', 1, 25));
		list.add(new JobSequencingGreedy('e', 3, 15));
		
		System.out.println("Job Schedule: ");
		
		JobSequencingGreedy g = new JobSequencingGreedy();
		g.scheduleJobs(list, 3); // 3 is max deadline
	}

	public void scheduleJobs(ArrayList<JobSequencingGreedy> list, int t) {
		
		boolean[] timeSlot = new boolean[t];
		char[] job = new char[t];
		
		//sort the list by profit
		Collections.sort(list, (a, b) -> b.profit-a.profit);
		
		//iterate through all jobs
		for(int i = 0; i< list.size(); i++) {
			//find free slot and assign from last slot
			for(int j = Math.min(t-1, list.get(i).deadline - 1); j >=0; j--) {
				if(timeSlot[j] == false) {
					timeSlot[j] = true;
					job[j] = list.get(i).id;
					break;
				}
			}
		}
		
		for(char c: job) {
			System.out.print(c+ " ");
		} System.out.println();
	}

}

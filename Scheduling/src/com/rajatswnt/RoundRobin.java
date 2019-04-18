package com.rajatswnt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class RoundRobin {
	ArrayList<Process> processList = new ArrayList<>();
	int n;	// number of processes
	Scanner sc = new Scanner(System.in);
	int quantum;
	
	private void input() {
		System.out.println("Enter number of processes:");
		n = sc.nextInt();
		
		System.out.println("Enter quantum:");
		quantum = sc.nextInt();
		
		for(int i=0;i<n;i++) {
			System.out.println("Enter process name:");
			String name;
			int arr,bus;
			name = sc.next();
			System.out.println("Enter arrival time:");
			arr = sc.nextInt();
			System.out.println("Enter burst time:");
			bus = sc.nextInt();
			
			Process p = new Process(name, arr, bus);
			processList.add(p);
		}
	}
	
	private void schedule() {
		//int remain_time[] = new int[n];
		//int waiting_time[] = new int[n];
		int t;
		Vector<Integer>remain_time = new Vector<>();
		Vector<Integer>waiting_time = new Vector<>();
		
		int i=0;
		for (Iterator<Process> iterator = processList.iterator(); iterator.hasNext();) {
			Process process = (Process) iterator.next();
			//remain_time[i] = process.getBurstTime();
			remain_time.set(i, process.getBurstTime());
			i++;
		}
		t = 0;
		
		for(int i1=0 ; i1<n ; i1++)
			waiting_time.set(i1, 0);
			//waiting_time[i1] = 0;
		
		boolean complete[] = new boolean[n];
		for(int i1=0;i1<n;i1++)
			complete[i1] = false;
		
		for(int i1=0 ; i1<n;i1++) {
			if(complete[i1]==false) {
				if (/*remain_time[i1]*/remain_time.get(i1) > quantum) {
					t = t+quantum;
					//remain_time[i1] -=quantum;
					int temp = remain_time.get(i1);
					remain_time.set(i1, temp-quantum);
				}
				else if (remain_time.get(i1) == quantum) {
					t = t+quantum;
					//remain_time[i1] -=quantum;
					int temp = remain_time.get(i1);
					remain_time.set(i1, temp-quantum);
					complete[i1] = true;
				}
				else {
					//waiting_time[i1] = t;
					waiting_time.set(i1, t);
					// t = t+remain_time[i1];
					t = t+ remain_time.get(i1);
					remain_time.set(i1, 0);
					//remain_time[i1] = 0;
				}
			}
			else 
				continue;
		}
		// System.out.println(waiting_time.length);
		for(int i1=0 ; i1<n ; i1++)
			System.out.println("\nWaiting time["+i1+"] :"+waiting_time.get(i1));
			//System.out.println("\nWaiting time["+i+"] :"+waiting_time[i]);
	}
	
	public static void main(String[] args) {
		RoundRobin rr = new RoundRobin();
		rr.input();
		rr.schedule();
	}
}

package com.rajatswnt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Fcfs {
	ArrayList<Process> processList = new ArrayList<>();
	int n;	// number of processes
	Scanner sc = new Scanner(System.in);
	
	private void input() {
		System.out.println("Enter number of processes:");
		n = sc.nextInt();
		
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
		Collections.sort(processList);
		int temp=0;
		boolean flag = true;
		for (Iterator<Process> iterator = processList.iterator(); iterator.hasNext();) {
			Process process = (Process) iterator.next();
			if (flag) {
				process.setStartTime(process.getArrivalTime());
				temp = process.getBurstTime() + process.getArrivalTime();
				flag = false;
			}
			else {
				if(temp>process.getArrivalTime()) {
				process.setStartTime(temp);
				temp += process.getBurstTime();}
				
				else {
					process.setStartTime(process.getArrivalTime());
					temp = process.getArrivalTime() + process.getBurstTime();
				}
			}
		}
		
		for (Iterator<Process> iterator = processList.iterator(); iterator.hasNext();) {
			Process process = (Process) iterator.next();
			System.out.println(process.toString());
		}
	}
	
	public static void main(String[] args) {
		Fcfs fcfs = new Fcfs();
		fcfs.input();
		fcfs.schedule();
	}
}

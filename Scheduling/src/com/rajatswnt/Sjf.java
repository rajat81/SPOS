package com.rajatswnt;

import java.util.ArrayList;
import java.util.Scanner;

public class Sjf {
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
	
	private void findWaitingTime(int wt[]) {
		// TODO Auto-generated method stub
		int rt[] = new int[n];
		
		for(int i=0;i<n;i++) {
			rt[i] = processList.get(i).getBurstTime();
		}
		
		int completed = 0,t=0,minm = Integer.MAX_VALUE;
		int shortest = 0, finish_time;
		
		boolean check = false;
		
		while(completed!=n) {
			// find process with min rmaining time
			for(int i=0; i<n ;i++) {
				if (processList.get(i).getArrivalTime()<=t && rt[i]<minm && rt[i]>0) {
					minm = rt[i];
					shortest = i;
					check = true;
				}
			}
			
			if(check == false) {
				t++;
				continue;
			}
			
			rt[shortest]--;
			
			if (minm == 0) {
				minm = Integer.MAX_VALUE;
			}
			
			if (rt[shortest]==0) {
				completed++;
				check=false;
				
				finish_time = t+1;
				
				wt[shortest] = finish_time - processList.get(shortest).getBurstTime() - processList.get(shortest).getArrivalTime();
				
				if (wt[shortest]<0) {
					wt[shortest] = 0;
				}
			}
			t++;
		}
	}
	
	private void findTurnaroundTime(int wt[], int tat[]) {
		// TODO Auto-generated method stub
		for(int i=0;i<n;i++)
		tat[i] = processList.get(i).getBurstTime() + wt[i];
	}
	private void schedule() {
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0,total_tat = 0;
		
		findWaitingTime(wt);
		
		findTurnaroundTime(wt,tat);
		
		for (int i = 0; i < n; i++) { 
            total_wt = total_wt + wt[i]; 
            total_tat = total_tat + tat[i]; 
            System.out.println(" "  + "\t\t"
                             + processList.get(i).getBurstTime() + "\t\t " + wt[i] 
                             + "\t\t" + tat[i]);
		}
	}
	
	public static void main(String[] args) {
		Sjf sjf = new Sjf();
		sjf.input();
		sjf.schedule();
	}
}

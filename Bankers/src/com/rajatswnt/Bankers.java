package com.rajatswnt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Bankers{
	int n;	// no of processes
	int m;	// no of resources
	int[][] max;
	int[][] need;
	int[][]	alloc;
	Scanner sc = new Scanner(System.in);
	int available[];
	ArrayList<Integer> safeSeq = new ArrayList<>();
	
	public Bankers() {
		System.out.println("Enter no of processes:");
		n = sc.nextInt();
		
		System.out.println("Enter no of resources:");
		m = sc.nextInt();
		
		max = new int[n][m];
		need = new int[n][m];
		alloc = new int[n][m];
		
		available = new int[m];
	}
	
	private void input() {
		
		System.out.println("Enter max matrix:");
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				max[i][j] = sc.nextInt();
			}
		}
		
		System.out.println("Enter allocation matrix:");
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				alloc[i][j] = sc.nextInt();
			}
		}
		System.out.println("Enter available resource:");
		for(int i=0;i<m;i++) {
			available[i] = sc.nextInt();
		}
		
	}
	
	private void findNeed() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				need[i][j] = max[i][j] - alloc[i][j];
			}
		}
		System.out.println("Need matrix calculated...\n");
	}
	
	private void isSafe() {
		
		int[] work = new int[m];
		for(int i=0;i<m;i++) {
			work[i] = available[i];
		}
		System.out.println("work matrix initialized...\n");
		
		boolean[] finish = new boolean[n];
		for(int i=0;i<n;i++) {
			finish[i] = false;
		}
		System.out.println("finish matrix initialized...\n");
		
		int count = 0;
		
		while(count < n) {
			//boolean flag = false;
			
			for(int i=0; i<n; i++) {
				if (finish[i]==false) {
					boolean flag = false;
					for(int j=0;j<m;j++) {
						if(need[i][j]>work[j])
							{flag = true;
						break;}
					}
					if (flag == false) {
						safeSeq.add(i);
						for(int y=0; y<m ;y++)
							work[y] += alloc[i][y];
						finish[i] = true;
						count++;
					}
				}
			}
		}
		
		for (Iterator<Integer> iterator = safeSeq.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			System.out.println("P"+integer);
			
		}
	}
	
	public static void main(String[] args) {
		Bankers b = new Bankers();
		b.input();
		b.findNeed();
		b.isSafe();
	}
}
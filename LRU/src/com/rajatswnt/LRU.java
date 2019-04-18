package com.rajatswnt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class LRU {
	int frames, count;	// capacity
	int[] page;
	Scanner sc = new Scanner(System.in);
	
	LRU(){
		System.out.println("page.length :");
		int count = sc.nextInt();
		
		page = new int[count];
	}
	
	private void input() {
		
		System.out.println("frame capacity:");
		frames = sc.nextInt();
		
		System.out.println("Enter page sequence:");
		
		for(int i=0; i<page.length; i++) {
			page[i] = sc.nextInt();
		}

	}
	
	int pageFaults() {
		// to store the currently present pages
		HashSet<Integer> set = new HashSet<>(frames);
		
		// to find LRU page from set
		HashMap<Integer, Integer> index = new HashMap<>();
		
		int page_fault = 0;
		for (int i = 0; i < page.length; i++) {
			if (set.size() < frames) { 
				
				// if already contain the page
				if (set.contains(page[i])) {
					index.put(page[i], i);
				}
				else {
					set.add(page[i]);
					index.put(page[i], i);
					page_fault++;
				}
			}
			
			else	// if set is full 1. contains the page 2. doesn't contain the page
			{
				// doesn't contain the page
				if(!set.contains(page[i])) {
					int min_index = Integer.MAX_VALUE, val = 0;
					for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext();) {
						Integer integer = (Integer) iterator.next();
						if (index.get(integer) < min_index) {
							min_index =index.get(integer);
							val = integer;
						}
					}
					set.remove(val);
					
					set.add(page[i]);
					
					page_fault++;
					
				}					
				index.put(page[i], i);

			}
			
			System.out.println("\nSet after ith iteration:\n");
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();
				System.out.println(integer+", ");
			}
			
		}
		
		return page_fault;
	}
	
	public static void main(String[] args) {
		LRU lru = new LRU();
		lru.input();
		System.out.println("Page faults:"+lru.pageFaults());

	}

}

/*
 * 13
frame capacity:
4
Enter page sequence:
7
0
1
2
0
3
0
4
2
3
0
3
2

Set after ith iteration:

7, 

Set after ith iteration:

0, 
7, 

Set after ith iteration:

0, 
1, 
7, 

Set after ith iteration:

0, 
1, 
2, 
7, 

Set after ith iteration:

0, 
1, 
2, 
7, 

Set after ith iteration:

0, 
1, 
2, 
3, 

Set after ith iteration:

0, 
1, 
2, 
3, 

Set after ith iteration:

0, 
2, 
3, 
4, 

Set after ith iteration:

0, 
2, 
3, 
4, 

Set after ith iteration:

0, 
2, 
3, 
4, 

Set after ith iteration:

0, 
2, 
3, 
4, 

Set after ith iteration:

0, 
2, 
3, 
4, 

Set after ith iteration:

0, 
2, 
3, 
4, 
Page faults:6
*/
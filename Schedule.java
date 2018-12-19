import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class Schedule {
	
	//private LinkedList <Interval> chart;
	private int count;
	private ArrayList<Process>readyQueue,processQueue;
	
public Schedule() {
	readyQueue = new ArrayList<Process>();
	processQueue = new ArrayList<Process>();
	count = 0;
}
	public void getinfo(int type)
	{
		Process temp;
		System.out.println("Enter number of processes:");
		Scanner sc = new Scanner(System.in);
		count = sc.nextInt();
		for(int i = 0;i<count;i++){
			temp = new Process();
			temp.input(type);
			readyQueue.add(temp);
		}
		processQueue = readyQueue;
	}
	
	public void priorityScheduling()	// input: process id, burst time, priority
	{
		// sort Processes based on priorities 
		Collections.sort(processQueue);
		ArrayList<Integer> waitingTime = new ArrayList<Integer>();
		// Printing sorted processes
		/*
		 * for (Iterator iterator = processQueue.iterator(); iterator.hasNext();) {
			Process process = (Process) iterator.next();
			process.showInfo();
		}
		*/
		
		int timer = 0;
		for (Iterator iterator = processQueue.iterator(); iterator.hasNext();) 
		{
			Process process = (Process) iterator.next();
			process.startTime = timer;
			process.endTime = timer + process.bt;
			timer = process.endTime;
		}
		
		System.out.println("\n Gantt Chart :\n");
		for (Iterator iterator = processQueue.iterator(); iterator.hasNext();) {
			Process process = (Process) iterator.next();
			process.showInfo();
			waitingTime.add(process.getStartTime());
			System.out.println("\nWaiting time:"+process.getStartTime()+"\nTurn around time:"+(process.getStartTime()+process.bt));
		}
		
		//Finding avg waiting time
		int sum=0;
		for (Iterator iterator = waitingTime.iterator(); iterator.hasNext();) {
			sum = sum + (Integer) iterator.next();
			
		}
		
		System.out.println("Average Waiting time = "+(sum/count));
	}
	
	public void fcfs()			// input: process id,burst time,arrival time
	{
		ArrayList<Integer> waitingTime = new ArrayList<Integer>();
		
		int timer = 0;
		for (Iterator iterator = processQueue.iterator(); iterator.hasNext();) 
		{
			Process process = (Process) iterator.next();
			process.startTime = timer;
			process.endTime = timer + process.bt;
			timer = process.endTime;
		}
		
		System.out.println("\n Gantt Chart :\n");
		for (Iterator iterator = processQueue.iterator(); iterator.hasNext();) {
			Process process = (Process) iterator.next();
			process.showInfo();
			waitingTime.add(process.getStartTime()-process.at);
			System.out.println("\nWaiting time:"+(process.getStartTime()-process.at)+"\nTurn around time:"+(process.getStartTime()-process.at+process.bt));
		}
		
		//Finding avg waiting time
		int sum=0;
		for (Iterator iterator = waitingTime.iterator(); iterator.hasNext();) {
			sum = sum + (Integer) iterator.next();
			
		}
		
		System.out.println("\nAverage Waiting time = "+(sum/count));
	}
	
	public void roundRobin()
	{
		int timeSlice = 0;	// TimeSlice or TimeQuantum
		Scanner sc = new Scanner(System.in);
		timeSlice = sc.nextInt();
		sc.close();
		
		Collections.sort(processQueue,Process.arrivalTimeComparator);
		

	}
	
	
	public static void main(String[] args) {
		/*
		 * For Priority Scheduling
		Schedule schedule = new Schedule();
		schedule.getinfo(0);
		schedule.priorityScheduling();
		 */
		
		/*
		// For FCFS
		Schedule schedule = new Schedule();
		schedule.getinfo(1);
		schedule.fcfs();
		*/
		
		// For Round Robin
		Schedule schedule = new Schedule();
		schedule.getinfo(1);
		schedule.roundRobin();
		
	}
}

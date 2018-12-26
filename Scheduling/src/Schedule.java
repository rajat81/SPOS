import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
		sc.close();
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
		for (Iterator<Process> iterator = processQueue.iterator(); iterator.hasNext();) 
		{
			Process process = (Process) iterator.next();
			process.startTime = timer;
			process.endTime = timer + process.bt;
			timer = process.endTime;
		}
		
		System.out.println("\n Gantt Chart :\n");
		for (Iterator<Process> iterator = processQueue.iterator(); iterator.hasNext();) {
			Process process = (Process) iterator.next();
			process.showInfo();
			waitingTime.add(process.getStartTime());
			System.out.println("\nWaiting time:"+process.getStartTime()+"\nTurn around time:"+(process.getStartTime()+process.bt));
		}
		
		//Finding avg waiting time
		int sum=0;
		for (Iterator<Integer> iterator = waitingTime.iterator(); iterator.hasNext();) {
			sum = sum + (Integer) iterator.next();
			
		}
		
		System.out.println("Average Waiting time = "+(sum/count));
	}
	
	public void fcfs()			// input: process id,burst time,arrival time
	{
		ArrayList<Integer> waitingTime = new ArrayList<Integer>();
		
		int timer = 0;
		for (Iterator<Process> iterator = processQueue.iterator(); iterator.hasNext();) 
		{
			Process process = (Process) iterator.next();
			process.startTime = timer;
			process.endTime = timer + process.bt;
			timer = process.endTime;
		}
		
		System.out.println("\n Gantt Chart :\n");
		for (Iterator<Process> iterator = processQueue.iterator(); iterator.hasNext();) {
			Process process = (Process) iterator.next();
			process.showInfo();
			waitingTime.add(process.getStartTime()-process.at);
			System.out.println("\nWaiting time:"+(process.getStartTime()-process.at)+"\nTurn around time:"+(process.getStartTime()-process.at+process.bt));
		}
		
		//Finding average waiting time
		int sum=0;
		for (Iterator<Integer> iterator = waitingTime.iterator(); iterator.hasNext();) {
			sum = sum + (Integer) iterator.next();
			
		}
		
		System.out.println("\nAverage Waiting time = "+(sum/count));
	}
	
	public void sjf(){
		Collections.sort(processQueue,Process.arrivalTimeComparator);
		fcfs();
	}
	
	public void roundRobin(int x)	// x = timeQuantum
	{

		int timeSlice = x;

		Collections.sort(processQueue,Process.arrivalTimeComparator);
		int timer = 0;
		System.out.println("SIZE "+processQueue.size());
	while(processQueue.isEmpty()!=true)
	{
		for (Iterator<Process> iterator = processQueue.iterator(); iterator.hasNext();) {
			Process type = (Process) iterator.next();
			for(int i=0;i<timeSlice && type.bt!=0;i++) {
				if (i==0) {
					System.out.println("Start time of process "+type.proid+" is "+timer+"\n");
				}
			
				type.bt = type.bt - 1;
				timer++;
			}
			if(type.bt==0){
				processQueue.remove(type);
			}
		}
	}

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
		
		/*
		// For Round Robin
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		sc.close();
		Schedule schedule = new Schedule();
		schedule.getinfo(1);
		
		schedule.roundRobin(x);*/
		
		
		// For Shortest Job First
		Schedule schedule = new Schedule();
		schedule.getinfo(1);
		schedule.sjf();
		
	}
}
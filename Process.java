import java.util.Comparator;
import java.util.Scanner;


public class Process implements Comparable<Process> {
	
	private String proid;
	public int bt,at,priority;
	public int startTime,endTime;
	private Scanner sc;
	public Process() {
		proid = null;
		bt = at = priority =startTime = endTime=0;
		sc = new Scanner(System.in);
	}
	
	public int getStartTime() {
		return startTime;
	}

	public int compareTo(Process p){
		return this.priority-p.priority;
	}
	
	public void input(int type){	// type = 0 if priority(not AT) is to be taken else type = 1 
		System.out.println("Enter process id:");
		proid = sc.nextLine();
		System.out.println("Enter burst time:");
		bt = sc.nextInt();
		// at = sc.nextInt();		
		if (type == 0) {
			System.out.println("Enter priority for proess:");
			priority = sc.nextInt();
		}
		
		if (type == 1) {
			System.out.println("Enter Arrival time:");
			at = sc.nextInt();
			
		}

	}
	public static Comparator<Process> arrivalTimeComparator = new Comparator<Process>() {
		public int compare(Process p1, Process p2){
			return Integer.compare(p1.at, p2.at);
		}
	};
	public int getAt() {
		return at;
	}

	public void showInfo(){
		System.out.println("\nProcess "+proid+"\nBurst Time:"+bt+"\n"+"Arrival Time:"+at+"\nPriority:"+priority+"Start:"+startTime+"\nEnd:"+endTime);
	}
}

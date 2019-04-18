package com.rajatswnt;

public class Process implements Comparable<Process>{
	String processName;
	int arrivalTime,burstTime,startTime,priority;
	public int getPriority() {
		return priority;
	}
	public Process(String processName, int arrivalTime, int burstTime, int priority) {
		super();
		this.processName = processName;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
	}
	public Process(String processName, int arrivalTime, int burstTime) {
		super();
		this.processName = processName;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.startTime = 0;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getBurstTime() {
		return burstTime;
	}
	@Override
	public String toString() {
		return "Process [processName=" + processName + ", arrivalTime=" + arrivalTime + ", burstTime=" + burstTime
				+ ", startTime=" + startTime +", End time=" + (startTime+burstTime)+ "]";
	}
	@Override
	public int compareTo(Process arg0) {
		return this.getArrivalTime() - arg0.getArrivalTime(); 
		//return 0;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	
}

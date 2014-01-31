/*
 * Class for the counters created by the user. 
 * Counters contain an editable name and a count of how many times the user has "clicked" the counter.
 * Each counter also keeps logs for hours, days, weeks, and months  in ArrayLists of type Statistic
 */


package com.example.assignment1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Counter implements Serializable {

	
	
	private String counterName = null;
	private int count = 0;
	private static final long serialVersionUID = 4654897;
	private Date date;
	private ArrayList<Statistic> hourLogs;  //holds all logs by hour for this counter
	private ArrayList<Statistic> dayLogs;	 //holds all logs by day for this counter
	private ArrayList<Statistic> weekLogs;	 //holds all logs by week for this counter
	private ArrayList<Statistic> monthLogs; //holds all logs by month for this counter
	
	
	public Counter(String name){
		this.counterName = name;
		this.date = new Date();
		this.hourLogs = new ArrayList<Statistic>();
		this.dayLogs = new ArrayList<Statistic>();
		this.weekLogs = new ArrayList<Statistic>();
		this.monthLogs = new ArrayList<Statistic>();
	}
	
	
	
	public ArrayList<Statistic> getDayLogs() {
		return dayLogs;
	}



	public ArrayList<Statistic> getHourLogs() {
		return hourLogs;
	}
	
	
	
	public ArrayList<Statistic> getWeekLogs() {
		return weekLogs;
	}
	
	
	
	public ArrayList<Statistic> getMonthLogs() {
		return monthLogs;
	}
	

	
	public int getCount(){
		return count;
	}
	
	
	public Date getDate(){
		return date;
	}
	
	public void increment(){
		this.date = new Date(); //this will set the last updated time to the latest count, which is viewable in the ManageCounters activity
		count ++;
	}
	
	
	public String getCounterName() {
		return counterName;
	}

	
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public void resetCount(){
		this.count = 0;
		this.date = new Date(); //this will set the last updated time to the latest count, which is viewable in the ManageCounters activity
	}
	
	public void addHourLog(Statistic log){
		
		this.hourLogs.add(log);
		
	}
	
public void addDayLog(Statistic log){
		
		this.dayLogs.add(log);
		
	}

public void addWeekLog(Statistic log){
	
	this.weekLogs.add(log);
	
}

public void addMonthLog(Statistic log){
	
	this.monthLogs.add(log);
	
}
	 
}

/*
 * Class for the counters created by the user. 
 * Counters contain an editable name and a count of how many times the user has "clicked" the counter.
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
	
	private ArrayList<Statistic> hourLogs;
	private ArrayList<Statistic> dayLogs;
	private ArrayList<Statistic> weekLogs;
	private ArrayList<Statistic> monthLogs;
	
	private int hourCount = 0;
	private int dayCount = 0;
	private int weekCount = 0;
	private int monthCount = 0;
	
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


	public void setDayLogs(ArrayList<Statistic> dayLogs) {
		this.dayLogs = dayLogs;
	}


	public int getHourCount() {
		return hourCount;
	}


	public void incHourCount() {
		this.hourLogs.get(-1).increment();
	}


	public int getDayCount() {
		return dayCount;
	}


	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}


	public int getWeekCount() {
		return weekCount;
	}


	public void setWeekCount(int weekCount) {
		this.weekCount = weekCount;
	}


	public int getMonthCount() {
		return monthCount;
	}


	public void setMonthCount(int monthCount) {
		this.monthCount = monthCount;
	}


	public ArrayList<Statistic> getHourLogs() {
		return hourLogs;
	}
	
	public void setHourLogs(ArrayList<Statistic> hourLogs) {
		this.hourLogs = hourLogs;
	}
	
	public ArrayList<Statistic> getWeekLogs() {
		return weekLogs;
	}
	
	public void setWeekLogs(ArrayList<Statistic> weekLogs) {
		this.weekLogs = weekLogs;
	}
	
	public ArrayList<Statistic> getMonthLogs() {
		return monthLogs;
	}
	
	public void setMonthLogs(ArrayList<Statistic> monthLogs) {
		this.weekLogs = monthLogs;
	}
	
	public ArrayList<Statistic> getYearLogs() {
		return monthLogs;
	}
	
	public void setYearLogs(ArrayList<Statistic> yearLogs) {
		this.monthLogs = yearLogs;
	}

	
	
	
	public int getCount(){
		return count;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void increment(){
		this.date = new Date();
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
		this.date = new Date();
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

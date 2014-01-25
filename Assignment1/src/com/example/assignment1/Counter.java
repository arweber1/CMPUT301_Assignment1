/*
 * Class for the counters created by the user. 
 * Counters contain an editable name and a count of how many times the user has "clicked" the counter.
 */


package com.example.assignment1;

import java.io.Serializable;

public class Counter implements Serializable {

	
	private String counterName = null;
	private int count = 0;
	private static final long serialVersionUID = 4654897;
	
	public Counter(String name){
		this.counterName = name;
		
	}
	public int getCount(){
		return count;
	}
	
	public void increment(){
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
	}
	
	
	 
}

/*
 * Class for the counters created by the user. 
 * Counters contain an editable name and a count of how many times the user has "clicked" the counter.
 */


package com.example.assignment1;

public class Counter {

	private String counterName = null;
	private int count = 0;
	
	public Counter(String name){
		counterName = name;
		
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

	
	 
}

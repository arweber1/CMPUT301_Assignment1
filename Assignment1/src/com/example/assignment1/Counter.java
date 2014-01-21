/*
 * Class for the counters created by the user. 
 * Counters contain an editable name and a count of how many times the user has "clicked" the counter.
 */


package com.example.assignment1;

public class Counter {

	private static String counterName;
	private static int count;
	
	
	public static int getCount(){
		return count;
	}
	
	public static void increment(){
		count ++;
	}
	
	public static String getCounterName() {
		return counterName;
	}

	public static void setCounterName(String counterName) {
		Counter.counterName = counterName;
	}

	
	 
}

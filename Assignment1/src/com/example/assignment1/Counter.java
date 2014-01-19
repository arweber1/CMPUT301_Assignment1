/*
 * Class for the counters created by the user. 
 * Counters contain an editable name and a count of how many times the user has "clicked" the counter.
 */


package com.example.assignment1;

public class Counter {

	private static String counterName;
	private static String count;
	
	
	
	public static String getName(){
		return counterName;
	}
	
	public static String getCount(){
		return count;
	}
	
	public void editName(String newName){
		this.counterName = newName;
	}
	
	 
}

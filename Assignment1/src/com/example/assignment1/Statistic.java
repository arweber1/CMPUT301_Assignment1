package com.example.assignment1;

import java.io.Serializable;
import java.util.Date;

/* Objects created by this class are a single statistic, or log entry. When a Statistic object is made,
 * it is added to the array of the appropriate Counter object
 */
public class Statistic implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	
	int count = 0;
	Date date;
	
	public Statistic(Date date){
		this.date = date;
		
	}
	
	public void increment(){
		count ++;
	}

	
	public int getCount() {
		return count;
	}

	
	
	public Date getDate() {
		return date;
	}



}

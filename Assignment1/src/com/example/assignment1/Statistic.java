package com.example.assignment1;

import java.io.Serializable;
import java.util.Date;

public class Statistic implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	int count = 0;
	Date date;
	
	public Statistic(Date date){
		this.date = new Date();
		//this.count = count;
		
	}
	
	public void increment(){
		count ++;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

}

package com.example.assignment1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

//This is where the user  can choose to view the various statistics by hour, day, week, or month
public class CounterStatistics extends MainActivity {

	
	private ArrayList<Counter> counterArray = new ArrayList<Counter>();
	   
   
	protected ListView listview2;
	private static final String FILENAME = "counters.sav";
	private int position = 0;
	private String text;
	private boolean backPressed = false;
	private SimpleDateFormat dateFormat;
	private String date;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_statistics);
        
        
        Bundle extras = getIntent().getExtras();
	       
        position = extras.getInt("counter position");
        
        
        
        
        
	}
	
	@Override
	public void onResume() {
		 
			super.onResume();
			setContentView(R.layout.counter_statistics);
			loadClassFile(FILENAME,counterArray);
			
		  		
	 }
	
	
	//Check if the back button was pressed. This prevents the activity from
	//closing completely when the user backs out from viewing statistics
	@Override
	public void onBackPressed(){
		if (backPressed){
			setContentView(R.layout.counter_statistics);
			backPressed = false;
		}
		else{
			finish();
		}
	}
	
	//view the hourly statistics
	public void viewByHour(View V){
		setContentView(R.layout.view_statistics);
		backPressed = true;
		text = "";
		dateFormat = new SimpleDateFormat("MMM dd ha", Locale.CANADA);
		
		TextView stats = (TextView) findViewById(R.id.textView1);
		
		int size = counterArray.get(position).getHourLogs().size();
		
		for (int i  = 0; i < size; i++){
			date = dateFormat.format(counterArray.get(position).getHourLogs().get(i).getDate());
			text += date + 
					" -- " + String.valueOf(counterArray.get(position).getHourLogs().get(i).getCount()) + "\n";
		}
		
		
		stats.setText(text);
	}
	
	//view the daily statistics
	public void viewByDay(View V){
		setContentView(R.layout.view_statistics);
		backPressed = true;
		text = "";
		dateFormat = new SimpleDateFormat("MMM dd", Locale.CANADA);
		
		TextView stats = (TextView) findViewById(R.id.textView1);
		
		int size = counterArray.get(position).getDayLogs().size();
		
		for (int i  = 0; i < size; i++){
			date = dateFormat.format(counterArray.get(position).getDayLogs().get(i).getDate());
			text += date + 
					" -- " + String.valueOf(counterArray.get(position).getDayLogs().get(i).getCount()) + "\n";
		}
		
		
		stats.setText(text);
	}
	
	//view the weekly statistics
	public void viewByWeek(View V){
		setContentView(R.layout.view_statistics);
		backPressed = true;
		text = "";
		dateFormat = new SimpleDateFormat("MMM dd", Locale.CANADA);
		
		TextView stats = (TextView) findViewById(R.id.textView1);
		
		int size = counterArray.get(position).getWeekLogs().size();
		
		for (int i  = 0; i < size; i++){
			date = dateFormat.format(counterArray.get(position).getWeekLogs().get(i).getDate());
			text += "Week of " + date + 
					" -- " + String.valueOf(counterArray.get(position).getWeekLogs().get(i).getCount()) + "\n";
		}
		
		
		stats.setText(text);
	}
	
	
	//view the monthly statistics
	public void viewByMonth(View V){
		setContentView(R.layout.view_statistics);
		backPressed = true;
		text = "";
		dateFormat = new SimpleDateFormat("MMM", Locale.CANADA);
		
		TextView stats = (TextView) findViewById(R.id.textView1);
		
		int size = counterArray.get(position).getMonthLogs().size();
		
		for (int i  = 0; i < size; i++){
			date = dateFormat.format(counterArray.get(position).getMonthLogs().get(i).getDate());
			text += "Month of " + date + 
					" -- " + String.valueOf(counterArray.get(position).getMonthLogs().get(i).getCount()) + "\n";
		}
		
		
		stats.setText(text);
	}
}


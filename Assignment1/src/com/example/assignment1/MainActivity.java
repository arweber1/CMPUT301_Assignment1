package com.example.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class MainActivity extends Activity {
	
	private ArrayList<Counter> counterArray;
	private ListView listview;
	private static final String COUNTERFILE = "counters.sav";
	private Calendar calendar;
	private Calendar calendar2;
	private ArrayList<Statistic> statistics;
	private Date date;
	private int size;
	private long timeInHours;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listview = (ListView) findViewById(R.id.list);
        
        //load the counters and set up the listview
        counterArray = new ArrayList<Counter>();
  		loadClassFile(COUNTERFILE, counterArray);
  		listview.setAdapter(new CustomAdapter(this, counterArray, true));
        
        //set up listener
        listview.setOnItemClickListener(new OnItemClickListener() {
       
        	    @Override
        		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        		 counterArray.get(position).increment(); //increment counter
        		 ((BaseAdapter) listview.getAdapter()).notifyDataSetChanged();
        		
        		 updateStats(position); //check which stats need to be updated
        		 saveInFile(counterArray);
        		}

				
        });
    }	
    
  			
		
  	@Override
    public void onResume() {
  		super.onResume();
  		//reload counters and set up the listview
  		
  		counterArray = new ArrayList<Counter>();
  		loadClassFile(COUNTERFILE, counterArray);
  		
        
        listview.setAdapter(new CustomAdapter(this, counterArray, true));
  	}
  	
  	
  	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    //enters counter creation
    public void createCounter(View v) {
		Intent create = new Intent(MainActivity.this, CreateCounter.class);
	  	startActivityForResult(create, 0);
	}
    
    
    //Add counter to ListView after returning from CreateCounter activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent create) {
    	if (resultCode == RESULT_OK){
    		
	    	super.onActivityResult(requestCode, resultCode, create);
	    	
	    	String name = create.getStringExtra("counter name");
	    	Counter counter = new Counter(name);
	    	counterArray.add(counter);
	    	saveInFile(counterArray);
    	}
    }
    
    
    //Enter management activity where the user can manage their counters
    public void manageCounters(View v) {
    	
    	if (counterArray.size() != 0) {
    		
	    	Intent manage = new Intent(MainActivity.this, ManageCounters.class);
		  	startActivity(manage);
    	}
    }
    
    
    //load the counters using gson
    public void loadClassFile(String file, ArrayList<Counter> array2)
    	{
    	try {
            FileInputStream fis = openFileInput(file);
                        
                        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                        String line = reader.readLine();
                        Gson gson = new Gson();
                        
                       
                        JsonParser parser = new JsonParser();
                        JsonArray array = parser.parse(line).getAsJsonArray();
                        
                      
                        //iterate through the loaded array and add it to the counterArray
                        Counter counter;	
                        for (int i = 0; i < array.size(); i++) {
                        	
                        		counter = gson.fromJson(array.get(i), Counter.class);
                        		array2.add(counter);
                        }
               
                
        
                  fis.close();
          } catch (IOException e) {
                        
                e.printStackTrace();
            }
   	}
    
    
    
    //save the counters using gson
    protected void saveInFile(ArrayList<Counter> counters) {
    	
        try {
        	Gson g_object = new Gson();
            String to_be_stored = g_object.toJson(counters);
            FileOutputStream fos = openFileOutput(COUNTERFILE,
                            Context.MODE_PRIVATE);
            

            fos.write(to_be_stored.getBytes());
            
            fos.close();
        	
        } catch (FileNotFoundException e) {
                
                e.printStackTrace();
        } catch (IOException e) {
               
                e.printStackTrace();
        }
}
    
  
  

//function that calls every update method
private void updateStats(int position) {
	
	
	updateHours(position);
	updateDays(position);
	updateWeeks(position);
	updateMonths(position);
	
	
}



private void updateHours(int position){
	
	statistics = counterArray.get(position).getHourLogs();
	date = counterArray.get(position).getDate();   // given date
	
	
	calendar = GregorianCalendar.getInstance(Locale.getDefault()); // creates a new calendar instance
	calendar2 = GregorianCalendar.getInstance(Locale.getDefault()); 
	 
	//set to current date
	calendar.setTime(date); 
	
	
	
	size = statistics.size();
	if (size != 0){
		date = counterArray.get(position).getHourLogs().get(size - 1).date;
	}
	
	//set the date to the last log entry
	calendar2.setTime(date);
	
	
	
	//check if one hour has passed since the last log was added
	timeInHours = ((calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / (1000 * 60 * 60));
	
	//If an hour has not passed then increment the count of the last log
	if (statistics.size() != 0 && timeInHours < 1){
		counterArray.get(position).getHourLogs().get(statistics.size()-1).increment();
	}
	
	//otherwise add a new log
	else{
		Statistic stat = new Statistic(new Date());
		counterArray.get(position).addHourLog(stat);
		counterArray.get(position).getHourLogs().get(statistics.size()-1).increment();
		
	}
}



private void updateDays(int position){
	
	statistics = counterArray.get(position).getDayLogs();
	date = counterArray.get(position).getDate();   // given date
	
	
	calendar.setTime(date);   // assigns calendar to given date 
	
	
	
	size = statistics.size();
	if (size != 0){
		date = counterArray.get(position).getDayLogs().get(size - 1).date;
	}
	
	//set the date to the last log entry
	calendar2.setTime(date);
	
	//check if one day has passed
	timeInHours = ((calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / (1000 * 60 * 60));
	
	//If one day has not passed then increment the last log entry
	if (statistics.size() != 0 && timeInHours < 24){
		counterArray.get(position).getDayLogs().get(statistics.size()-1).increment();
	}
	
	//Otherwise add a new day entry
	else{
		Statistic stat = new Statistic(new Date());
		counterArray.get(position).addDayLog(stat);
		counterArray.get(position).getDayLogs().get(statistics.size()-1).increment();
		
	}
}


private void updateWeeks(int position){
	
   calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); 
	
	statistics = counterArray.get(position).getWeekLogs();
	
	date = calendar.getTime(); 
	
	calendar.setTime(date); //set the date to the first day of the current week
	
	
	
	size = statistics.size();
	if (size != 0){
		date = counterArray.get(position).getWeekLogs().get(size - 1).date;
		
	}
	
	//get the date of the last log entry
	calendar2.setTime(date);

	//check if one week has passed
	timeInHours = ((calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / (1000 * 60 * 60));
	
	//If one week has not passed then increment the last log entry
	if (statistics.size() != 0 && timeInHours < 168 ){
		counterArray.get(position).getWeekLogs().get(statistics.size()-1).increment();
	}
	
	//Otherwise add a new week log entry that starts at the beginning of the week
	else{
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		Statistic stat = new Statistic(calendar.getTime());
		counterArray.get(position).addWeekLog(stat);
		counterArray.get(position).getWeekLogs().get(statistics.size()-1).increment();
		
	}
}

private void updateMonths(int position){
	
	statistics = counterArray.get(position).getMonthLogs();
	date = counterArray.get(position).getDate();   // given date
	
	
	calendar.setTime(date);   // assigns calendar to given date 
	int month = calendar.get(Calendar.MONTH); 
	int year = calendar.get(Calendar.YEAR);
	
	size = statistics.size();
	if (size != 0){
		date = counterArray.get(position).getMonthLogs().get(size - 1).date;
	}
	
	//get the date of the last log entry
	calendar2.setTime(date);
	int currentMonth = calendar2.get(Calendar.MONTH);
	int currentYear = calendar2.get(Calendar.YEAR);
	
	//For months, check if the month has changed and if the year has changed
	if (statistics.size() != 0 && currentMonth == month && year == currentYear){
		counterArray.get(position).getMonthLogs().get(statistics.size()-1).increment();
	}
	
	//If they have changed then add a new log entry
	else{
		Statistic stat = new Statistic(new Date());
		counterArray.get(position).addMonthLog(stat);
		counterArray.get(position).getMonthLogs().get(statistics.size()-1).increment();
		
	}
}



}

package com.example.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
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
	
	private ArrayList<Counter> counterArray;// = new ArrayList<Counter>();
	protected ListView countersListView;
	//protected ArrayAdapter<String> arrayAdapter;
	private ListView listview;
	private static final String COUNTERFILE = "counters.sav";
	private static final String STATSFILE = "statistics.sav";
	
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listview = (ListView) findViewById(R.id.list);
        counterArray = new ArrayList<Counter>();
  		loadClassFile(COUNTERFILE, counterArray);
  		
        
        listview.setAdapter(new CustomAdapter(this, counterArray, true));
        
        
        listview.setOnItemClickListener(new OnItemClickListener() {
       
        	    @Override
        		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        		 counterArray.get(position).increment();
        		 ((BaseAdapter) listview.getAdapter()).notifyDataSetChanged();
        		 //updateStats(position);
        		 //saveInFile(counterArray);
        		 updateStats(position);
        		 saveInFile(counterArray);
        		}

				
        });
    }	
    
  			
		
  	@Override
    public void onResume() {
  		super.onResume();
  		
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
    
    
    //Enter management activity where the user can edit their counters
    public void manageCounters(View v) {
    	
    	if (counterArray.size() != 0) {
    		
	    	Intent manage = new Intent(MainActivity.this, ManageCounters.class);
		  	startActivity(manage);
    	}
    }
    
    public void loadClassFile(String file, ArrayList<Counter> array2)
    	{
    	try {
            FileInputStream fis = openFileInput(file);
                        
                        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                        String line = reader.readLine();
                        Gson gson = new Gson();
                        
                       
                        JsonParser parser = new JsonParser();
                        JsonArray array = parser.parse(line).getAsJsonArray();
                        //System.out.println(array);
                      
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
    
    protected void saveInFile(ArrayList<Counter> counters) {
    	
        try {
        	Gson g_object = new Gson();
            String to_be_stored = g_object.toJson(counters);
            FileOutputStream fos = openFileOutput(COUNTERFILE,
                            Context.MODE_PRIVATE);
            

            fos.write(to_be_stored.getBytes());
            
            fos.close();
        	
        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
}
    
  //orders the counter array by descending order
    public ArrayList<Counter> orderCounters(ArrayList<Counter> counters) {
    	
    	int max;
    	int counterPosition = 0;
    	int count;
    	ArrayList<Counter> counterArray = new ArrayList<Counter>();
    	
    	while (counters.size() != 0){
    		
    		max = 0;
    		
    		for (int i = 0; i < counters.size(); i++){
    			
    			count = counters.get(i).getCount();
    			
    			if (count >= max){
    				
    				max = count;
    				counterPosition = i;
    			}
    			
    		}
    		
    		counterArray.add(counters.get(counterPosition));
    		counters.remove(counterPosition);
    		
    	}
    	
    		

    	return counterArray;
    }
  


private void updateStats(int position) {
	// TODO Auto-generated method stub
	ArrayList<Statistic> statistics = counterArray.get(position).getHourLogs();
	Date date = counterArray.get(position).getDate();   // given date
	int hour;
	
	Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault()); // creates a new calendar instance
	Calendar calendar2 = GregorianCalendar.getInstance(Locale.getDefault()); // creates a new calendar instance
	//dateFormat.setCalendar(calendar); 
	calendar.setTime(date);   // assigns calendar to given date 
	hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
	
	
	int size = counterArray.get(position).getHourLogs().size();
	if (size != 0){
		date = counterArray.get(position).getHourLogs().get(size - 1).date;
	}
	
	calendar.setTime(date);
	int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
	
	
	if (statistics.size() != 0 && currentHour == hour){
		counterArray.get(position).getHourLogs().get(statistics.size()-1).increment();
	}
	
	else{
		Statistic stat = new Statistic(new Date());
		counterArray.get(position).addHourLog(stat);
		counterArray.get(position).getHourLogs().get(statistics.size()-1).increment();
		
	}
	
	
	
	
	
	
	statistics = counterArray.get(position).getDayLogs();
	date = counterArray.get(position).getDate();   // given date
	
	
	calendar.setTime(date);   // assigns calendar to given date 
	int day = calendar.get(Calendar.DAY_OF_WEEK); // gets hour in 24h format
	
	
	size = counterArray.get(position).getDayLogs().size();
	if (size != 0){
		date = counterArray.get(position).getDayLogs().get(size - 1).date;
	}
	
	calendar.setTime(date);
	int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
	
	
	if (statistics.size() != 0 && currentDay == day){
		counterArray.get(position).getDayLogs().get(statistics.size()-1).increment();
	}
	
	else{
		Statistic stat = new Statistic(new Date());
		counterArray.get(position).addDayLog(stat);
		counterArray.get(position).getDayLogs().get(statistics.size()-1).increment();
		
	}
	
	
	
	
	
	
	calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
	
	statistics = counterArray.get(position).getWeekLogs();
	//date = counterArray.get(position).getDate();   // given date
	
	//System.out.println(calendar.getTime());
	  // assigns calendar to given date 
	date = calendar.getTime(); // gets hour in 24h format
	calendar.setTime(date); 
	int week = calendar.get(Calendar.DAY_OF_WEEK);
	System.out.println(week);
	
	size = counterArray.get(position).getWeekLogs().size();
	if (size != 0){
		date = counterArray.get(position).getWeekLogs().get(size - 1).date;
		
	}
	
	
	calendar2.setTime(date);
	int currentWeek = calendar.get(Calendar.DAY_OF_WEEK);
	long time = ((calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / (1000 * 60 * 60));
	//System.out.println(time);
	
	if (statistics.size() != 0 && time < 168 ){
		counterArray.get(position).getWeekLogs().get(statistics.size()-1).increment();
	}
	
	else{
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		Statistic stat = new Statistic(calendar.getTime());
		counterArray.get(position).addWeekLog(stat);
		counterArray.get(position).getWeekLogs().get(statistics.size()-1).increment();
		
	}
	
	
	
	
	statistics = counterArray.get(position).getMonthLogs();
	date = counterArray.get(position).getDate();   // given date
	
	
	calendar.setTime(date);   // assigns calendar to given date 
	int month = calendar.get(Calendar.MONTH); // gets hour in 24h format
	
	
	size = counterArray.get(position).getMonthLogs().size();
	if (size != 0){
		date = counterArray.get(position).getMonthLogs().get(size - 1).date;
	}
	
	calendar.setTime(date);
	int currentMonth = calendar.get(Calendar.MONTH);
	
	
	if (statistics.size() != 0 && currentMonth == month){
		counterArray.get(position).getMonthLogs().get(statistics.size()-1).increment();
	}
	
	else{
		Statistic stat = new Statistic(new Date());
		counterArray.get(position).addMonthLog(stat);
		counterArray.get(position).getMonthLogs().get(statistics.size()-1).increment();
		
	}
	
	
}

}

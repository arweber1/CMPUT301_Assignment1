package com.example.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class ManageCounters extends MainActivity {

	
	
		private ArrayList<Counter> counterArray = new ArrayList<Counter>();
	    private ListView listview2;
		private static final String FILENAME = "counters.sav";
		
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.manage_counters);
	        listview2 = (ListView) findViewById(R.id.manage_list);
	        
	        //load counters and set up the listview
	        counterArray = new ArrayList<Counter>();
	        loadClassFile(FILENAME, counterArray);
	        listview2.setAdapter(new CustomAdapter(this, counterArray, false));
	        
	        
	        
	        //set up listener
	        listview2.setOnItemClickListener(new OnItemClickListener() {
	       
	        @Override
	        //Enter the edit counter activity on click
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        		
	       
	        		Intent edit = new Intent(ManageCounters.this, EditCounter.class);
	        		edit.putExtra("counter position", (int)position); //send over the position of the counter clicked
	        		edit.putExtra("counter name", counterArray.get(position).getCounterName()); //send over the name of the counter clicked
	        		startActivity(edit);
	   
	        		}
	        });
	  
	    }	
	
	
 @Override
public void onResume() {
	 
		super.onResume();
		counterArray = new ArrayList<Counter>();
		loadClassFile(FILENAME, counterArray);
		counterArray = new ArrayList<Counter>(orderCounters(counterArray));
		saveInFile(counterArray);
		
		//set up view
		listview2 = (ListView) findViewById(R.id.manage_list);
		listview2.setAdapter(new CustomAdapter(this, counterArray, false));
	  		
		//exit activity if there are no counters to edit
	  	if (counterArray.size() == 0){
	  		finish();
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
 
 


}

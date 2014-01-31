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
	   
	    protected ListView countersListView;

		protected ArrayAdapter<String> arrayAdapter;
		protected ListView listview2;
		private static final String FILENAME = "counters.sav";
		
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.manage_counters);

	        counterArray = new ArrayList<Counter>();
	        loadClassFile(FILENAME, counterArray);
	        
	  		listview2 = (ListView) findViewById(R.id.manage_list);
	        listview2.setAdapter(new CustomAdapter(this, counterArray, false));
	        
	        
	        listview2.setOnItemClickListener(new OnItemClickListener() {
	       
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        		
	       
	        		Intent edit = new Intent(ManageCounters.this, EditCounter.class);
	        		edit.putExtra("counter position", (int)position);
	        		edit.putExtra("counter name", counterArray.get(position).getCounterName());
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
		listview2 = (ListView) findViewById(R.id.manage_list);
		listview2.setAdapter(new CustomAdapter(this, counterArray, false));
	  		
	  		
	  		
 }
	
	
 
 /*public void viewStatistics(View v){
	 
	 Intent statistics = new Intent(ManageCounters.this, CounterStatistics.class);
	 startActivity(statistics);
 }*/
 
 
 


}

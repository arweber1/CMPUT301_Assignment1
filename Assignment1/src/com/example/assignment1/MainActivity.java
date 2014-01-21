package com.example.assignment1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	protected ArrayList<Counter> counterArray = new ArrayList<Counter>();
   // private String counterName;
   
    protected ListView countersListView;

	// Declare an array to store data to fill the list
	//protected String[] counterArray ;

	// Declare an ArrayAdapter that we use to join the data set and the ListView
	// is the way of type safe, means you only can pass Strings to this array
	//Anyway ArrayAdapter supports only TextView
	protected ArrayAdapter<String> arrayAdapter;
	protected ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize the UI components
        //countersListView = (ListView) findViewById(R.id.list);
        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, counterArray);
        //countersListView.setAdapter(arrayAdapter);
      
        listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(new CustomAdapter(this, counterArray));
        
        
        listview.setOnItemClickListener(new OnItemClickListener() {
        	
        	 @Override
        		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        		// TODO Auto-generated method stub
        		 Counter.increment();
        		 ((BaseAdapter) listview.getAdapter()).notifyDataSetChanged();
        		}
        });
        
       
    }	
    
  	
   

		
		
  	@Override
    public void onResume() {
  		super.onResume();
  	
  		
  		
  		
  	}
  	
  	
  	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    //enters counter creation
    public void addCounter(View v) {
		Intent create = new Intent(MainActivity.this, CreateCounter.class);
		//create.putExtra("name", counterName);
	  	startActivityForResult(create, 0);
	}
    
    
    //Add counter to ListView after returning from CreateCounter activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent create) {
    	if (resultCode == RESULT_OK){
    		
	    	super.onActivityResult(requestCode, resultCode, create);
	    	String name = create.getStringExtra("counter name");
	    	
	    	Counter.setCounterName(name);
	    	counterArray.add(new Counter());
	   
    	}
    }
    
    
}

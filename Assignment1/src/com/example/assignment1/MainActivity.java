package com.example.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class MainActivity extends Activity {
	
	private ArrayList<Counter> counterArray = new ArrayList<Counter>();
	protected ListView countersListView;
	protected ArrayAdapter<String> arrayAdapter;
	private ListView listview;
	private static final String FILENAME = "counters.sav";
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listview = (ListView) findViewById(R.id.list);
        counterArray = new ArrayList<Counter>();
  		loadClassFile(FILENAME, counterArray);
  		
        
        listview.setAdapter(new CustomAdapter(this, counterArray));
        
        
        listview.setOnItemClickListener(new OnItemClickListener() {
       
        	    @Override
        		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        		 counterArray.get(position).increment();
        		 ((BaseAdapter) listview.getAdapter()).notifyDataSetChanged();
        		 saveInFile(counterArray);
        		}
        });
    }	
    
  			
		
  	@Override
    public void onResume() {
  		super.onResume();
  		
  		counterArray = new ArrayList<Counter>();
  		loadClassFile(FILENAME, counterArray);
  		
        
        listview.setAdapter(new CustomAdapter(this, counterArray));
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
            FileOutputStream fos = openFileOutput(FILENAME,
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
  }


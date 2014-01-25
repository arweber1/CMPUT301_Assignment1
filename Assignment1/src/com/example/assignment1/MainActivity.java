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
   // private String counterName;
   
    protected ListView countersListView;

	// Declare an array to store data to fill the list
	//protected String[] counterArray ;

	// Declare an ArrayAdapter that we use to join the data set and the ListView
	// is the way of type safe, means you only can pass Strings to this array
	//Anyway ArrayAdapter supports only TextView
	protected ArrayAdapter<String> arrayAdapter;
	protected ListView listview;
	private static final String FILENAME = "counters.sav";
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize the UI components
        //countersListView = (ListView) findViewById(R.id.list);
        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, counterArray);
        //countersListView.setAdapter(arrayAdapter);
        //counterArray.add(new Counter("test"));
        //counterArray = (ArrayList<Counter>)loadClassFile(new File(FILENAME));
        listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(new CustomAdapter(this, counterArray));
        //counterArray.add(new Counter("test"));
        //counterArray.add(new Counter("test2"));
        
        //saveInFile(new Counter("test"));
        loadClassFile(FILENAME,counterArray);
        
        listview.setOnItemClickListener(new OnItemClickListener() {
       
        	 @Override
        		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		// TODO Auto-generated method stub
        		 //saveInFile()
        		 counterArray.get(position).increment();
        		 ((BaseAdapter) listview.getAdapter()).notifyDataSetChanged();
        		 saveInFile(counterArray);
        		}
        });
        
       
    }	
    
  	
   

		
		
  	@Override
    public void onResume() {
  		super.onResume();
  		((BaseAdapter) listview.getAdapter()).notifyDataSetChanged();
  		
  		
  		
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
	    	Counter counter = new Counter(name);
	    	counterArray.add(counter);
	    	saveInFile(counterArray);
    	}
    }
    
    
    //Enter management activity where the user can edit their counters
    public void manageCounters(View v){
    	
    	if (counterArray.size() != 0){
	    	Intent manage = new Intent(MainActivity.this, ManageCounters.class);
			manage.putExtra("list of counters", counterArray);
		  	startActivity(manage);
    	}
    }
    
    public void loadClassFile(String file, ArrayList<Counter> array2)
    	{
    	try {
            FileInputStream fis = openFileInput(file);
                        
                        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                        String line = reader.readLine();
                        //System.out.println(line);
                       // String line = reader.readLine();
                       // line = reader.readLine();
                        Gson gson = new Gson();
                        
                       
                        JsonParser parser = new JsonParser();
                        JsonArray array = parser.parse(line).getAsJsonArray();

                        //Counter myTypes = gson.fromJson(new FileReader(reader), Counter.class);
                        //System.out.println(gson.toJson(myTypes));
                        //try{
                        		Counter counter;
                        		
                        		for (int i = 0; i < array.size(); i++){
                        			counter = gson.fromJson(array.get(i), Counter.class);
                        			array2.add(counter);
                        		}
                                
                                //System.out.println(counter);
                                //counterArray.add(counter);
                                
                       // System.out.println(object.fromJson(reader, Counter.class) + "hhhhhhhhhhhhhhhhh");
                        //}catch(EOFException e){
                
        
                  fis.close();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
   	}
    
    protected void saveInFile(ArrayList<Counter> counters) {
        try {
        	Gson g_object = new Gson();
            String to_be_stored = g_object.toJson(counters);
            FileOutputStream fos = openFileOutput(FILENAME,
                            Context.MODE_PRIVATE);
            
            /*fos.write(new String(date.toString() + " | " + text)
                            .getBytes());*/
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


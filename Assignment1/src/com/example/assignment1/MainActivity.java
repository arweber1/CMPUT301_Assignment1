package com.example.assignment1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ArrayList<String> itemArray;
    private ArrayAdapter<String> itemAdapter;
    private ListView lvItem;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //itemArray.add("test");
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itemArray);
        //lvItem = new ListView(this);
        lvItem.setAdapter(itemAdapter);
        //itemAdapter.notifyDataSetChanged();
    }	
         
  	  // first parameter is the context, second is the class of the activity to launch
  	  
  	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    //enters counter creation
    public void addCounter(View v) {
		Intent create = new Intent(MainActivity.this, CreateCounter.class);
	  	  startActivity(create); // brings up the second activity
	}
}

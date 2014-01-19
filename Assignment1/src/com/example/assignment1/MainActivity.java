package com.example.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        
        
        
        
        
        final Button btncreate = (Button) findViewById(R.id.addCounter);
        btncreate.setOnClickListener(new View.OnClickListener(){
        	
        	public void onClick(View view) {
        		Intent create = new Intent(MainActivity.this, CreateCounter.class);
        	  	  startActivity(create); // brings up the second activity
        	}

			
    });
        
    }
    
  	  // first parameter is the context, second is the class of the activity to launch
  	  
  	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

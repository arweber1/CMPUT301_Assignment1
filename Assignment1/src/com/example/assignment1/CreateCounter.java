package com.example.assignment1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CreateCounter extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_counter);
		
		//cancel button for canceling current counter creation
		final Button btncancel = (Button) findViewById(R.id.cancel_button);
        btncancel.setOnClickListener(new View.OnClickListener(){
        	
        	public void onClick(View view) {
        		finish();
        	}

			
    });
		
        
        
        //button to actually create the counter and add it to the ListView
        final Button btncreate = (Button) findViewById(R.id.create_button);
        btncreate.setOnClickListener(new View.OnClickListener(){
        	
        	public void onClick(View view) {
        		//RelativeLayout layout = (RelativeLayout) findViewById(R.id.CreateCounter_container);
        		//layout.setBackgroundColor(Color.argb(255, 255, 0, 0));
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		//finish();
        	}

			
    });
		
		}
}

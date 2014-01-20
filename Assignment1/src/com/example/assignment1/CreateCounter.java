package com.example.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateCounter extends Activity {
	String name;
	
	
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_counter);
		
		
	}
	
	
	//adds counter to ListView
	public void create(View v){
		
		EditText editName = (EditText) findViewById(R.id.counter_name);
		if (editName != null){
			
			Intent create = getIntent();
			
			
			name = create.getStringExtra("counterName");
			name = editName.getText().toString();
			
			create.putExtra("counter name", name);
			setResult(RESULT_OK, create);
			
			
			finish();
		}
		
		
	}
	
	
	
	public void cancel(View v){
		finish();
	}
}

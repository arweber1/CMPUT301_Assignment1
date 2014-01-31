package com.example.assignment1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateCounter extends Activity {
	String name;
	EditText editName = null;
	
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_counter);
		
		
	}
	
	
	//adds counter to ListView
	public void create(View v){
		
		Intent create = getIntent();
		EditText editName = (EditText) findViewById(R.id.counter_name);

		name = editName.getText().toString().trim();
		
		if (name.length() != 0 && name.length() <= 20){
						
			create.putExtra("counter name", name);
			setResult(RESULT_OK, create);
		    finish();
		}
		
		else{
			Context context = getApplicationContext();
			CharSequence text;
			int duration = Toast.LENGTH_LONG;

			
			if (name.length() == 0){
				text = "Name cannot be blank";
			}
			
			else{
				text = "Name exceeds 20 characters \nLength of input: " + name.length() + " characters";
			}
			
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
			toast.show();
		}
		
	}
	
	
	
	public void cancel(View v){
		finish();
	}
}

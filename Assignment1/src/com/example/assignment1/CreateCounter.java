package com.example.assignment1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*This is where the counters are made. They don't get added until it returns to the MainActivity.
 * Each counter has an editable name that cannot be blank and cannot exceed 20 characters.
 */

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
						
			create.putExtra("counter name", name); //send the counter name back to the MainActivity to be added
			setResult(RESULT_OK, create);
		    finish();
		}
		
		//handles the name restrictions. Gives the user a message if the name is blank
		//or exceeds 20 characters
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
	
	
	//Allows the user to quickly back out when they have the onscreen keyboard open
	public void cancel(View v){
		finish();
	}



	
}

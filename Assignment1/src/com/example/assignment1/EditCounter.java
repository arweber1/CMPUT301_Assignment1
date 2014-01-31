package com.example.assignment1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*This is where the user can manage their counters. They can edit
 * the name, reset it, delete it, and view this counter's stats
 */
public class EditCounter extends MainActivity {
	
	private boolean resetCount = false;
	private ArrayList<Counter> counterArray = new ArrayList<Counter>();
	private static final String FILENAME = "counters.sav";
	private int counterPosition;
	private String newName;
	private boolean deleteCounter = false;
	private EditText editName;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.edit_counter);
	        Bundle extras = getIntent().getExtras();
	       
	        counterPosition = extras.getInt("counter position");
	        newName = extras.getString("counter name");
	        loadClassFile(FILENAME,counterArray);
	        editName = (EditText) findViewById(R.id.editName);
			editName.setText(newName); //fill in the editText field with the current counter name
	       
	 }
	 
	 
	 
	 public String editName() {
			
		 newName = editName.getText().toString().trim();
		 return newName;
	 }
	 
	 public void resetCount(View v) {
		 
		 final Button resetButton = (Button) findViewById(R.id.reset_count);
		 resetCount = !resetCount;
		 
		 if (resetCount) {
			 resetButton.setText("Undo Reset");
			 
		 }
		 
		 else {
			resetButton.setText("Reset Count");
			
		 }
	 }
	 
	 public void deleteCounter(View v) {
		 
		 final Button deleteButton = (Button) findViewById(R.id.delete_button);
		 deleteCounter = !deleteCounter;
		 
		 if (deleteCounter) {
			 deleteButton.setText("Undo Delete");
			 
		 }
		 
		 else {
			deleteButton.setText("Delete This Counter");
			
		 }
	 }
	 
	 public void viewStatistics(View v){
		 
		 Intent statistics = new Intent(EditCounter.this, CounterStatistics.class);
		 statistics.putExtra("counter position", (int)counterPosition);
		 startActivity(statistics);
	 }
	 
	 public void saveChanges(View v){
		 
		 boolean saveAndExit = true; //cannot exit if the name restrictions are violated
		 
		 //check if the counter was deleted. If so, then skip to the end after deleting it
		 if (deleteCounter){
			 counterArray.remove(counterPosition);
			 
		 }
		 
		 else{ 
			newName = editName();
			Context context = getApplicationContext();
			CharSequence text;
			int duration = Toast.LENGTH_LONG;
			 
			 

				
			
			
			 if (newName.length() != 0 && newName.length() <= 20){
				 counterArray.get(counterPosition).setCounterName(newName);
			 }
			 
			 //check that the name isn't blank and doesn't exceed 20 characters
			 else{
				 
				 saveAndExit = false; //cannot exit until fixed
				 if (newName.length() == 0){
						text = "Name cannot be blank";
						
					}
						
					else{
						text = "Name exceeds 20 characters \nLength of input: " + newName.length() + " characters";
					}
						
					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
					toast.show();
					
			 }
			 
			 if (resetCount){
				 counterArray.get(counterPosition).resetCount();
			 }
		 }
		 
		 if (saveAndExit){
			 saveInFile(counterArray);
			 finish();
		 }
		 
	 }

	
	 
	 
}

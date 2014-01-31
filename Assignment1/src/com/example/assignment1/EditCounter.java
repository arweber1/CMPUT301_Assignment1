package com.example.assignment1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditCounter extends MainActivity {
	
	private boolean resetCount = false;
	private ArrayList<Counter> counterArray = new ArrayList<Counter>();
	private static final String FILENAME = "counters.sav";
	private int counterPosition;
	private String newName;
	private boolean deleteCounter = false;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.edit_counter);
	        Bundle extras = getIntent().getExtras();
	       
	        counterPosition = extras.getInt("counter position");
	        
	        loadClassFile(FILENAME,counterArray);
	       
	 }
	 
	 
	 
	 public String editName() {
		 
		 EditText editName = (EditText) findViewById(R.id.editName);
		 
			
		 newName = editName.getText().toString();
		
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
		 
		 if (deleteCounter){
			 counterArray.remove(counterPosition);
			 
		 }
		 
		 else{ 
			 newName = editName();
			 if (newName.trim().length() != 0){
				 counterArray.get(counterPosition).setCounterName(newName);
			 }
			 
			 if (resetCount){
				 counterArray.get(counterPosition).resetCount();
			 }
		 }
		 
		 saveInFile(counterArray);
		 finish();
	 }
	 
	 
}

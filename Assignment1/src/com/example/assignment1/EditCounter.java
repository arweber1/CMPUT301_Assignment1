package com.example.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

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
	 
	 
	 
	 public String editName(){
		 EditText editName = (EditText) findViewById(R.id.editName);
		 
			
		 newName = editName.getText().toString();
		 System.out.println(newName);
		 return newName;
	 }
	 
	 public void resetCount(View v){
		 final Button resetButton = (Button) findViewById(R.id.reset_count);
		 resetCount = !resetCount;
		 
		 if (resetCount){
			 resetButton.setText("Undo Reset");
			 
		 }
		 
		 else{
			resetButton.setText("Reset Count");
			
		 }
	 }
	 
	 public void deleteCounter(View v){
		 final Button deleteButton = (Button) findViewById(R.id.delete_button);
		 deleteCounter = !deleteCounter;
		 
		 if (deleteCounter){
			 deleteButton.setText("Undo Delete");
			 
		 }
		 
		 else{
			deleteButton.setText("Delete This Counter");
			
		 }
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

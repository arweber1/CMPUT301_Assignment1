package com.example.assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CreateCounter extends MainActivity {
	
	
	
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_counter);
		
	}
	
	
	//adds counter to ListView
	public void create(View v){
		counterArray.add("2");
	}
	
	
	
	public void cancel(View v){
		finish();
	}
}

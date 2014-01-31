package com.example.assignment1;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/*
 * This is the custom adapter for the various listviews throughout the app
 * 
 */
public class CustomAdapter extends BaseAdapter {

	
	Context context;
	ArrayList<Counter> data = new ArrayList<Counter>();
	boolean mainActivity;
    private static LayoutInflater inflater = null;

    public CustomAdapter(Context context, ArrayList<Counter> data, boolean isMainActivity) {
       
        this.context = context;
        this.data = data;
        this.mainActivity = isMainActivity;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
	@Override
	public int getCount() {
		
		 return data.size();
	}

	@Override
	public Object getItem(int position) {
		
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.counter_list_row, null);
        TextView text = (TextView) vi.findViewById(R.id.counter_name);
      
        //Set up the view for the MainActivity
        if (mainActivity){
        	text.setText(String.valueOf(data.get(position).getCounterName()));
        	text = (TextView) vi.findViewById(R.id.count);
        	text.setText(String.valueOf(data.get(position).getCount()));
        	
        }
        
        //Otherwise set up the view for ManageCounters
        else{
        	text.setText(String.valueOf(data.get(position).getCounterName()) + "\n\n" + 
        			"Last updated: " + data.get(position).getDate());
        	text = (TextView) vi.findViewById(R.id.count);
        	text.setText(String.valueOf("Current count: " + data.get(position).getCount()));
        }
        
        
        
        return vi;
	}

	

}

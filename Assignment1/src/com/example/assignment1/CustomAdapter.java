package com.example.assignment1;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	
	Context context;
    //String[] data;
	ArrayList<Counter> data = new ArrayList<Counter>();
    private static LayoutInflater inflater = null;

    public CustomAdapter(Context context, ArrayList<Counter> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.counter_list_row, null);
        TextView text = (TextView) vi.findViewById(R.id.counter_name);
        text.setText(Counter.getCounterName());
        text = (TextView) vi.findViewById(R.id.count);
        text.setText(Integer.toString(Counter.getCount()));
        return vi;
	}

}

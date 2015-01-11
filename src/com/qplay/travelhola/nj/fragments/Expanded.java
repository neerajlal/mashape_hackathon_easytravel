package com.qplay.travelhola.nj.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidhive.pushnotifications.R;
import com.qplay.travelhola.nj.libraries.AsyncFunctions;
import com.qplay.travelhola.nj.libraries.ParseJSON;

public class Expanded extends Activity {
	
	TextView frm;
	TextView to1;
	TextView date1;
	TextView air1;
	TextView gate1;
	
	TextView from_st;
	TextView to_st;
	
	ImageView weather;
	ImageView checklist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expanded);
		
		String id = getIntent().getExtras().getString("id");
		
		frm = (TextView) findViewById(R.id.from_a);
		to1 = (TextView) findViewById(R.id.to_a);
		
		date1 = (TextView) findViewById(R.id.from_date);
		air1 = (TextView) findViewById(R.id.airline1);
		gate1 = (TextView) findViewById(R.id.gate);
		
		to_st = (TextView) findViewById(R.id.to_st);
		from_st = (TextView) findViewById(R.id.from_st);
		
		weather = (ImageView) findViewById(R.id.weather_btn);
		checklist = (ImageView) findViewById(R.id.chklist_btn);
		
		new Load().execute(id);

		
	}
	
	public class Load extends AsyncTask<String, Boolean, String> {
		
		
		String from;
		String to;
		String depdate;
		String id1;
		String gate;
		String airline;
		
		@Override
		protected String doInBackground(String... params) {
			return new AsyncFunctions().getFlightItinerary(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			id1 = new ParseJSON().getString(result, "id").get(0);
			from = new ParseJSON().getString(result, "flight_from").get(0);
			to = new ParseJSON().getString(result, "flight_to").get(0);
			depdate = new ParseJSON().getString(result, "departure_date").get(0);
			gate = new ParseJSON().getString(result, "gate_number").get(0);
			airline = new ParseJSON().getString(result, "airline").get(0);
			
			frm.setText(from);
			to1.setText(to);
			date1.setText(depdate);
			air1.setText(airline);
			gate1.setText(gate);
			
			to_st.setText(to.substring(0, 3).toUpperCase());
			from_st.setText(from.substring(0, 3).toUpperCase());
			
			
			weather.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Intent intent = new Intent(Expanded.this, Weather.class);
					intent.putExtra("loc", to);
					startActivity(intent);
					
				}
			});
			
			checklist.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Expanded.this, Checklist.class);
					intent.putExtra("loc", to);
					startActivity(intent);
				}
			});
			
			
		}

	}

}

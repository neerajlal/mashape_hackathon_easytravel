package com.qplay.travelhola.nj.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.InflateException;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidhive.pushnotifications.R;
import com.qplay.travelhola.nj.libraries.AsyncFunctions;
import com.qplay.travelhola.nj.libraries.ParseJSON;

public class Weather extends Activity {
	
	TextView txtLoc;
	TextView txtTemp;
	TextView txtWeather;
	ImageView imgWeather;
	
	String loc=null;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);
		
		loc = getIntent().getExtras().getString("loc");
		txtLoc = (TextView) findViewById(R.id.txtPlace);
		txtTemp = (TextView) findViewById(R.id.txtTemp);
		txtWeather = (TextView) findViewById(R.id.txtWeather);
		
		imgWeather = (ImageView) findViewById(R.id.img_weather);
		

		try {
			
			new Load().execute(loc);
			
		} catch (InflateException e) {
			e.printStackTrace();
		}
		
	}
	
	public class Load extends AsyncTask<String, Boolean, String> {
		
		
		String high;
		String low;
		
		String cond;
		
		ProgressDialog pd;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(Weather.this);
			pd.setMessage("Loading");
			pd.setTitle("Please Wait");
			pd.setCancelable(true);
			pd.show();
		}

		
		
		@Override
		protected String doInBackground(String... params) {
			return new AsyncFunctions().getWeather(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			high = new ParseJSON().getString(result, "high").get(0);
			low = new ParseJSON().getString(result, "low").get(0);
			
			cond = new ParseJSON().getString(result, "condition").get(0);
			
			txtTemp.setText(high + "' C - " + low +"' C");
			txtLoc.setText(loc);
			
			txtWeather.setText(cond);
			
			pd.dismiss();
			
			

		}

	}
	

}

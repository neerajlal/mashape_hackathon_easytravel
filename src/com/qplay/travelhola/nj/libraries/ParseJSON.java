package com.qplay.travelhola.nj.libraries;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ParseJSON {
	
	public boolean logintable(String result) {
		
		try {
			JSONArray jsa=new JSONArray(result);
			JSONObject jso = jsa.getJSONObject(0);
			Integer res = jso.getInt("response");
			
			if(res > 0)
				return true;

		} catch (JSONException e) {
			Log.e("Dtrack", "JSONException");
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<String> getString(String result, String key) {		
		
		ArrayList<String> vals=new ArrayList<String>();
		try {
			JSONArray jsa=new JSONArray(result);
			for (int i = 0; i < jsa.length(); i++) {			
				JSONObject jso = jsa.getJSONObject(i);
				vals.add(jso.getString(key));				
			} 
		}
		catch (JSONException e) {
			Log.e("Dtrack", "JSONException");
			e.printStackTrace();
		}
		
		return vals;		
	}
	
	public ArrayList<Double> getDouble(String result, String key) {
		
		ArrayList<Double> vals=new ArrayList<Double>();
		try {
			JSONArray jsa=new JSONArray(result);
			for (int i = 0; i < jsa.length(); i++) {			
				JSONObject jso = jsa.getJSONObject(i);
				vals.add(jso.getDouble(key));				
			} 
		}
		catch (JSONException e) {
			Log.e("Dtrack", "JSONException");
			e.printStackTrace();
		}
		return vals;		
	}

	
	

}

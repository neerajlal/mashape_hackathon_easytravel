package com.qplay.travelhola.nj.libraries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class AsyncFunctions {
	
	private String url="http://172.16.8.5:80/client/";
//	private String url = "https://n33raj-travel-v1.p.mashape.com/";
	
	
	//public String Go(List<BasicNameValuePair> args){
	public String Go(){
		
		BufferedReader reader=null;

		String l=null;
		StringBuffer sb=new StringBuffer("");
		HttpClient client=new DefaultHttpClient();
		
		try{						
			HttpGet request = new HttpGet(url);
			//HttpPost request = new HttpPost(url);
			//request.setEntity(new UrlEncodedFormEntity(args));
			HttpResponse response=client.execute(request);
			reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"iso-8859-1"),8);
			
			while( (l = reader.readLine()) != null){
				Log.e("ITS", l);
				sb.append(l);
			}
			reader.close();
			return sb.toString();
			
		} catch(ClientProtocolException e){
			Log.e("ITS", "ClientProtocolException");
			e.printStackTrace();
		}
		catch(IOException e){
			Log.e("ITS", "IOException");
			e.printStackTrace();
		}
		return null;
	}
	

	
	
	
	

	public String getFlightList(){

		url += "flist/";
//		url += "flist/?mashape-key=qeSBoskKmQmsh77i3hSgDVaQJpLFp1Vxu28jsnjm5p1gepocuw";
		return Go();
	}

	public String getFlightItinerary(String id){

		url += "getflightitinerary/?id="+id;
		return Go();
	}
	
	public String getWeather(String loc){

		url += "getweather/?loc="+loc;
		return Go();
	}
	
}

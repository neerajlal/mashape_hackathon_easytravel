package com.qplay.travelhola.nj.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidhive.pushnotifications.R;
import com.qplay.travelhola.nj.libraries.AsyncFunctions;
import com.qplay.travelhola.nj.libraries.ParseJSON;

public class Home extends Activity implements OnItemClickListener{
	
	ListView listView = null;
	List<String> id1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		id1 = new ArrayList<String>();
		
		try {
			
			new Load().execute();
			
			listView = (ListView) findViewById(R.id.listView1);
			listView.setOnItemClickListener(this);
			
		} catch (InflateException e) {
			e.printStackTrace();
		}
		
	}
	
	public class Load extends AsyncTask<String, Boolean, String> {
		
		
		List<String> from=new ArrayList<String>();
		List<String> to=new ArrayList<String>();
		List<String> depdate=new ArrayList<String>();
		
		
		@Override
		protected String doInBackground(String... params) {
			return new AsyncFunctions().getFlightList();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			id1 = new ParseJSON().getString(result, "id");
			from = new ParseJSON().getString(result, "flight_from");
			to = new ParseJSON().getString(result, "flight_to");
			depdate = new ParseJSON().getString(result, "departure_date");
			
			CustomAdapter adapter = new CustomAdapter(Home.this, R.layout.home, from, to, depdate, id1);
			listView.setAdapter(adapter);	
		}

	}
	
	
	
	// define your custom adapter
	private class CustomAdapter extends ArrayAdapter<String> {

		List<String> idd;
		List<String> bname;
		List<String> btime;
		List<String> ddate;

		ViewHolder viewHolder;

		public CustomAdapter(Context context, int textViewResourceId, List<String> from, List<String> to, List<String> date, List<String> idd) {

			// let android do the initializing :)
			super(context, textViewResourceId, from);
			this.bname = from;
			this.btime = to;
			this.ddate = date;
			this.idd = idd;
		}

		private class ViewHolder {
			TextView bn;
			TextView bt;
			TextView dt;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (convertView == null) {
				
				convertView = inflater.inflate(R.layout.bus_list_single, null);
				viewHolder = new ViewHolder();
				viewHolder.bn = (TextView) convertView.findViewById(R.id.txtime);
				viewHolder.bt = (TextView) convertView.findViewById(R.id.txname);
				viewHolder.dt = (TextView) convertView.findViewById(R.id.fdate);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			viewHolder.bn.setText(btime.get(position).toString());
			viewHolder.bt.setText(bname.get(position).toString());
			viewHolder.dt.setText(ddate.get(position).toString());
			
			return convertView;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> context, View view, int pos, long id) {
		
		String content= id1.get(pos);
		
//		new General(Home.this).Toaster(content);
		
		Intent map = new Intent(Home.this, Expanded.class);
		map.putExtra("id", content);
		startActivity(map);
	}
}

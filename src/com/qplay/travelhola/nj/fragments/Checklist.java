package com.qplay.travelhola.nj.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidhive.pushnotifications.R;

public class Checklist extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checklist);
		
		ListView lv = (ListView) findViewById(R.id.packinglist);
		List<String> list = new ArrayList<String>();
		
		list.add("Blanket");
		list.add("Sweater");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Checklist.this, android.R.layout.simple_list_item_1, list);
		lv.setAdapter(adapter);
		
	}

}

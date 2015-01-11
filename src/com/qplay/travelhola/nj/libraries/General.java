package com.qplay.travelhola.nj.libraries;

import android.content.Context;
import android.widget.Toast;

public class General {	
	
	public General() {
	}
	
	Context context;
	public General(Context context) {		
		this.context = context;		
	}
	
	
	
	public void Toaster(String s) {		
		Toast.makeText(context, s, Toast.LENGTH_LONG).show();		
	}

}

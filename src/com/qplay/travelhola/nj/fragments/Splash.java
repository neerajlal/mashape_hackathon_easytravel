package com.qplay.travelhola.nj.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.androidhive.pushnotifications.AlertDialogManager;
import com.androidhive.pushnotifications.ConnectionDetector;
import com.androidhive.pushnotifications.MainActivity;
import com.androidhive.pushnotifications.R;

public class Splash extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		final AlertDialogManager alert = new AlertDialogManager();
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {

				Intent intent = new Intent(Splash.this, Home.class);
				startActivity(intent);
				
				ConnectionDetector cd = new ConnectionDetector(Splash.this);
				// Check if Internet present
				if (!cd.isConnectingToInternet()) {
					// Internet Connection is not present
					alert.showAlertDialog(Splash.this,
							"Internet Connection Error",
							"Please connect to working Internet connection", false);
					// stop executing code by return
					return;
				}

			}
		}, 5000);

		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}

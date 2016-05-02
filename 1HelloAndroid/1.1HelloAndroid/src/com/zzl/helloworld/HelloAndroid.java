package com.zzl.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HelloAndroid extends Activity {
	private static final String TAG = "HelloAndroid";

	public void onCreate(Bundle savedInstanceState) {
		
		// Required call through to Activity.onCreate()
		// Restore any saved instance state
		super.onCreate(savedInstanceState);
		
		// Set up the application's user interface (content view)
		setContentView(R.layout.main);
		Log.d(TAG,"Helloworld");
	}
}
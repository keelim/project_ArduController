package com.keelim.hardware;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class GpslocActivity extends Activity{
    
   TextView tr,ts;
   double latitude,longitude;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsloc);
        GpslocateActivity gps = new GpslocateActivity(this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
        tr= findViewById(R.id.textView60);
        ts= findViewById(R.id.textView61);
        
    }

	@Override
	protected void onStart() {
		tr.setText("latitude "+ latitude);
		ts.setText("longitude "+ longitude);
		super.onStart();
	}
    
    }
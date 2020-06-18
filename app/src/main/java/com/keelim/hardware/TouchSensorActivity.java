package com.keelim.hardware;

import android.app.Activity;
import android.os.Bundle;

public class TouchSensorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new SingleTouchEventView(this, null));
		
	}
}

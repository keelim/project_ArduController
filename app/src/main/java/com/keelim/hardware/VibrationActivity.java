package com.keelim.hardware;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.Toast;

public class VibrationActivity extends Activity {
Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vibration);
		b1= findViewById(R.id.but101);
		b1.setOnClickListener(v -> {
			Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			v1.vibrate(400);
			Toast.makeText(getApplicationContext(), "Your Vibration Working Very Well",Toast.LENGTH_SHORT).show();
		});
	}
}

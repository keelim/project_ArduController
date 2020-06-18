package com.keelim.hardware;



import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ButtonTestingActivity extends Activity implements OnTouchListener {
private static final String TAG = "Touch" ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buttontesting);
		
		FrameLayout main = findViewById(R.id.main_view);

	}
		public boolean dispatchKeyEvent(KeyEvent event) {
			    int action = event.getAction();
			    int keyCode = event.getKeyCode();
			        switch (keyCode) {
			        case KeyEvent.KEYCODE_VOLUME_UP:
			            if (action == KeyEvent.ACTION_UP) {
			                Toast.makeText(getApplicationContext(), "PRESSED VOLUME UP BUTTON", Toast.LENGTH_SHORT).show();
			            }
			            return true;
			        case KeyEvent.KEYCODE_VOLUME_DOWN:
			            if (action == KeyEvent.ACTION_DOWN) {
			            	Toast.makeText(getApplicationContext(), "PRESSED VOLUME DOWN BUTTON", Toast.LENGTH_SHORT).show(); //TODO
			            }
			            return true;
			  
			      
			       
			        default:
			            return super.dispatchKeyEvent(event);
			        }
			    }
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

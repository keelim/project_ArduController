package keelim.ArduController;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class BluetoothService {
    private static final String TAG = "Bluetoth Service";
    private BluetoothAdapter btAdapter;

    private Activity mActivity;
    private Handler handler;

    public BluetoothService(Activity mActivity, Handler handler) {
        this.mActivity = mActivity;
        this.handler = handler;

        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean getDeviceStatus() { //블루투스 활성화
        Log.d(TAG, "Check the Bluetooth support");

        if (btAdapter == null) {
            Log.d(TAG, "Bluetooth is not available");

            return false;
        } else {
            Log.d(TAG, "Bluetooth is available");

            return true;
        }

    }

    public void enableBluetooth(){
        final int REQUEST_ENABLE_BT = 99;
        Log.i(TAG, "Check the enable Bluetooth");

        if(btAdapter.isEnabled()){
            Log.d(TAG, "Bluetooth Enable Now");
        } else{
            Log.d(TAG, "Bluetooth Enable Request");

            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mActivity.startActivityForResult(intent, REQUEST_ENABLE_BT);
        }
    }
}

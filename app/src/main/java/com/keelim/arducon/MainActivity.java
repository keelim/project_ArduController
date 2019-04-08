package com.keelim.arducon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int MESSAGE_READ = 2;
    private static final int CONNECT_FAILE = 3;
    private static final int CONNECT_SUCESS = 4;

    private Button button_change;
    private BluetoothAdapter bluetoothAdapter;
    private Button button_send;
    private EditText editText;
    private TextView textView;
    private ConnectedThread clientConnected;
    private BluetoothSocket bluetoothSocket; //socket check
    private UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //uuid 설정 값?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_change = (Button) findViewById(R.id.button_change);
        button_send = (Button) findViewById(R.id.button_send_main);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        button_change.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "화면 전환을 실행 합니다. ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ControllerActivity.class);
                startActivity(intent);

            }
        });

        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String string = String.valueOf(editText.getText());
                Log.d(TAG, "onClick: " + string);

                if (bluetoothSocket == null) {
                    Toast.makeText(MainActivity.this, "블루투스 연결을 확인하여 주세요", Toast.LENGTH_SHORT).show();

                } else {
                    clientConnected.write(string.getBytes());
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //todo
                    inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0); //todo?

                }
            }
        });
    }


    Handler mHandler = new Handler(new Handler.Callback() { //todo 핸들링 하는 곳 같긴 한데
        @Override
        public boolean handleMessage(Message msg) {
            StringBuilder mMessage = new StringBuilder(); //todo StringBuilder?

            switch (msg.what) {
                case MESSAGE_READ:
                    byte[] readBuffer = (byte[]) msg.obj; //버퍼를 만든다.
                    String tempMessage = new String(readBuffer, 0, msg.arg1);
                    mMessage.append(tempMessage);
                    int endOfLineIndex = mMessage.indexOf("\n");//todo?
                    if (endOfLineIndex > 0) {
                        String sbprint = mMessage.substring(0, endOfLineIndex);
                        Log.d(TAG, "handleMessage: " + sbprint);
                        textView.setText(sbprint); //todo String 가공 같은데
                    }
                    break;
                case CONNECT_FAILE:
                    Toast.makeText(MainActivity.this, "연결 실패", Toast.LENGTH_SHORT).show();
                    break;

                case CONNECT_SUCESS:
                    Toast.makeText(MainActivity.this, "연결 성공", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    });


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 메뉴를 선택할 수 있게 한다.
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    } //액션바를 사용할 수 있게 한다.

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id) {
            case R.id.action_main:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(this, "화면 전환을 실행 합니다.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            case R.id.action_controller:
                intent = new Intent(getApplicationContext(), ControllerActivity.class);
                Toast.makeText(this, "화면 전환을 실행 합니다. ", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;

            case R.id.action_bluetooth:
                intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent, REQUEST_ENABLE_BT); //todo?

            case R.id.action_setting:
                Toast.makeText(this, "준비 중입니다. ", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    } //액션바 옵셥 메뉴 선택 --> Onclick nono

    //innder class
    private class ConnectThread extends Thread { //bluetooth thread 연결 쓰레드
        final BluetoothSocket mmsocket;
        final BluetoothDevice mmdevice;

        private ConnectThread(BluetoothDevice device) {
            BluetoothSocket temp = null;
            mmdevice = device;

            try {
                temp = device.createRfcommSocketToServiceRecord(uuid);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmsocket = temp;
            Log.d(TAG, "ConnectedThread: " + mmsocket);

        }


        @Override
        public void run() {
            bluetoothAdapter.cancelDiscovery();

            try {
                mmsocket.connect();
                Log.d(TAG, "run: sucess" + mmsocket);
                bluetoothSocket = mmsocket;
                mHandler.sendEmptyMessage(CONNECT_SUCESS);

            } catch (IOException e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(CONNECT_FAILE);
                Log.d(TAG, "run: fail");

                try {
                    mmsocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            clientConnected = new ConnectedThread(mmsocket);
            clientConnected.start();
        }

        public void cancel() {
            try {
                mmsocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //inner class
    private class ConnectedThread extends Thread { //연결되는 쓰레드
        final BluetoothSocket mmsocket;
        final InputStream inputStream;
        final OutputStream outputStream;


        public ConnectedThread(BluetoothSocket socket) { //쓰레드의 생성
            mmsocket = socket;
            InputStream temp_input = null;
            OutputStream temp_output = null;

            try {
                temp_input = socket.getInputStream();
                temp_output = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            inputStream = temp_input;
            outputStream = temp_output;

        }

        @Override
        public void run() { //실행
            byte[] buffer = new byte[1024]; //버퍼를 만든다.
            int bytes;


            while (true) { //계속 해서 돈다. send Thread

                try {
                    bytes = inputStream.read(buffer);
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


        }

        void write(byte[] bytes) {
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void cancel() {
            try {
                mmsocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (clientConnected != null) {
            clientConnected.cancel();
        }
    } //액티비티 종료 되면 호출
}

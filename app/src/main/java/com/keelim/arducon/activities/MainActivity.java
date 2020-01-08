package com.keelim.arducon.activities;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.keelim.arducon.R;
import com.keelim.arducon.controller.ControllerActivity;
import com.keelim.arducon.devices.DeviceActivity;
import com.keelim.arducon.setting.SettingActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    private int mPariedDeviceCount = 0;
    // 사용자 정의 함수로 블루투스 활성 상태의 변경 결과를 App으로 알려줄때 식별자로 사용됨 (0보다 커야함)

    private Set<BluetoothDevice> mDevices;
    private BluetoothAdapter mBluetoothAdapter;
    private DrawerLayout drawerlayout;

    // 스마트폰과 페어링 된 디바이스간 통신 채널에 대응 하는 BluetoothSocket
    private BluetoothSocket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private String mStrDelimiter = "\n";
    private char mCharDelimiter = '\n';

    private Thread mWorkerThread;
    private byte[] readBuffer;
    private int readBufferPosition;

    private View drawerView;
    private TextView receiveString;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendButton = findViewById(R.id.sendButton);
        TextView sendString = findViewById(R.id.sendString);
        receiveString = findViewById(R.id.receive_string);
        drawerlayout = findViewById(R.id.drawerlayout);
        drawerView = findViewById(R.id.drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        AdView adView = findViewById(R.id.adView);
        NavigationView navigationView = findViewById(R.id.navigationView);

        sendButton.setOnClickListener(v -> {
            sendData(sendString.getText().toString());
            sendString.setText("");
            checkBluetooth();
        });

        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);//adMob

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            switch (id) {
                case R.id.drawer_account:
                    Toast.makeText(MainActivity.this, "계정 창 준비 중입니다. ", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.drawer_bug_report:
                    Intent intent_bugReport = new Intent(this, WebViewActivity.class);
                    startActivity(intent_bugReport); //webView page 이동을 할 것
                    break;
                case R.id.drawer_setting:
                    Intent intent_temp = new Intent(this, SettingActivity.class);
                    startActivity(intent_temp);
                    break;
                case R.id.drawer_controller:
                    Intent controller = new Intent(this, ControllerActivity.class);
                    startActivity(controller);
                    break;
                case R.id.drawer_devices:
                    Intent devices = new Intent(this, DeviceActivity.class);
                    startActivity(devices);
            }
            return false;
        });
    }


    public BluetoothDevice getDeviceFromBondedList(String name) {
        // BluetoothDevice : 페어링 된 기기 목록을 얻어옴.
        BluetoothDevice selectedDevice = null;
        // getBondedDevices 함수가 반환하는 페어링 된 기기 목록은 Set 형식이며,
        // Set 형식에서는 n 번째 원소를 얻어오는 방법이 없으므로 주어진 이름과 비교해서 찾는다.
        for (BluetoothDevice deivce : mDevices) {
            if (name.equals(deivce.getName())) {
                selectedDevice = deivce;
                break;
            }
        }
        return selectedDevice;
    }

    public void sendData(String msg) {
        msg += mStrDelimiter;  // 문자열 종료표시 (\n)
        try {
            // getBytes() : String을 byte로 변환
            // OutputStream.write : 데이터를 쓸때는 write(byte[]) 메소드를 사용함. byte[] 안에 있는 데이터를 한번에 기록해 준다.
            mOutputStream.write(msg.getBytes());  // 문자열 전송.
        } catch (Exception e) {  // 문자열 전송 도중 오류가 발생한 경우
            Toast.makeText(getApplicationContext(), "데이터 전송중 오류가 발생", Toast.LENGTH_LONG).show();
        }
    } //문자열 전송 함수

    //  실제 데이터 송수신을 위해서는 소켓으로부터 입출력 스트림을 얻고 입출력 스트림을 이용하여 이루어 진다.
    public void connectToSelectedDevice(String selectedDeviceName) {
        // BluetoothDevice 원격 블루투스 기기를 나타냄.
        //    /**
        //     * BluetoothDevice 로 기기의 장치정보를 알아낼 수 있는 자세한 메소드 및 상태값을 알아낼 수 있다.
        //     * 연결하고자 하는 다른 블루투스 기기의 이름, 주소, 연결 상태 등의 정보를 조회할 수 있는 클래스.
        //     * 현재 기기가 아닌 다른 블루투스 기기와의 연결 및 정보를 알아낼 때 사용.
        //     */
        BluetoothDevice mRemoteDevice = getDeviceFromBondedList(selectedDeviceName);
        UUID uuid = java.util.UUID.randomUUID(); //UUID 를 랜덤으로 만든다.
        try {
            // 소켓 생성, RFCOMM 채널을 통한 연결.
            // createRfcommSocketToServiceRecord(uuid) : 이 함수를 사용하여 원격 블루투스 장치와 통신할 수 있는 소켓을 생성함.
            // 이 메소드가 성공하면 스마트폰과 페어링 된 디바이스간 통신 채널에 대응하는 BluetoothSocket 오브젝트를 리턴함.
            mSocket = mRemoteDevice.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect(); // 소켓이 생성 되면 connect() 함수를 호출함으로써 두기기의 연결은 완료된다.
            // 데이터 송수신을 위한 스트림 얻기.
            // BluetoothSocket 오브젝트는 두개의 Stream을 제공한다.
            // 1. 데이터를 보내기 위한 OutputStrem
            // 2. 데이터를 받기 위한 InputStream
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();
            // 데이터 수신 준비.
            beginListenForData();

        } catch (Exception e) { // 블루투스 연결 중 오류 발생
            Toast.makeText(getApplicationContext(), "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
        }
    } //  connectToSelectedDevice() : 원격 장치와 연결하는 과정을 나타냄.

    public void beginListenForData() {
        final Handler handler = new Handler(); //쓰레드 사용에 있어서 필요함
        readBufferPosition = 0;                 // 버퍼 내 수신 문자 저장 위치.
        readBuffer = new byte[1024];            // 수신 버퍼.

        // 문자열 수신 쓰레드.
        mWorkerThread = new Thread(() -> {
            // interrupt() 메소드를 이용 스레드를 종료시키는 예제이다.
            // interrupt() 메소드는 하던 일을 멈추는 메소드이다.
            // isInterrupted() 메소드를 사용하여 멈추었을 경우 반복문을 나가서 스레드가 종료하게 된다.
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // InputStream.available() : 다른 스레드에서 blocking 하기 전까지 읽은 수 있는 문자열 개수를 반환함.
                    int byteAvailable = mInputStream.available();   // 수신 데이터 확인
                    if (byteAvailable > 0) {                        // 데이터가 수신된 경우.
                        byte[] packetBytes = new byte[byteAvailable];
                        // read(buf[]) : 입력스트림에서 buf[] 크기만큼 읽어서 저장 없을 경우에 -1 리턴.
                        mInputStream.read(packetBytes);

                        for (int i = 0; i < byteAvailable; i++) {
                            byte aByte = packetBytes[i];
                            if (aByte == mCharDelimiter) {
                                byte[] encodedBytes = new byte[readBufferPosition];
                                //  System.arraycopy(복사할 배열, 복사시작점, 복사된 배열, 붙이기 시작점, 복사할 개수)
                                //  readBuffer 배열을 처음 부터 끝까지 encodedBytes 배열로 복사.
                                System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                final String data = new String(encodedBytes, StandardCharsets.UTF_8);
                                readBufferPosition = 0;
                                // 수신된 문자열 데이터에 대한 처리.
                                handler.post(() -> {
                                    // mStrDelimiter = '\n';
                                    receiveString.setText(data + mStrDelimiter);
                                });
                            } else {
                                readBuffer[readBufferPosition++] = aByte;
                            }
                        }
                    }

                } catch (Exception e) {    // 데이터 수신 중 오류 발생.
                    Toast.makeText(getApplicationContext(), "데이터 수신 중 오류가 발생 했습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }// 데이터 수신(쓰레드 사용 수신된 메시지를 계속 검사함)

    public void selectDevice() {
        // 블루투스 디바이스는 연결해서 사용하기 전에 먼저 페어링 되어야만 한다
        // getBondedDevices() : 페어링된 장치 목록 얻어오는 함수.
        mDevices = mBluetoothAdapter.getBondedDevices();
        mPariedDeviceCount = mDevices.size();
        if (mPariedDeviceCount == 0)  // 페어링된 장치가 없는 경우.
            Toast.makeText(this, "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
        else {
            // 페어링된 장치가 있는 경우.
            // 각 디바이스는 이름과(서로 다른) 주소를 가진다. 페어링 된 디바이스들을 표시한다.
            List<String> listItems = new ArrayList<>();
            for (BluetoothDevice device : mDevices) {
                // device.getName() : 단말기의 Bluetooth Adapter 이름을 반환.
                listItems.add(device.getName());
            }
            listItems.add("취소");  // 취소 항목 추가.
            // CharSequence : 변경 가능한 문자열.
            // toArray : List형태로 넘어온것 배열로 바꿔서 처리하기 위한 toArray() 함수.
            String[] items = new String[listItems.size()];
            for (int i = 0; i < listItems.size(); i++) {
                items[i] = listItems.get(i);
            }
            // toArray 함수를 이용해서 size만큼 배열이 생성 되었다.
            new AlertDialog.Builder(this)
                    .setTitle("블루투스 장치 선택")
                    .setItems(items, (dialog, item) -> {
                        if (item == mPariedDeviceCount) { // 연결할 장치를 선택하지 않고 '취소' 를 누른 경우.
                            Toast.makeText(this, "연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();

                        } else { // 연결할 장치를 선택한 경우, 선택한 장치와 연결을 시도함.
                            connectToSelectedDevice(items[item].toString());
                        }
                    })
                    .setCancelable(false)
                    .show();
        }

    }// 블루투스 지원하며 활성 상태인 경우.

    public void checkBluetooth() { //블루투스가 활성화 되어 있는 지를 확인
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {  // 블루투스 미지원
            Toast.makeText(this, "기기가 블루투스를 지원하지 않습니다.", Toast.LENGTH_LONG).show();
        } else {
            if (!mBluetoothAdapter.isEnabled()) { // 블루투스 지원하며 비활성 상태인 경우.
                Toast.makeText(this, "현재 블루투스가 비활성 상태입니다.", Toast.LENGTH_LONG).show();

                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); // 블루투스 어댑터로 인텐트를 보낸다.
                startActivityForResult(enableBtIntent, 1);
            } else {// 블루투스 지원하며 활성 상태인 경우.
                selectDevice();
            }
        }
    }

    private void TotalExit() { //전체를 종료를 하는 로직
        mWorkerThread.interrupt(); // 데이터 수신 쓰레드 종료
        try {
            mInputStream.close();
            mSocket.close();
        } catch (IOException e) {
            Log.e("Error", e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //액티비티 결과를 보여준다.
        // startActivityForResult 를 여러번 사용할 땐 이런 식으로 switch 문을 사용하여 어떤 요청인지 구분하여 사용함.
        if (requestCode == 1) {
            switch (resultCode) {
                case RESULT_OK: //블루 투스 활성
                    selectDevice();
                    break;
                case RESULT_CANCELED: //블루 투스 종료
                    Toast.makeText(getApplicationContext(), "블루투스를 사용할 수 없어 프로그램을 종료합니다", Toast.LENGTH_LONG).show();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // menu  만들기
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            drawerlayout.openDrawer(drawerView);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        TotalExit();
        super.onDestroy();
    }


}


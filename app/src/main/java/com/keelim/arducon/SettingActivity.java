package com.keelim.arducon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    final String TAG = "SubActivity";
    final int REQUEST_ENABLE_BT = 1;
    ArrayAdapter<String> pairingAdapter, scanAdapter;
    ListView listView_pairing, listView_scan;
    ArrayList<String> arrayList_scan = new ArrayList<>();
    Button bt_scan;
    Switch switchOnOff;
    BluetoothAdapter myBluetoothAdapter;
    BluetoothDevice[] btArray = new BluetoothDevice[10];
    ArrayList<BluetoothDevice> btArray_scan = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bt_scan = (Button) findViewById(R.id.bt_scan);
        listView_pairing = (ListView) findViewById(R.id.listview_pairing);
        listView_scan = (ListView) findViewById(R.id.listview_scan);
        switchOnOff = (Switch) findViewById(R.id.switchOnOff);

        //Toolbar
        Toolbar mysubToolbar = (Toolbar) findViewById(R.id.my_subtoolbar);
        mysubToolbar.setTitle("");
        mysubToolbar.setNavigationIcon(R.drawable.ic_group_collapse_00);

        //Toolbar Back Icon Click event
        mysubToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //블루투스 어답터
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        /*
        액티비티가 시작되면서 블루투스가 켜져 있으면 즉시 페어링 목록을 가져 오도록 하자.
        이미 연결할 기기가 있으니 선택하고 메인으로 돌아 가면 된다.
        목록에 없다면 검색을 해서 목록에서 선택하기로 하자.
         */
        if (myBluetoothAdapter.isEnabled()) {
            switchOnOff.setChecked(true);

            //페어링된 디바이스 목록을 가져 온다.
            Set<BluetoothDevice> pairedDevices = myBluetoothAdapter.getBondedDevices();
            String[] strings = new String[pairedDevices.size()];
            int index = 0;
            // If there are paired devices
            if (pairedDevices.size() > 0) {
                // 루프를 돌면서 리스트뷰에 출력할 array에 계속 추가한다.
                for (BluetoothDevice device : pairedDevices) {
                    // Add the name and address to an array adapter to show in a ListView
                    btArray[index] = device;
                    strings[index] = device.getName();//기기 이름과 맥어드레스를 추가한다.
                    index++;
                }
                //페어링된 리스트뷰 어답터 세팅
                pairingAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, strings);
                listView_pairing.setAdapter(pairingAdapter);
            }
        }

        //페어링 리스트 항목 클릭
        listView_pairing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myBluetoothAdapter.cancelDiscovery(); //일단 검색 중단시킨다.
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                /*
                페어링목록에서 하나를 선택하면 해당 기기의 맥어드레스를 가지고 메인으로 돌아 간다. 이 액티비티는 종료한다.
                 */
                Intent deviceIntent = new Intent();
                deviceIntent.putExtra("name", btArray[i].getName());
                deviceIntent.putExtra("address", btArray[i].getAddress());//이게 중요하다.
                setResult(1, deviceIntent);
                finish();
            }
        });


        //블루투스 켜고 끄는 스위치
        switchOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchOnOff.isChecked()) { //ON일때
                    //블루투스를 활성화 합니다.
                    bluetoothEnable();
                    Toast.makeText(SettingActivity.this, "블루투스카 켜졌습니다. ", Toast.LENGTH_SHORT).show();
                } else { //OFF할때
                    if (myBluetoothAdapter.isEnabled()) {
                        myBluetoothAdapter.disable();
                        Toast.makeText(SettingActivity.this, "블루투스가 꺼졌습니다. ", Toast.LENGTH_SHORT).show();
                        //페어링목록과 검색목록을 크리어 해준다. 그런데 방법이 이게 맞는지 모르겠다.
                        //페어링된 리스트뷰 어답터 세팅
                        String[] strings = new String[0];
                        pairingAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, strings);
                        listView_pairing.setAdapter(pairingAdapter);
                        //검색 목록 초기화
                        arrayList_scan.clear();
                    }
                }
            }
        });


    /*
        페어링 목록에 원하는 기기가 없다면 주변 블루투스 디바이스 새로 검색한다.
         */
        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList_scan.clear(); //기존 데이터를 크리어 해준다.
                btArray_scan.clear();
                if (myBluetoothAdapter.isDiscovering()) {
                    myBluetoothAdapter.cancelDiscovery();
                }
                myBluetoothAdapter.startDiscovery();

                //새 기기 검색 리스트뷰 어답터 세팅
                scanAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, arrayList_scan);
                listView_scan.setAdapter(scanAdapter);

                if (!myBluetoothAdapter.isEnabled()) {
                    Toast.makeText(getApplicationContext(), "블루투스를 켜고 하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //검색된 새 디바이스 리스트 항목 클릭시
        listView_scan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myBluetoothAdapter.cancelDiscovery();//항상 기존 검색중인 것을 종료하고 검색해야 한다. 리소스문제
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                //Toast.makeText(getApplicationContext(), "디바이스: " + selectedItem, Toast.LENGTH_SHORT).show();
                Intent deviceIntent = new Intent();
                deviceIntent.putExtra("name", btArray_scan.get(i).getName());
                deviceIntent.putExtra("address", btArray_scan.get(i).getAddress());//이게 중요하다.
                setResult(1, deviceIntent);
                finish();
            }
        });

    /*
    Register the BroadcastReceiver
    이걸 여기다 하는게 맞을까? 버튼에 했더니 중복 등록되는 심각한 문제가 있다.
    액티비티가 시작되면 한번 등록하는 것으로 하자. 이 리시버는 새기기 검색할때 필요한  리시버이다.
    액티브가 종료될때 등록해제 해 줘야 한다.
     */
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        registerReceiver(mBroadcastReceiver2, filter); // Don't forget to unregister during onDestroy

        /*당장 필요하지 않으니 이것도 일단 주석처리 블루투스 상태 변화를 브로드캐스팅 하는 거*/
        //IntentFilter btIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        //registerReceiver(mBroadcastReceiver1, btIntent);//onDestory()에서 언레지스터하는 것을 추가해 줄것.
    }

    //스위치에서 켜기 하면 호출
    private void bluetoothEnable() {
        if (myBluetoothAdapter == null) { //굳이 이걸 여기다 할 필요가 없을듯 액티비티 시작할때 한번 해주면 될건데 그냥 패스~
            Toast.makeText(getApplicationContext(), "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_SHORT).show();
        } else {
            //블루투스가 꺼져 있으면 승인 요청창을 띄운다.
            if (!myBluetoothAdapter.isEnabled()) {
                Intent btEnableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(btEnableIntent, REQUEST_ENABLE_BT);
            }
            //블루투스 켜져 있으면?? 왜 이래야 하지? 기존의 것을 일단 정리하는 차원인가?
            if (myBluetoothAdapter.isEnabled()) {
                myBluetoothAdapter.disable();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                //블루투스가 활성화 되었다.
                Toast.makeText(getApplicationContext(), "블루투스가 켜졌습니다.", Toast.LENGTH_SHORT).show();

                //이건 이미 페어링되어 있는 기기 목록을 가져 오는것이다.(반복되네... 별도로 빼야 되나? 일단 이렇게 하자.)
                Set<BluetoothDevice> pairedDevices = myBluetoothAdapter.getBondedDevices();
                String[] strings = new String[pairedDevices.size()];
                int index = 0;
                // If there are paired devices
                if (pairedDevices.size() > 0) {
                    // 루프를 돌면서 리스트뷰에 출력할 array에 계속 추가한다.
                    for (BluetoothDevice device : pairedDevices) {
                        // Add the name and address to an array adapter to show in a ListView
                        btArray[index] = device;
                        strings[index] = device.getName();//기기 이름과 맥어드레스를 추가한다.
                        index++;
                        //페어링된 리스트뷰 어답터 세팅
                        pairingAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, strings);
                        listView_pairing.setAdapter(pairingAdapter);
                    }
                }

            } else if (resultCode == RESULT_CANCELED) {
                //사용자가 취소함
                switchOnOff.setChecked(false);
                Toast.makeText(this, "취소 되었습니다. ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
        새기기를 검색하게 되면 기기를 찾는대로 바로바로 목록 Array에 추가해 주는 역활을 한다.
         */
    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                btArray_scan.add(device);
                arrayList_scan.add(device.getName()); //맥어드레스도 출력되길 원하는 getAdress()추가해 주면 된다.
                scanAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //액티비티가 종료될때 등록한 리시버들을 해제 해 준다. 반드시 그래야 한다.
        if (mBroadcastReceiver2 != null)
            unregisterReceiver(mBroadcastReceiver2);
    }
}



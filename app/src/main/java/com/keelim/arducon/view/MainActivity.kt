package com.keelim.arducon.view

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import com.keelim.arducon.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_drawer.*
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var mDevices: Set<BluetoothDevice>
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var mSocket: BluetoothSocket
    private lateinit var mOutputStream: OutputStream
    private lateinit var mInputStream: InputStream
    private lateinit var mCharDelimiter: String
    private lateinit var mWorkerThread: Thread
    private lateinit var readBuffer: ByteArray
    private var readBufferPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID))
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        setSupportActionBar(main_toolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        main_sendButton.setOnClickListener {
            sendData(main_sendString.text.toString())
            checkBluetooth()
        }


        navigationView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.drawer_account -> Snackbar.make(drawerlayout, "기능 준비 중입니다", Snackbar.LENGTH_SHORT).show()

                R.id.drawer_bug_report ->  startActivity(Intent(this, WebViewActivity::class.java))

                R.id.drawer_setting -> startActivity(Intent(this, SettingActivity::class.java))

                R.id.drawer_controller -> startActivity(Intent(this, ControllerActivity::class.java))

                R.id.drawer_devices -> startActivity(Intent(this, DeviceActivity::class.java))
            }
            false
        }
    }

    private fun sendData(msg: String) {
        val msgData = "${msg}\n"
        try {
            // getBytes() : String을 byte로 변환
            // OutputStream.write : 데이터를 쓸때는 write(byte[]) 메소드를 사용함. byte[] 안에 있는 데이터를 한번에 기록해 준다.
            mOutputStream.write(msgData.toByteArray()) // 문자열 전송.
        } catch (e: Exception) { // 문자열 전송 도중 오류가 발생한 경우
            Toast.makeText(this, "데이터 전송 오류 발생", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkBluetooth() { //블루투스가 활성화 되어 있는 지를 확인
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (!bluetoothAdapter.isEnabled) { // 블루투스 지원하며 비활성 상태인 경우.
            Toast.makeText(this, "현재 블루투스가 비활성 상태입니다.", Toast.LENGTH_LONG).show()
            startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1) //블루투스를 요청한다.

        } else { // 블루투스 지원하며 활성 상태인 경우.
            selectDevice()
        }
    }

    private fun selectDevice() {
        mDevices = bluetoothAdapter.bondedDevices

        if (mDevices.isEmpty()) // 페어링된 장치가 없는 경우.
            Toast.makeText(this, "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show() else { // 페어링된 장치가 있는 경우.

            val deviceList: MutableList<String> = ArrayList()
            for (device in mDevices){
                deviceList.add(device.name)
            }

            deviceList.add("취소") // 취소 항목 추가.
            val items = arrayOfNulls<String>(deviceList.size)

            for (i in deviceList.indices){
                items[i] = deviceList[i]
            }

            AlertDialog.Builder(this)
                    .setTitle("블루투스 장치 선택")
                    .setItems(items) { _: DialogInterface?, item: Int ->
                        if (item == mDevices.size) { // 연결할 장치를 선택하지 않고 '취소' 를 누른 경우.
                            Toast.makeText(this, "연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_LONG).show()
                        } else { // 연결할 장치를 선택한 경우, 선택한 장치와 연결을 시도함.
                            connectToSelectedDevice(items[item].toString())
                        }
                    }
                    .setCancelable(false)
                    .show()
        }
    } // 블루투스 지원하며 활성 상태인 경우.

    private fun getDeviceFromBondedList(name: String): BluetoothDevice? { // BluetoothDevice : 페어링 된 기기 목록을 얻어옴.
        var selectedDevice: BluetoothDevice? = null
        for (device in mDevices) {
            if (name == device.name) {
                selectedDevice = device
                break
            }
        }
        return selectedDevice
    }


    private fun connectToSelectedDevice(selectedDeviceName: String) { // BluetoothDevice 원격 블루투스 기기를 나타냄.
        /**
         * BluetoothDevice 로 기기의 장치정보를 알아낼 수 있는 자세한 메소드 및 상태값을 알아낼 수 있다.
         * 연결하고자 하는 다른 블루투스 기기의 이름, 주소, 연결 상태 등의 정보를 조회할 수 있는 클래스.
         * 현재 기기가 아닌 다른 블루투스 기기와의 연결 및 정보를 알아낼 때 사용.
         */
        val uuid = UUID.randomUUID() //UUID 를 랜덤으로 만든다.
        try { // 소켓 생성, RFCOMM 채널을 통한 연결.
            // createRfcommSocketToServiceRecord(uuid) : 이 함수를 사용하여 원격 블루투스 장치와 통신할 수 있는 소켓을 생성함.
            // 이 메소드가 성공하면 스마트폰과 페어링 된 디바이스간 통신 채널에 대응하는 BluetoothSocket 오브젝트를 리턴함.
            mSocket = getDeviceFromBondedList(selectedDeviceName)!!.createRfcommSocketToServiceRecord(uuid)
            mSocket.connect() // 소켓이 생성 되면 connect() 함수를 호출함으로써 두기기의 연결은 완료된다.

            mOutputStream = mSocket.outputStream
            mInputStream = mSocket.inputStream
            beginListenForData()
        } catch (e: Exception) { // 블루투스 연결 중 오류 발생
            Toast.makeText(this, "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
        }
    }

    private fun beginListenForData() {
        val handler = Handler(mainLooper) //쓰레드 사용에 있어서 필요함
        readBufferPosition = 0 // 버퍼 내 수신 문자 저장 위치.
        readBuffer = ByteArray(1024) // 수신 버퍼.
        mWorkerThread = Thread(Runnable {  // 문자열 수신 쓰레드.

            while (!Thread.currentThread().isInterrupted) {
                try { // InputStream.available() : 다른 스레드에서 blocking 하기 전까지 읽은 수 있는 문자열 개수를 반환함.
                    val byteAvailable = mInputStream.available() // 수신 데이터 확인

                    if (byteAvailable > 0) { // 데이터가 수신된 경우.
                        val packetBytes = ByteArray(byteAvailable)
                        // read(buf[]) : 입력스트림에서 buf[] 크기만큼 읽어서 저장 없을 경우에 -1 리턴.

                        mInputStream.read(packetBytes)
                        for (i in 0 until byteAvailable) {
                            val aByte = packetBytes[i]
                            if (aByte == mCharDelimiter.toByte()) {
                                val encodedBytes = ByteArray(readBufferPosition)
                                //  System.arraycopy(복사할 배열, 복사시작점, 복사된 배열, 붙이기 시작점, 복사할 개수)
                                //  readBuffer 배열을 처음 부터 끝까지 encodedBytes 배열로 복사.
                                System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.size)
                                val data = String(encodedBytes, StandardCharsets.UTF_8)
                                readBufferPosition = 0
                                // 수신된 문자열 데이터에 대한 처리.
                                handler.post {
                                    main_receive_string.text = "${data}\n"
                                }
                            } else {
                                readBuffer[readBufferPosition++] = aByte
                            }
                        }
                    }
                } catch (e: Exception) { // 데이터 수신 중 오류 발생.
                    Toast.makeText(this, "데이터 수신 중 오류가 발생 했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        })
    } // 데이터 수신(쓰레드 사용 수신된 메시지를 계속 검사함)



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            when (resultCode) {
                Activity.RESULT_OK -> selectDevice()
                Activity.RESULT_CANCELED -> Toast.makeText(this, "블루투스를 사용할 수 없어 프로그램을 종료합니다", Toast.LENGTH_LONG).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (drawerlayout.isDrawerOpen(GravityCompat.START))
                    drawerlayout.closeDrawer(GravityCompat.START)
                else
                    drawerlayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        mWorkerThread.interrupt() // 데이터 수신 쓰레드 종료
        mInputStream.close()
        mSocket.close()

        finish()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }
}
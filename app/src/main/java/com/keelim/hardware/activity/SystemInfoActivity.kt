package com.keelim.hardware.activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_systeminfo.*

class SystemInfoActivity : AppCompatActivity() { //todo 여기부터 수정을 할 것
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_systeminfo)
    }

    override fun onStart() {
        val release = Build.VERSION.RELEASE
        val sdkVersion = Build.VERSION.SDK_INT
        val q = Build.VERSION.CODENAME
        val t = Build.VERSION.INCREMENTAL
        val m = Build.BOARD
        val h = Build.BOOTLOADER
        val j = Build.BRAND
        val k = Build.DEVICE
        val z = Build.HARDWARE
        val c = Build.MANUFACTURER
        val b = Build.SERIAL

        info_tv1!!.text = "Android SDK: $sdkVersion$q ($release)"
        info_tv2!!.text = "Build no :$t"
        info_tv3!!.text = "Name of underlying board $m"
        info_tv4!!.text = "System bootloader version number $h"
        info_tv5!!.text = "Brand of the software is  $j"
        info_tv6!!.text = "Name of the industrial design $k"
        info_tv7!!.text = "Name of the ternel $z"
        info_tv8!!.text = "Manufacturer of the product$c"
        info_tv9!!.text = "Hardware serial number $b"

        super.onStart()

        /* final TelephonyManager tm =(TelephonyManager)getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

			  String deviceid = tm.getDeviceId();
			   int phone = tm.getPhoneType();
Toast.makeText(getthis(),deviceid +"and"+ phone,Toast.LENGTH_SHORT).show();*/
    }
}
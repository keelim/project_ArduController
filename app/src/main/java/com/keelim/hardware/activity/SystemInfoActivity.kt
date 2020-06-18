package com.keelim.hardware.activity

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class SystemInfoActivity : AppCompatActivity() {
    private var t1: TextView? = null
    private var t2: TextView? = null
    private var t3: TextView? = null
    private var t4: TextView? = null
    private var t5: TextView? = null
    private var t6: TextView? = null
    private var t7: TextView? = null
    private var t8: TextView? = null
    private var t9: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_systeminfo)
        t1 = findViewById(R.id.textView1a)
        t2 = findViewById(R.id.textView2a)
        t3 = findViewById(R.id.textView3a)
        t4 = findViewById(R.id.textView4a)
        t5 = findViewById(R.id.textView5a)
        t6 = findViewById(R.id.textView6a)
        t7 = findViewById(R.id.textView7a)
        t8 = findViewById(R.id.textView8a)
        t9 = findViewById(R.id.textView9a)
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
        t3!!.text = "Name of underlying board $m"
        t4!!.text = "System bootloader version number $h"
        t5!!.text = "Brand of the software is  $j"
        t6!!.text = "Name of the industrial design $k"
        t7!!.text = "Name of the ternel $z"
        t8!!.text = "Manufacturer of the product$c"
        t9!!.text = "Hardware serial number $b"
        t2!!.text = "Build no :$t"
        t1!!.text = "Android SDK: $sdkVersion$q ($release)"
        super.onStart()

        /* final TelephonyManager tm =(TelephonyManager)getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

			  String deviceid = tm.getDeviceId();
			   int phone = tm.getPhoneType();
Toast.makeText(getthis(),deviceid +"and"+ phone,Toast.LENGTH_SHORT).show();*/
    }
}
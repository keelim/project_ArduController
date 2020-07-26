package com.keelim.hard

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.keelim.hard.view.BottomSheetDialog

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.io.File
import java.io.OutputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private val str: String? = null
    private lateinit var sm: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val list = sm.getSensorList(Sensor.TYPE_ALL)

        val i = 0
        val str = StringBuilder()
                .append("전체 센서 수: ").append(list.size).append("\n")

        for (s in list) {
            str.append("").append(i).append(" name: ").append(s.name).append("\n")
                    .append("power: ").append(s.power).append("\n").append("resolution: ").append(s.resolution).append("\n")
                    .append("range: ").append(s.maximumRange).append("\n").append("vendor: ").append(s.vendor).append("\n")
                    .append("min delay: ").append(s.minDelay).append("\n\n")
        }

        after_text.movementMethod = ScrollingMovementMethod()
        after_text.text = str.toString()

        floating.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog.instance
            bottomSheetDialog.show(supportFragmentManager, "bottomSheet")
        }


    }


    private fun makeFile(s: String?) {
        val filename = getString(R.string.file)
        val outputStream: OutputStream
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(s!!.toByteArray(Charset.defaultCharset()))
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val file = File(application.filesDir, filename)
        val message: String
        message = if (file.exists()) "정상적으로 완성되었습니다. " else "에러가 나서 다시 실행해주세요 "
        Snackbar.make(main_container, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun checkingFile() {
        val file = File(application.filesDir, getString(R.string.file))
        val message: String
        message = if (file.exists()) "정상적으로 완성되었습니다. " else "에러가 나서 다시 실행해주세요 "
        Snackbar.make(main_container, message, Snackbar.LENGTH_SHORT).show()
    }
}
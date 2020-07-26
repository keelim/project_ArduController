package com.keelim.hardware

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var text: TextView? = null
    private var sm: SensorManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        // SensorManager 객체를 getSystemService 메소드를 통해 얻음
        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // 모든 타입의 센서 목록을 얻음
        val sensors = sm!!.getSensorList(Sensor.TYPE_ALL)
        var i = 0

        val list = StringBuilder()
        .append("전체 센서 수: ").append(sensors.size).append("\n")
        for (s in sensors) {
            list.append("").append(i++).append(" name: ").append(s.name).append("\n")
                .append("power: ").append(s.power).append("\n").append("resolution: ")
                .append(s.resolution).append("\n").append("range: ")
                .append(s.maximumRange).append("\n").append("vendor: ").append(s.vendor)
                .append("\n").append("min delay: ").append(s.minDelay).append("\n\n")
        }
        text = findViewById<View>(R.id.after_text) as TextView
        // TextView에 텍스트 내용이 화면 크기를 넘어서 들어갈 때 스크롤 가능하게 만들기 위한 메소드 호출
        text!!.movementMethod = ScrollingMovementMethod()
        text!!.text = list.toString()
    }
}
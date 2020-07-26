package com.keelim.hardware;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView text;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        StringBuilder list = new StringBuilder();
        // SensorManager 객체를 getSystemService 메소드를 통해 얻음
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        // 모든 타입의 센서 목록을 얻음
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);

        list.append("전체 센서 수: ").append(sensors.size()).append("\n");
        int i = 0;
        for(Sensor s: sensors) {
            list.append("").append(i++).append(" name: ").append(s.getName()).append("\n").append("power: ").append(s.getPower()).append("\n").append("resolution: ").append(s.getResolution()).append("\n").append("range: ").append(s.getMaximumRange()).append("\n").append("vendor: ").append(s.getVendor()).append("\n").append("min delay: ").append(s.getMinDelay()).append("\n\n");
        }

        text = (TextView)findViewById(R.id.after_text);
        // TextView에 텍스트 내용이 화면 크기를 넘어서 들어갈 때 스크롤 가능하게 만들기 위한 메소드 호출
        text.setMovementMethod(new ScrollingMovementMethod());
        text.setText(list.toString());
    }
}
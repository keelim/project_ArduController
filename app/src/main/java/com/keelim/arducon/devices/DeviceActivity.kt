package com.keelim.arducon.devices

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.arducon.R
import kotlinx.android.synthetic.main.activity_device.*

class DeviceActivity : AppCompatActivity() {
    private val adapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
        Toast.makeText(this, "디바이스 목록 입니다.", Toast.LENGTH_SHORT).show()

        init()
        getData()
    }

    private fun init() {
        val linearLayoutManager = LinearLayoutManager(this)
        device_recycler.layoutManager = linearLayoutManager
        device_recycler.adapter = adapter
    }

    private fun getData() {
        val listTitle = listOf("국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립",
                "국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립")

        val listContent = listOf("이 꽃은 국화입니다.",
                "여기는 사막입니다.",
                "이 꽃은 수국입니다.",
                "이 동물은 해파리입니다.",
                "이 동물은 코알라입니다.",
                "이것은 등대입니다.",
                "이 동물은 펭귄입니다.",
                "이 꽃은 튤립입니다.",
                "이 꽃은 국화입니다.",
                "여기는 사막입니다.",
                "이 꽃은 수국입니다.",
                "이 동물은 해파리입니다.",
                "이 동물은 코알라입니다.",
                "이것은 등대입니다.",
                "이 동물은 펭귄입니다.",
                "이 꽃은 튤립입니다.")

        for (i in 0..listTitle.size) {
            val data = Data(title = listTitle.get(i), content = listContent.get(i))
            adapter.addItem(data)

            //어댑터의 값이 변경되었음을 알려준다.
            adapter.notifyDataSetChanged()

        }
    }

}





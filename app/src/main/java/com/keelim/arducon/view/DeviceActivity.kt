package com.keelim.arducon.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.arducon.R
import com.keelim.arducon.model.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_device.*

class DeviceActivity : AppCompatActivity(R.layout.activity_device) {
    private val adapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, "디바이스 목록 입니다.", Toast.LENGTH_SHORT).show()

        device_recycler.run {
            layoutManager = LinearLayoutManager(this@DeviceActivity)
            adapter = adapter
        }

        getData()
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
            val data = RecyclerAdapter.Data(title = listTitle[i], content = listContent[i])
            adapter.run {
                addItem(data)
                notifyDataSetChanged()
            }
        }

    }
}





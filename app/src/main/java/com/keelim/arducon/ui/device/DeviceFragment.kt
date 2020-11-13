package com.keelim.arducon.ui.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.arducon.R
import kotlinx.android.synthetic.main.activity_device.view.*

class DeviceFragment : Fragment() {
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_device, container, false)
        Toast.makeText(requireActivity(), "디바이스 목록 입니다.", Toast.LENGTH_SHORT).show()

        view.device_recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter()
        }
        getData()
        return view
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





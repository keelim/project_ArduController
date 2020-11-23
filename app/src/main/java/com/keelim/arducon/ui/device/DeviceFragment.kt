package com.keelim.arducon.ui.device

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.arducon.R
import com.keelim.arducon.databinding.FragmentDeviceBinding


class DeviceFragment : Fragment(R.layout.fragment_device) {
    private lateinit var adapter: RecyclerAdapter
    private var fragmentDeviceFragment: FragmentDeviceBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDeviceBinding.bind(view)
        fragmentDeviceFragment = binding

        Toast.makeText(requireActivity(), "디바이스 목록 입니다.", Toast.LENGTH_SHORT).show()

        binding.deviceRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter()
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

    override fun onDestroyView() {
        fragmentDeviceFragment = null
        super.onDestroyView()
    }

}





package com.keelim.arducon.ui.device

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.arducon.R
import com.keelim.arducon.databinding.FragmentDeviceBinding
import com.keelim.arducon.utils.toast


class DeviceFragment : Fragment(R.layout.fragment_device) {
    private var fragmentDeviceFragment: FragmentDeviceBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDeviceBinding.bind(view)
        fragmentDeviceFragment = binding

        requireActivity().toast("디바이스 목록 입니다.")

        binding.deviceRecycler.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = RecyclerAdapter()
        }
    }


    override fun onDestroyView() {
        fragmentDeviceFragment = null
        super.onDestroyView()
    }

}





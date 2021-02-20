package com.keelim.arducon.ui.controller

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.keelim.arducon.R
import com.keelim.arducon.databinding.FragmentControllerBinding
import com.keelim.arducon.utils.toast

class ControllerFragment : Fragment(R.layout.fragment_controller) {
    private var fragmentControllerFragment: FragmentControllerBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentControllerBinding.bind(view)
        fragmentControllerFragment = binding

        binding.centerBt.setOnClickListener {
            requireActivity().toast("center 버튼을 클릭하였습니다.")
        }
    }

    override fun onDestroyView() {
        fragmentControllerFragment = null
        super.onDestroyView()
    }
}
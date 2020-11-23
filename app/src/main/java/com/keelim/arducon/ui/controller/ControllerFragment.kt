package com.keelim.arducon.ui.controller

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.keelim.arducon.R
import com.keelim.arducon.databinding.FragmentControllerBinding

class ControllerFragment : Fragment(R.layout.fragment_controller) {
    private var fragmentControllerFragment: FragmentControllerBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentControllerBinding.bind(view)
        fragmentControllerFragment = binding

        binding.centerBt.setOnClickListener {
            Toast.makeText(requireActivity(), "center 버튼을 클릭하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        fragmentControllerFragment = null
        super.onDestroyView()
    }
}
package com.keelim.arducon.ui.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.keelim.arducon.R
import com.keelim.arducon.databinding.FragmentControllerBinding
import com.keelim.common.toast
import timber.log.Timber

class ControllerFragment : Fragment() {
    private lateinit var binding: FragmentControllerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentControllerBinding>(inflater, R.layout.fragment_controller, container, false)
            .apply {
                composeView.setContent {
                    MaterialTheme {
                        TextBluetooth()
                    }
                }
            }
        Timber.d("Controller View Created")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.centerBt.setOnClickListener {
            requireActivity().toast("center 버튼을 클릭하였습니다.")
        }
    }
}
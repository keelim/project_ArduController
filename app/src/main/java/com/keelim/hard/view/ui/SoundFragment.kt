package com.keelim.hard.view.ui

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import com.keelim.hard.model.FrKeyEventListener
import com.keelim.hard.view.MainActivity


class SoundFragment : Fragment(), FrKeyEventListener {
    private lateinit var activity: MainActivity
    override fun onAttach(context: Context) { // 작동을 할지는 잘 모르겠네
        super.onAttach(context)
        activity = context as MainActivity
        activity.setFrKeyEventListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_sound, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun FrtKeyEvent(event: KeyEvent?): Boolean {
        val action = event!!.action
        return when (event.keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                if (action == KeyEvent.ACTION_UP) {
                    Toast.makeText(requireContext(), "볼륨 업 버튼이 정상 작동 합니다.", Toast.LENGTH_SHORT).show()
                }
                true
            }

            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                if (action == KeyEvent.ACTION_DOWN) {
                    Toast.makeText(requireContext(), "볼륨 다운 버튼이 정상 작동 합니다.", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> false
        }
    }
}
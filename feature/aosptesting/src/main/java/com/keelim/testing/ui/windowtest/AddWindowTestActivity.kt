package com.keelim.testing.ui.windowtest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.keelim.common.toast
import com.keelim.testing.R
import com.keelim.testing.databinding.ActivityAddWindowBinding
import com.keelim.testing.ui.result.ResultActivity

import timber.log.Timber


class AddWindowTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddWindowBinding
    private lateinit var sampleDialog: AlertDialog
    private val viewModel by viewModels<AddWinodwViewModel>()

    private var resultArray = ArrayList<Long>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_window)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        toast("샘플 버튼을 눌러 기능을 확인 하세요")

        sampleDialog = makingDialog()

        binding.btnResult1.setOnClickListener {
            toast("1 초 뒤 테스트를 시작합니다.")
            Handler(Looper.getMainLooper()).postDelayed({
                test1Start()
            }, 1000)
        }

        binding.btnResult2.setOnClickListener {
            Snackbar.make(binding.root, "샘플 다이알로그를 실행 합니다.", Snackbar.LENGTH_SHORT).show()
            sampleDialog.show()
        }

        binding.btnResult3.setOnClickListener {
            Snackbar.make(binding.root, "샘플 다이알로그를 종료 합니다", Snackbar.LENGTH_SHORT).show()
            sampleDialog.dismiss()
        }
    }

    private fun test1Start() {
        firstChecking()
    }

    private fun firstChecking() {
        Timber.d("AlertDialog")
        AlertDialog.Builder(this)
                .setMessage("리얼 테스트를 실행 합니다. 카운터 횟수 인 ${viewModel.counter.value!!} 만큼 실행합니다")
                .setPositiveButton("YES") { _, _ ->
                    run {
                        toast("테스트를 실행합니다.")
                        measureTest1()
                    }
                }
                .setNegativeButton("No") { _, _ ->
                    run {
                        toast("어플리케이션을 재실행해주세요")
                    }
                }
                .create()
                .show()
    }

    private fun measureTest1() {
        val alert = AlertDialog.Builder(this)
                .setMessage("테스트 진행 중")
                .create()

        for (x in 0..viewModel.counter.value!!) {

            val start = System.nanoTime()
            Timber.d("dialog start time: $start")


            alert.show()
            alert.dismiss()

            val end = System.nanoTime()
            Timber.d("dialog end time: $end")

            val time = end - start
            Timber.d("test1 time:$time")

            val meanTime = time / 1000000000 //초

            resultArray.add(time)
            Thread.sleep(1)
        }

        endTest()
    }

    private fun endTest() {
        toast("테스트 결과 전송")
        Timber.d("sending test data")
        Intent(this, ResultActivity::class.java).apply {
            putExtra("result", resultArray)
            startActivity(this)
        }
    }

    private fun makingDialog(): AlertDialog {
        return AlertDialog.Builder(this)
                .setMessage("샘플 메시지를 눌려주셔서 감사합니다")
                .setPositiveButton("YES") { _, _ -> }
                .setNegativeButton("No") { _, _ ->
                    Snackbar.make(binding.root, "샘플 다이알로그를 종료 합니다", Snackbar.LENGTH_SHORT).show()
                }
                .create()
    }
}
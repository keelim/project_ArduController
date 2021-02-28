/*
 * Designed and developed by 2020 keelim (Jaehyun Kim)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
      Handler(Looper.getMainLooper()).postDelayed(
        {
          test1Start()
        },
        1000
      )
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

      val meanTime = time / 1000000000 // 초

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

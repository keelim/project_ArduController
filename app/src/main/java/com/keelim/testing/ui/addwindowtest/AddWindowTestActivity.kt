package com.keelim.testing.ui.addwindowtest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat
import com.google.android.material.snackbar.Snackbar
import com.keelim.testing.R
import com.keelim.testing.ui.result.ResultActivity
import kotlinx.android.synthetic.main.activity_test1.*


// todo 프로그레스 바로 테스트 실행을 알릴 것
// todo 테스트 결과를 파이어베이스로 보내서 확인을 할 것 어떻게? --> csv? 그러면 파일을 만들어서 저장소에 넣고 옮겨야 한다.
// todo 그럼 단순히 테스트를 하는데 로직을 너무 많이 짜는 것 같은데 --> 파일을 만들어서 그냥 메일로 쏠까? 이게 제일 간단하긴 하다.
// todo 어차피 PC 연결이니 컴퓨터 옮기자

class AddWindowTestActivity : AppCompatActivity() {

    private lateinit var sampleDialog: AlertDialog
    private var resultArray = ArrayList<Long>()
    var counter = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
        Toast.makeText(this, "샘플 버튼을 눌러 기능을 확인 하세요", Toast.LENGTH_SHORT).show()

        sampleDialog = makingDialog()
        value.text = "" + counter

        TextViewCompat.setAutoSizeTextTypeWithDefaults(value, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        btn_result1.setOnClickListener {
            Toast.makeText(this, "초 뒤 테스트를 시작합니다.", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                test1Start()
            }, 1000);
        }

        btn_result2.setOnClickListener {
            Snackbar.make(test1_container, "샘플 다이알로그를 실행 합니다.", Snackbar.LENGTH_SHORT).show()
            sampleDialog.show()
        }

        btn_result3.setOnClickListener {
            Snackbar.make(test1_container, "샘플 다이알로그를 종료 합니다", Snackbar.LENGTH_SHORT).show()
            sampleDialog.dismiss()
        }

        plusBtn.setOnClickListener {
            counter += 500
            value.text = "" + counter
        }

        minusBtn.setOnClickListener {

            counter -= 500
            if (counter < 0) counter = 0

            value.text = "" + counter
        }

    }

    private fun test1Start() {
        firstChecking()
    }

    private fun firstChecking() {
        AlertDialog.Builder(this)
                .setMessage("리얼 테스트를 실행 합니다. 카운터 횟수 인 $counter 만큼 실행합니다")
                .setPositiveButton("YES") { _, _ ->
                    run {
                        Toast.makeText(this@AddWindowTestActivity, "테스트를 실행합니다. ", Toast.LENGTH_SHORT).show()
                        measureTest1()
                    }
                }
                .setNegativeButton("No") { _, _ ->
                    run {
                        Toast.makeText(this@AddWindowTestActivity, "어플리케이션을 재 실행해주세요", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                .create()
                .show()

    }

    private fun measureTest1() {
        val alert = AlertDialog.Builder(this)
                .setMessage("테스트 진행 중")
                .create()

        for (x in 0..counter) {

            val start = System.nanoTime()
            Log.d("test1_start", "dialog start time: $start")


            alert.show()
            alert.dismiss()

            val end = System.nanoTime()
            Log.d("test1_start", "dialog end time: $end")

            val time = end - start
            Log.d("test1 time", "test1 time:$time")

            val meanTime = time / 1000000000 //초
//            Toast.makeText(this, "측정 시간 입니다. $meanTime", Toast.LENGTH_SHORT).show()
            resultArray.add(time)
            Thread.sleep(1)
        }

        endTest()
    }

    private fun endTest() {
        Toast.makeText(this, "테스트 결과를 결과페이지로 보냅니다. ", Toast.LENGTH_SHORT).show()
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
                    Snackbar.make(test1_container, "샘플 다이알로그를 종료 합니다", Snackbar.LENGTH_SHORT).show()
                }
                .create()
    }


}
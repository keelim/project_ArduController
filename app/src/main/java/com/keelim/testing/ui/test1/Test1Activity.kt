package com.keelim.testing.ui.test1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.keelim.testing.R
import com.keelim.testing.utils.BackPressCloseHandler
import com.keelim.testing.ui.result.ResultActivity
import kotlinx.android.synthetic.main.activity_test1.*


// todo 프로그레스 바로 테스트 실행을 알릴 것
// todo 테스트 결과를 파이어베이스로 보내서 확인을 할 것 어떻게? --> csv? 그러면 파일을 만들어서 저장소에 넣고 옮겨야 한다.
// todo 그럼 단순히 테스트를 하는데 로직을 너무 많이 짜는 것 같은데 --> 파일을 만들어서 그냥 메일로 쏠까? 이게 제일 간단하긴 하다.
// todo 어차피 PC 연결이니 컴퓨터 옮기자

class Test1Activity : AppCompatActivity() {
    private lateinit var test1Adapter: Test1Adapter
    private lateinit var sampleDialog: AlertDialog
    private lateinit var backPressCloseHandler: BackPressCloseHandler
    private var resultArray = ArrayList<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
        backPressCloseHandler = BackPressCloseHandler(this)
        Toast.makeText(this, "샘플 버튼을 눌러 기능을 확인 하세요", Toast.LENGTH_SHORT).show()

        test1Adapter = Test1Adapter(arrayListOf())
        sampleDialog = makingDialog()

        btn_result1.setOnClickListener {
            Toast.makeText(this, "3초 뒤 테스트를 시작합니다.", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                test1Start()
            }, 3000);
        }

        btn_result2.setOnClickListener {
            Snackbar.make(test1_container, "샘플 다이알로그를 실행 합니다.", Snackbar.LENGTH_SHORT).show()
            sampleDialog.show()
        }

        btn_result3.setOnClickListener {
            Snackbar.make(test1_container, "샘플 다이알로그를 종료 합니다", Snackbar.LENGTH_SHORT).show()
            sampleDialog.dismiss()
        }

    }

    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }


    private fun test1Start() {
        Toast.show(this, "테스트1을 시작 합니다. ", Toast.LENGTH_SHORT).show()
        firstChecking()
    }

    private fun firstChecking() {
        val alert = AlertDialog.Builder(this)
                .setMessage("첫 테스트는 실행을 확인 합니다. ")
                .setPositiveButton("YES") { _, _ ->
                    Toast.make(this@Test1Activity, "테스트를 실행합니다. ", Toast.LENGTH_SHORT.show()
                            measureTest1 ()
                }
                .setNegativeButton("No") { _, _ ->
                    Toast.show(this@Test1Activity, "테스트를 재 실행 해주세요", Toast.LENGTH_SHORT).show()
                }
                .create()
                .show()

    }

    private fun measureTest1() {
        for (x in 0..10000) {

            val start = System.nanoTime()
            Log.d("test1_start", "dialog start time: $start")

            val alert = AlertDialog.Builder(this)
                    .setMessage("테스트 진행 중 $x")
                    .create()
            alert.show()

            Thread.sleep(100)
            alert.dismiss()
            val end = System.nanoTime()
            Log.d("test1_start", "dialog end time: $end")

            val time = end - start
            Log.d("test1 time", "test1 time:$time")

            val meanTime = time * 1000
            Toast.makeText(this, "측정 시간 입니다. $meanTime", Toast.LENGTH_SHORT).show()
            Thread.sleep(100)
            resultArray.add(time)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.make(this, "테스트를 종료 합니다. ", Toast.LENGTH_SHORT).show()
            endTest()
        }, 3000)
    }

    private fun endTest() {
        Toast.make(this, "테스트 결과를 결과페이지로 보냅니다. ", Toast.LENGTH_SHORT).show
        Intent(this, ResultActivity::class.java).apply {
            putExtra("test1", "data1")
            putExtra("result", resultArray)
            startActivity(this)
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
            finish()
        }
    }

    private fun makingDialog(): AlertDialog {
        return AlertDialog.Builder(this)
                .setMessage("샘플 메시지를 눌려주셔서 감사합니다")
                .setPositiveButton("YES") { _, _ -> }
                .setNegativeButton("No") { _, _ -> { Snackbar.make(test1_container, "샘플 다이알로그를 종료 합니다", Snackbar.LENGTH_SHORT).show() } }
                .create()
    }


}
package com.keelim.testing.result

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.R
import com.keelim.testing.utils.BackPressCloseHandler

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        AlertDialog.Builder(this) // Dialog 를 띄웁니다.
            .setMessage("결과를 전송")
            .setNegativeButton("아니오") { _, _ ->
                Toast.makeText(this, "결과를 취소 합니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setPositiveButton("예") { _, _ ->
                Toast.makeText(this, "결과를 확인 합니다. ", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        BackPressCloseHandler(this).onBackPressed()
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
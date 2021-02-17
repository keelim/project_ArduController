package com.keelim.testing.ui.result

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.keelim.testing.R
import kotlinx.android.synthetic.main.activity_result.*
import java.io.BufferedWriter
import java.io.File
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*


class ResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var resultArray: ArrayList<Long>
    private lateinit var fab_open: Animation
    private lateinit var fab_close: Animation
    private var isFabOpen = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        ref = FirebaseStorage.getInstance().reference // 파이어베이스 스토리지지

        fab_open = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close);

        resultArray = intent.getSerializableExtra("result") as ArrayList<Long>
        // array List 를 받는다.

        result_recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ResultActivity)
            adapter = ResultAdapter(resultArray)
        }

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);


    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.fab1 -> {
                anim()
            }
            R.id.fab2 -> {
                makingData()
            }
            R.id.fab3 -> {
                uploadToServer()
            }
        }
    }

    fun anim() {
        if (isFabOpen) {
            fab2.startAnimation(fab_close)
            fab3.startAnimation(fab_close)
            fab2.isClickable = false
            fab3.isClickable = false
            isFabOpen = false
        } else {
            fab2.startAnimation(fab_open)
            fab3.startAnimation(fab_open)
            fab2.isClickable = true
            fab3.isClickable = true
            isFabOpen = true
        }
    }

    private lateinit var writer: BufferedWriter
    private lateinit var ref: StorageReference

    private fun uploadToServer() { // 서버에 올린다 파이어 스토어
        //if there is a file to upload
        val file = File(application.filesDir, getString(R.string.file))

        if (!file.exists()) {
            Toast.makeText(this, "데이터 파일을 확인해세요", Toast.LENGTH_SHORT).show()
            return
        }

        val filePath = Uri.fromFile(file)
        //displaying a progress dialog while upload is going on
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA).format(Date())
        val riversRef: StorageReference = ref.child("data/data${timestamp}.csv")

        riversRef.putFile(filePath)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "File Uploaded", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { exception ->
                    progressDialog.dismiss()
                    Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { taskSnapshot ->

                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    progressDialog.setMessage("Uploaded " + progress.toInt() + "%...")
                }
    }


    //todo progressing button 달기
    private fun makingData() {
        val lineSeparator = System.getProperty("line.separator")
        val fOut = openFileOutput(getString(R.string.file), MODE_PRIVATE)



        for (i in resultArray) {
            fOut.apply {
                write(i.toString().toByteArray(Charset.defaultCharset()))
                write(lineSeparator.toByteArray(Charset.defaultCharset()))
                fOut.flush()
            }
        }


        fOut.close()

        val file = File(application.filesDir, getString(R.string.file))
        if (file.exists()) Snackbar.make(result_container, "파일이 정상적으로 생성되었습니다. ", Snackbar.LENGTH_SHORT).show()
        else Snackbar.make(result_container, "파일 생성에 오류가 있습니다.", Snackbar.LENGTH_SHORT).show()
    }
}


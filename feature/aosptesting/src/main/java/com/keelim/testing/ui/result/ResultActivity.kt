package com.keelim.testing.ui.result

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.keelim.common.toast
import com.keelim.testing.R
import com.keelim.testing.databinding.ActivityResultBinding
import timber.log.Timber
import java.io.File
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*


class ResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var resultArray: ArrayList<Long>
    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    private lateinit var ref: StorageReference
    private lateinit var binding: ActivityResultBinding
    private var isFabOpen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ref = FirebaseStorage.getInstance().reference

        fabOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)

        resultArray = intent.getSerializableExtra("result") as ArrayList<Long>

        binding.resultRecycler.adapter = ResultAdapter().apply{
            setItem(resultArray.toList())
        }

        binding.fab1.setOnClickListener(this)
        binding.fab2.setOnClickListener(this)
        binding.fab3.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab1 -> anim()

            R.id.fab2 -> makingData()

            R.id.fab3 -> uploadToServer()
        }
    }

    private fun anim() {
        Timber.d("anim")
        if (isFabOpen) {
            binding.fab2.startAnimation(fabClose)
            binding.fab3.startAnimation(fabClose)
            binding.fab2.isClickable = false
            binding.fab3.isClickable = false
            isFabOpen = false
        } else {
            binding.fab2.startAnimation(fabOpen)
            binding.fab3.startAnimation(fabOpen)
            binding.fab2.isClickable = true
            binding.fab3.isClickable = true
            isFabOpen = true
        }
    }
    private fun uploadToServer() { // 서버에 올린다 파이어 스토어
        Timber.d("uploadToServer")
        val file = File(application.filesDir, getString(R.string.file))
        val filePath = Uri.fromFile(file)

        if (!file.exists()) {
            toast("데이터 파일을 확인하세요")
            return
        }

        val progressDialog = ProgressDialog(this).apply{
            setTitle("Uploading...")
            show()
        }

        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA).format(Date())
        val riversRef: StorageReference = ref.child("data/data${timestamp}.csv")

        riversRef.putFile(filePath)
                .addOnSuccessListener {
                    Timber.d("File Uploaded")
                    progressDialog.dismiss()
                    toast("File Uploaded")
                }

                .addOnFailureListener { exception ->
                    Timber.d("File Uploaded Failure")
                    progressDialog.dismiss()
                    toast("${exception.message}")
                }

                .addOnProgressListener { taskSnapshot ->
                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    progressDialog.setMessage("Uploaded " + progress.toInt() + "%...")
                }
    }

    private fun makingData() {
        Timber.d("making Data")
        val lineSeparator = System.getProperty("line.separator")
        val fOut = openFileOutput(getString(R.string.file), MODE_PRIVATE)

        resultArray.forEach { resultValue ->
            fOut.apply {
                write(resultValue.toString().toByteArray(Charset.defaultCharset()))
                write(lineSeparator!!.toByteArray(Charset.defaultCharset()))
                fOut.flush()
            }
        }
        fOut.close()

        val file = File(application.filesDir, getString(R.string.file))
        if (file.exists())
            toast("파일이 정상적으로 생성되었습니다.")
        else
            toast("파일 생성에 오류가 있습니다.")
    }
}



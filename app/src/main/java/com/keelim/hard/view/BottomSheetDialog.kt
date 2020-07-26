package com.keelim.hard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*


class BottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false)

        view.share!!.setOnClickListener(this)
        view.check!!.setOnClickListener(this)
        view.parser!!.setOnClickListener(this)
        view.make!!.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.make -> Toast.makeText(context, "준비 중 입니다.", Toast.LENGTH_SHORT).show()
            R.id.check -> Toast.makeText(context, "준비 중 입니다.", Toast.LENGTH_SHORT).show()
            R.id.parser -> Toast.makeText(context, "준비 중 입니다.", Toast.LENGTH_SHORT).show()
            R.id.share -> Toast.makeText(context, "준비 중 입니다.", Toast.LENGTH_SHORT).show() // 파이어 베이스 저장 및 보내기
        }
        dismiss()
    }

    companion object {
        val instance: BottomSheetDialog
            get() = BottomSheetDialog()
    }


}
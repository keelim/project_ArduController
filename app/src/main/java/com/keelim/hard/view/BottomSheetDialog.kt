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

        view.bluetoothLo!!.setOnClickListener(this)
        view.emailLo!!.setOnClickListener(this)
        view.cloudLo!!.setOnClickListener(this)
        view.msgLo!!.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.msgLo -> Toast.makeText(context, "Message", Toast.LENGTH_SHORT).show()
            R.id.emailLo -> Toast.makeText(context, "Email", Toast.LENGTH_SHORT).show()
            R.id.cloudLo -> Toast.makeText(context, "Cloud", Toast.LENGTH_SHORT).show()
            R.id.bluetoothLo -> Toast.makeText(context, "Bluetooth", Toast.LENGTH_SHORT).show()
        }
        dismiss()
    }

    companion object {
        val instance: BottomSheetDialog
            get() = BottomSheetDialog()
    }


}
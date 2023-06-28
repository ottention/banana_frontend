package com.example.banana

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button

class changeMainCardDialog (context: Context) {

    lateinit var listener: LessonDeleteDialogClickedListener
    lateinit var btnDelete: Button
    lateinit var btnCancel: Button

    interface LessonDeleteDialogClickedListener {
        fun onDeleteClicked()
    }

    private val dlg = Dialog(context)

    fun start() {
        /*타이틀바 제거*/
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*커스텀 다이얼로그 radius 적용*/
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dlg.setContentView(R.layout.dialog_change_main_card)

        btnDelete = dlg.findViewById(R.id.btn_yes_change)
        btnDelete.setOnClickListener {
            listener.onDeleteClicked()
            dlg.dismiss()
        }

        btnCancel = dlg.findViewById(R.id.btn_no_change)
        btnCancel.setOnClickListener {
            dlg.dismiss()
        }
        dlg.show()
    }
}
package com.example.banana

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class editTextDialog  (context: Context) {

    lateinit var listener: editTextDialog.editText
    lateinit var btnEdit: Button

    interface editText {
        fun edit(text : String)
    }

    private val dlg = Dialog(context)

    fun start() {
        /*타이틀바 제거*/
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*커스텀 다이얼로그 radius 적용*/
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.setContentView(R.layout.dialog_edit_text)

        btnEdit = dlg.findViewById(R.id.editBtn)
        btnEdit.setOnClickListener {
            val str = dlg.findViewById<EditText>(R.id.edited_text).text.toString()
            listener.edit(str)
            dlg.dismiss()
        }
        dlg.show()
    }
}
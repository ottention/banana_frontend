package com.example.banana

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button

class commentDeleteOrUpdateDialog (context: Context) {

    lateinit var listener: CommentListener
    lateinit var btnDelete: Button
    lateinit var btnUpdate: Button

    interface CommentListener {
        fun delete()
        fun update()
    }

    private val dlg = Dialog(context)

    fun start() {
        /*타이틀바 제거*/
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*커스텀 다이얼로그 radius 적용*/
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dlg.setContentView(R.layout.dialog_comment_delete_or_update)

        btnDelete = dlg.findViewById(R.id.btn_delte_comment)
        btnDelete.setOnClickListener {
            listener.delete()
            dlg.dismiss()
        }

        btnUpdate = dlg.findViewById(R.id.btn_update_comment)
        btnUpdate.setOnClickListener {
            listener.update()
            dlg.dismiss()
        }
        dlg.show()
    }
}
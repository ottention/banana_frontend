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
import android.widget.ImageButton
import android.widget.ImageView

class addLinkDialog (context: Context) {

    lateinit var listener: addLinkClickListner
    lateinit var btnSave: Button

    interface addLinkClickListner {
        fun save(icon : Drawable, link : String)
    }

    private val dlg = Dialog(context)

    fun start() {
        /*타이틀바 제거*/
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*커스텀 다이얼로그 radius 적용*/
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.setContentView(R.layout.dialog_add_link)

        btnSave = dlg.findViewById(R.id.addLinkBtn)
        btnSave.setOnClickListener {
            val twitterBtn = dlg.findViewById<ImageButton>(R.id.btn_twitter_link)
            val instagramBtn = dlg.findViewById<ImageButton>(R.id.btn_instagram_link)
            val ectBtn = dlg.findViewById<ImageButton>(R.id.btn_etc_link)
            val btns = listOf<ImageButton>(twitterBtn,instagramBtn, ectBtn)

            val icon = dlg.findViewById<ImageView>(R.id.link_icon)

            for(i in btns) {
                i.setOnClickListener {
                    icon.background = i.background
                }
            }

            listener.save(icon.background, dlg.findViewById<EditText>(R.id.link).text.toString())
            Log.d("image Check", icon.toString())
            dlg.dismiss()
        }
        dlg.show()
    }
}
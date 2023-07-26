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
import androidx.core.content.ContextCompat

class addLinkDialog (c_context: Context) {

    lateinit var listener: addLinkClickListner
    lateinit var btnSave: Button
    var context = c_context

    interface addLinkClickListner {
        fun save(index : Int, icon : Drawable, link : String)
    }

    private val dlg = Dialog(context)

    fun start() {
        /*타이틀바 제거*/
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*커스텀 다이얼로그 radius 적용*/
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.setContentView(R.layout.dialog_add_link)

        btnSave = dlg.findViewById(R.id.addLinkBtn)

        var index = 0
        val twitterBtn = dlg.findViewById<ImageButton>(R.id.btn_twitter_link)
        val instagramBtn = dlg.findViewById<ImageButton>(R.id.btn_instagram_link)
        val ectBtn = dlg.findViewById<ImageButton>(R.id.btn_etc_link)
        val icon = dlg.findViewById<ImageView>(R.id.link_icon)
        ectBtn.setOnClickListener {
            icon.background = ContextCompat.getDrawable(context, R.drawable.icon_etc_link)
            index = 0
        }

        twitterBtn.setOnClickListener {
            icon.background = ContextCompat.getDrawable(context, R.drawable.icon_twitter_link)
            index = 1
        }

        instagramBtn.setOnClickListener {
            icon.background = ContextCompat.getDrawable(context, R.drawable.icon_instagram_link)
            index = 2
        }


        btnSave.setOnClickListener {
            listener.save(index, icon.background, dlg.findViewById<EditText>(R.id.link).text.toString())
            Log.d("image Check", icon.toString())
            dlg.dismiss()
        }
        dlg.show()
    }
}
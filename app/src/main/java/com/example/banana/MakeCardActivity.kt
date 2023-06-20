package com.example.banana

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide


class MakeCardActivity : AppCompatActivity() {

    val PERMISSION_Album = 101 // 앨범 권한 처리
    val REQ_GALLERY = 1 // 앨범 권한 처리
    lateinit var cardView : FrameLayout

    inner class addIconListner : View.OnClickListener {
        val listener : OnTouchListener = DragListner()
        override fun onClick(v: View?) {

            val image = ImageView(v!!.context)
                    image.setImageDrawable(v.background)
                    // 위치 설정
                    image.x = 10f
                    image.y = 10f
                    var imageLayoutParams = LinearLayout.LayoutParams(100,100)
                    image.layoutParams = imageLayoutParams
                    image.setOnTouchListener(listener)
                    cardView.addView(image)
            }
    }

    inner class addTextBoxListner(type: String) : View.OnClickListener {
        val type = type
        val listener : OnTouchListener = DragListner()
        override fun onClick(v: View?) {

            val text = TextView(v!!.context)
            // 위치
            val x = 100.0f
            val y = 100.0f
            // 위치 설정
            text.x = x
            text.y = y
            // 상세설정
            text.setTextColor(Color.GRAY)
            text.setText("입력하세요")
            val font = ResourcesCompat.getFont(baseContext, R.font.pretendardregular)
            text.setTypeface(font)
            if(type == "h1") {
                text.setTextSize(16.0f) //임의
            }else {
                text.setTextSize(12.0f) //임의
            }

            var imageLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            text.layoutParams = imageLayoutParams
            text.setOnTouchListener(listener)
            cardView.addView(text)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_card)
        cardView = findViewById(R.id.card)
        val addIconListner : OnClickListener = addIconListner()
        val addTextBox1Lister : OnClickListener = addTextBoxListner("h1")
        val addTextBox2Lister : OnClickListener = addTextBoxListner("h2")

        var color : Color? = null

        val templateBtn01 = findViewById<Button>(R.id.btn_template01)
        val templateBtn02 = findViewById<Button>(R.id.btn_template02)
        val templateBtn03 = findViewById<Button>(R.id.btn_template03)
        val templateBtn04 = findViewById<Button>(R.id.btn_template04)

        // 임시로 1template이 선택되었다고 생각~
        var clickedBtnId = R.id.btn_template01
        templateBtn01.setBackgroundResource(R.drawable.btn_template)
        templateBtn01.setTextColor(Color.WHITE)

        var btnList = listOf<Button>(templateBtn01, templateBtn02, templateBtn03, templateBtn04)
        for(i in btnList) {
            i.setOnClickListener {
                for(z in btnList) {
                    z.setBackgroundColor(Color.TRANSPARENT)
                    z.setTextColor(Color.parseColor("#20000000"))
                }
                i.setBackgroundResource(R.drawable.btn_template)
                i.setTextColor(Color.WHITE)
                clickedBtnId = i.id
            }
        }


        val backWhiteBtn = findViewById<View>(R.id.card_color_white)
        val backPinkBtn = findViewById<View>(R.id.card_color_pink)
        val backYellowBtn = findViewById<View>(R.id.card_color_yellow)
        val backGreenBtn = findViewById<View>(R.id.card_color_green)
        val backSkyBtn = findViewById<View>(R.id.card_color_skyblue)
        val backNavyBtn = findViewById<View>(R.id.card_color_navy)
        val backPurpleBtn = findViewById<View>(R.id.card_color_purple)


        // 배경색 바꾸기
        var colorBtnList = listOf<View>(backWhiteBtn, backPinkBtn, backGreenBtn, backNavyBtn, backPurpleBtn, backSkyBtn, backYellowBtn)
        for(i in colorBtnList) {
            i.setOnClickListener {
                findViewById<View>(R.id.card).background = i.background
            }
        }

        // image 가져오기
        val getImageBtn = findViewById<ImageButton>(R.id.btn_getImage)
        getImageBtn.setOnClickListener {
            checkPermission()

        }

        // textBox 추가하기
        val h1addBtn = findViewById<View>(R.id.btn_h1)
        val h2addBtn = findViewById<View>(R.id.btn_h2)
        h1addBtn.setOnClickListener(addTextBox1Lister)
        h2addBtn.setOnClickListener(addTextBox2Lister)

        // 링크 추가하기
        val linkIconBtn = findViewById<ImageButton>(R.id.btn_goLink)


        // 아이콘 기능 추가하기
        val notionIconBtn = findViewById<ImageButton>(R.id.icon_notion)
        val bookMarkIconBtn = findViewById<ImageButton>(R.id.icon_bookMark)
        val phoneBookIconBtn = findViewById<ImageButton>(R.id.icon_phoneBook)
        val mailIconBtn = findViewById<ImageButton>(R.id.icon_mail)
//        val mapIconBtn = findViewById<ImageButton>(R.id.btn_goMap)
        val cardIconBtn = findViewById<ImageButton>(R.id.icon_creditCard)
        val checkIconBtn = findViewById<ImageButton>(R.id.icon_check)
        val timeIconBtn = findViewById<ImageButton>(R.id.icon_time)
        val phoneIconBtn = findViewById<ImageButton>(R.id.icon_phone)
        val graduationIconBtn = findViewById<ImageButton>(R.id.icon_graduation)
        val workIconBtn = findViewById<ImageButton>(R.id.icon_work)
        var iconBtnList = listOf<ImageButton>(notionIconBtn, bookMarkIconBtn, phoneBookIconBtn, mailIconBtn, mailIconBtn, cardIconBtn, checkIconBtn, timeIconBtn, phoneIconBtn, graduationIconBtn, workIconBtn)

        for(i in iconBtnList) {
            i.setOnClickListener(addIconListner)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val listener : OnTouchListener = DragListner()

        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == REQ_GALLERY) {
            data?.data?.let {
                uri ->
                val imageUri : Uri? = data?.data
                if(imageUri != null) {
                    // 위치
                    val x = 100.0f
                    val y = 100.0f
                    // 크기
                    val width = 100
                    val height = 100
                    val image = ImageView(this)
                    // 위치 설정
                    image.x = x
                    image.y = y
                    var imageLayoutParams = LinearLayout.LayoutParams(width,height)
                    image.layoutParams = imageLayoutParams
                    Glide.with(this).load(imageUri).into(image)
                    findViewById<FrameLayout>(R.id.card).addView(image)
                    image.setOnTouchListener(listener)
                }
            }
        }
    }

    fun checkPermission() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if(permission == PackageManager.PERMISSION_GRANTED) {
            permissionGranted(PERMISSION_Album)
        }else {
            permissionDenied(PERMISSION_Album)
        }
    }

    private fun permissionGranted(requestCode: Int) {
        when (requestCode) {
            PERMISSION_Album -> openGallery()
        }
    }

    private fun permissionDenied(requestCode: Int) {
        when (requestCode) {
            PERMISSION_Album -> Toast.makeText(
                this,
                "저장소 권한을 승인해야 앨범에서 이미지를 불러올 수 있습니다.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun openGallery() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, REQ_GALLERY)
    }
}

class DragListner : OnTouchListener {

    var moveX = 0f
    var moveY = 0f

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        val parentWidth = (v!!.parent as ViewGroup).width // 부모 View 의 Width
        val parentHeight = (v.parent as ViewGroup).height // 부모 View 의 Height

        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                moveX = v!!.x - event.x
                moveY = v!!.y - event.y
            }

            MotionEvent.ACTION_MOVE -> {
                v!!.x = v.getX() + (event.getX()) - (v.getWidth()/2)
                v!!.y = v.getY() + (event.getY()) - (v.getHeight()/2)

            }

            MotionEvent.ACTION_UP -> {
                if(v.getX() < 0){
                    v.x = 0f
                }else if((v.getX() + v.getWidth()) > parentWidth){
                    v.x = (parentWidth - v.getWidth()).toFloat();
                }

                if(v.getY() < 0){
                    v.y = 0f
                }else if((v.getY() + v.getHeight()) > parentHeight){
                    v.y = (parentHeight - v.getHeight()).toFloat();

                }

            }
        }

        return true
    }
}
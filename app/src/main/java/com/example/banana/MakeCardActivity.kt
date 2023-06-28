package com.example.banana

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.PointF
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginLeft
import com.bumptech.glide.Glide


class MakeCardActivity : AppCompatActivity() {

    val PERMISSION_Album = 101 // 앨범 권한 처리
    val REQ_GALLERY = 1 // 앨범 권한 처리
    // 현재 보이는 카드 면
    lateinit var cardView : FrameLayout
    // 뒷면
    lateinit var cardView_back : FrameLayout
    // 앞면
    lateinit var cardView_front : FrameLayout

    // 제스쳐 감지
    private var v : View? = null
    private var matrix //기존 매트릭스
            : Matrix? = null
    private val savedMatrix //작업 후 이미지에 매핑할 매트릭스
            : Matrix? = null
    private var midPoint //두손가락 터치 시 중신 포인트
            : PointF? = null
    private var oldDistance //터치 시 두손가락 사이의 거리
            = 0f

    private var oldDegree = 0.0 // 두손가락의 각도

    enum class TOUCH_MODE {
        NONE,  // 터치 안했을 때
        SINGLE,  // 한손가락 터치
        MULTI //두손가락 터치
    }
    var touchMode : TOUCH_MODE? = null

    inner class iconLongClickListner : OnLongClickListener {
        override fun onLongClick(v: View?): Boolean {

            val deleteBtn : Button = Button(baseContext)
            deleteBtn.setBackgroundResource(R.drawable.icon_deleteicons)

            deleteBtn.setOnClickListener {
                removeView(v!!) // 해당 뷰 삭제
            }

            deleteBtn.x = (v!!.x + 0.5).toFloat()
            deleteBtn.y = (v!!.y + 0.5).toFloat()

            var layoutPr = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            deleteBtn.layoutParams = layoutPr
            addView(deleteBtn)

            return true
        }
    }

    inner class DragListner : OnTouchListener {

        var moveX = 0f
        var moveY = 0f

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            val action = event!!.action
            val parentWidth = (v!!.parent as ViewGroup).width // 부모 View 의 Width
            val parentHeight = (v.parent as ViewGroup).height // 부모 View 의 Height

            when(event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    moveX = v!!.x - event.x
                    moveY = v!!.y - event.y
                }

                MotionEvent.ACTION_POINTER_DOWN -> {
                    if(event.pointerCount == 2) {
                        touchMode = MakeCardActivity.TOUCH_MODE.MULTI
                        var fx = (event.getX(0) - event.getX(1)).toDouble()
                        var fy = event.getY(0) - event.getY(1)
                        val oldDistance = Math.sqrt(fx*fx + fy*fy).toFloat()


                        if (oldDistance > 5f) {
                            savedMatrix!!.set(matrix)

                            val x: Float = (event.getX(0) - event.getX(1))/ 2
                            val y: Float = (event.getY(0) - event.getY(1)) / 2
                            val midPoint = PointF(x,y)

                            val radian = Math.atan2(
                                (event.y - midPoint!!.y).toDouble(),
                                (event.x - midPoint!!.x).toDouble()
                            )
                            oldDegree = radian * 180 / Math.PI
                        }
                    }
                }

                MotionEvent.ACTION_MOVE -> {
                    if(touchMode == MakeCardActivity.TOUCH_MODE.MULTI) {

                        var fx = (event.getX(0) - event.getX(1)).toDouble()
                        var fy = event.getY(0) - event.getY(1)
                        val newDistance = Math.sqrt(fx*fx + fy*fy).toFloat()
                        if (newDistance > 5f) {
                            matrix!!.set(savedMatrix)
                            val scale: Float = newDistance / oldDistance
                            matrix!!.postScale(scale, scale, midPoint!!.x, midPoint!!.y)
                            val nowRadian = Math.atan2(event!!.y.toDouble() - midPoint!!.y, event!!.x.toDouble() - midPoint!!.x)
                            val nowDegress = nowRadian * 180 / Math.PI
                            val degree = (nowDegress - oldDegree) as Float
                            matrix!!.postRotate(degree, midPoint!!.x, midPoint!!.y)
                            v!!.matrix.set(matrix)
                        }

                    }else {
                        v!!.x = v.getX() + (event.getX()) - (v.getWidth()/2)
                        v!!.y = v.getY() + (event.getY()) - (v.getHeight()/2)

                    }
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

                MotionEvent.ACTION_POINTER_UP -> {
                    touchMode = TOUCH_MODE.NONE
                }

            }

            return true
        }
    }
    inner class addIconListner : View.OnClickListener {

        val listener : OnTouchListener = DragListner()
        override fun onClick(v: View?) {

                    val image = ImageView(v!!.context)
                    image.setImageDrawable(v.background)
                    if(image!=null) {
                        image.drawable.setTint(Color.BLACK)
                    }
                    // 위치 설정
                    image.x = 10f
                    image.y = 10f
                    var imageLayoutParams = LinearLayout.LayoutParams(100,100)
                    image.layoutParams = imageLayoutParams
                    image.scaleType = ImageView.ScaleType.MATRIX
                    image.setOnTouchListener(listener)
                    addView(image)
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
            text.setTextColor(Color.BLACK)
            text.setText("텍스트를 입력하세요")
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
            addView(text)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_card)
        val parent_layout = findViewById<LinearLayout>(R.id.make_card_layout)
        cardView_front = findViewById(R.id.card)
        cardView_back = FrameLayout(this)

        // 뒷면 기본 크기 설정
        cardView_back.x = cardView_front.x
        cardView_back.y = cardView_front.y
        cardView_back.elevation = 10f

        var imageLayoutParams = LinearLayout.LayoutParams(cardView_front.width,cardView_front.height)
        imageLayoutParams.setMargins(20, 0, 20, 0)
        cardView_back.layoutParams = imageLayoutParams
        cardView_back.setBackgroundColor(Color.BLACK)
        parent_layout.addView(cardView_back)
        cardView_back.visibility = View.GONE

        val addIconListner : OnClickListener = addIconListner()
        val editIconListner = iconLongClickListner()
        val addTextBox1Lister : OnClickListener = addTextBoxListner("h1")
        val addTextBox2Lister : OnClickListener = addTextBoxListner("h2")
        // 입력받는 방법을 관리하는 Manager객체를  요청하여 InputMethodmanager에 반환한다.
        val imm = getSystemService(INPUT_METHOD_SERVICE);
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
                // 여기도 수정 ~~~~~~
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
        val mapIconBtn = findViewById<ImageButton>(R.id.btn_goMap)
        val cardIconBtn = findViewById<ImageButton>(R.id.icon_creditCard)
        val checkIconBtn = findViewById<ImageButton>(R.id.icon_check)
        val timeIconBtn = findViewById<ImageButton>(R.id.icon_time)
        val phoneIconBtn = findViewById<ImageButton>(R.id.icon_phone)
        val graduationIconBtn = findViewById<ImageButton>(R.id.icon_graduation)
        val workIconBtn = findViewById<ImageButton>(R.id.icon_work)
        var iconBtnList = listOf<ImageButton>(mapIconBtn, notionIconBtn, bookMarkIconBtn, phoneBookIconBtn, mailIconBtn, mailIconBtn, cardIconBtn, checkIconBtn, timeIconBtn, phoneIconBtn, graduationIconBtn, workIconBtn)

        for(i in iconBtnList) {
            i.setOnClickListener(addIconListner)
            i.setOnLongClickListener(editIconListner)
        }

        val mainCardCheckBox = findViewById<CheckBox>(R.id.isMainChecked)
        if(mainCardCheckBox.isChecked) {
            mainCardCheckBox.isEnabled = false
        }

        mainCardCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(mainCardCheckBox.isChecked){
                val dlg = changeMainCardDialog(this)
                dlg.listener = object: changeMainCardDialog.LessonDeleteDialogClickedListener {
                    override fun onDeleteClicked() {
                        // main card 변경
                    }
                }
                dlg.start()
            }
        }

        val goEditTag = findViewById<Button>(R.id.goeditTag)
        goEditTag.setOnClickListener {
            val bottomSheet = addTagbottomDialog(this)
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)

        }

        val rotationBtn = findViewById<ImageButton>(R.id.rotation_btn)
        rotationBtn.setOnClickListener {
            if(cardView_front.visibility == View.VISIBLE) {
                flipCard(this, cardView_back, cardView_front)
                Toast.makeText(this, "뒷면으로 뒤집기", Toast.LENGTH_SHORT).show()
            }else {
                flipCard(this, cardView_front , cardView_back)
                Toast.makeText(this, "앞면으로 뒤집기", Toast.LENGTH_SHORT).show()

            }
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
                    addView(image)
                    image.setOnTouchListener(listener)
                    image.scaleType = ImageView.ScaleType.MATRIX
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


    // visibleView 가져오는 것, inVisibleView 뒤로 보내는 것
    fun flipCard(context: Context, visibleView: View, inVisibleView: View) {
        try {
            visibleView.visibility = View.VISIBLE
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_out
                ) as AnimatorSet
            flipOutAnimatorSet.setTarget(inVisibleView)
            val flipInAnimationSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
                ) as AnimatorSet
            flipInAnimationSet.setTarget(visibleView)
            flipOutAnimatorSet.start()
            flipInAnimationSet.start()
            flipInAnimationSet.doOnEnd {
                inVisibleView.visibility = View.GONE
            }
        } catch (e: Exception) {

        }
    }

    fun addView(element : View) {
        if(cardView_front.visibility == View.VISIBLE) {
            cardView_front.addView(element)
        }else {
            cardView_back.addView(element)
        }
    }

    fun removeView(element : View) {
        if(cardView_front.visibility == View.VISIBLE) {
            cardView_front.removeView(element)
        }else {
            cardView_back.removeView(element)
        }
    }
}

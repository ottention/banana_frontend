package com.example.banana.activity

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.app.ActionBar.LayoutParams
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnTouchListener
import android.view.ViewGroup
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
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banana.R
import com.example.banana.adapter.KeywordViewAdapter
import com.example.banana.addLinkDialog
import com.example.banana.addTagbottomDialog
import com.example.banana.auth.authApplication
import com.example.banana.changeMainCardDialog
import com.example.banana.data.Contents
import com.example.banana.data.Coordinate
import com.example.banana.data.Image
import com.example.banana.data.Link
import com.example.banana.data.cardRequestData
import com.example.banana.data.getCardResponseModel
import com.example.banana.editTextDialog
import com.example.banana.fragment.CardDetailFragment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import com.example.banana.viewModel.AddCardViewModel
import com.example.banana.viewModel.DetailCardDataViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kakao.sdk.common.KakaoSdk.type
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.Companion.createFormData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream



var CardAPI : API = RetrofitInstance.retrofitInstance().create(API::class.java)

class MakeCardActivity : AppCompatActivity() {


    private lateinit var addCardViewModel: AddCardViewModel

    private var doubleClickFlag = 0
    private var CARD_ID : Long = -1
    private val CLICK_DELAY: Long = 250
    val PERMISSION_Album = 101 // 앨범 권한 처리
    val REQ_GALLERY = 1 // 앨범 권한 처리
    // 앞면
    lateinit var cardView : FrameLayout
    // 뒷면
    lateinit var cardView_back : FrameLayout
    // tmeplate
    var template : Int = 1

    // templateColor
    var front_color : String = "#FFFFFF"
    var back_color : String = "#FFFFFF"

    // contentList
    var frontHList : HashMap<TextView, String> = HashMap()
    var backHList : HashMap<TextView, String> = HashMap()

    // imageList
    var frontImageList : HashMap<String, ImageView> = HashMap()
    var backImageList : HashMap<String, ImageView> = HashMap()

    // linkList
    var frontLinkList : HashMap<ImageView, String> = HashMap()
    var backLinkList : HashMap<ImageView, String> = HashMap()


    var frontContents = arrayListOf<Contents>()
    var frontLinks = arrayListOf<Link>()
    var frontImages = arrayListOf<Image>()
    var frontTemplateColor = ""
    var backContents = arrayListOf<Contents>()
    var backLinks = arrayListOf<Link>()
    var backImages = arrayListOf<Image>()
    var backTemplateColor = ""

    var isPublic : Boolean = false
    var isPresent : Boolean = false


    // 제스쳐 감지
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

    inner class DragListner : OnTouchListener {

        var moveX = 0f
        var moveY = 0f

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            val action = event!!.action
            val parentWidth = (v!!.parent as ViewGroup).width // 부모 View 의 Width
            val parentHeight = (v.parent as ViewGroup).height // 부모 View 의 Height

            when(event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    doubleClickFlag++
                    val handler = Handler()
                    val clickRunnable = Runnable {
                        doubleClickFlag = 0
                        moveX = v!!.x - event.x
                        moveY = v!!.y - event.y
                    }

                    if( doubleClickFlag == 1 ) {
                        handler.postDelayed( clickRunnable, CLICK_DELAY );
                    }else if( doubleClickFlag == 2 ) {
                        doubleClickFlag = 0;
                        Log.d("tagged", "twice")
                        v.setOnTouchListener(null)

                        if (v is TextView) {
                            val editOrDeleteBtn = View.inflate(baseContext, R.layout.delete_n_edit_btn,null)

                            val width = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                50f,
                                resources.displayMetrics
                            ).toInt()

                            var layoutPr = LinearLayout.LayoutParams(width,50)
                            editOrDeleteBtn.layoutParams = layoutPr

                            editOrDeleteBtn.x = (v!!.x + v.width).toFloat()
                            editOrDeleteBtn.y = (v!!.y - v.height).toFloat()
                            addView(editOrDeleteBtn)
                            editOrDeleteBtn.findViewById<ImageButton>(R.id.text_edit_btn).setOnClickListener {
                                editText(v)
                                removeView(editOrDeleteBtn)
                                v.setOnTouchListener(DragListner())
                            }

                            editOrDeleteBtn.findViewById<ImageButton>(R.id.icon_delete_btn).setOnClickListener {
                                removeView(v!!) // 해당 뷰 삭제
                                removeView(editOrDeleteBtn)
                            }

                            cardView.setOnClickListener { removeOpBtn(v, editOrDeleteBtn) }
                            cardView_back.setOnClickListener { removeOpBtn(v, editOrDeleteBtn) }
                        }else{
                            val deleteBtn : Button = Button(baseContext)
                            deleteBtn.setBackgroundResource(R.drawable.icon_deleteicons)

                            deleteBtn.setOnClickListener {
                                removeView(v!!) // 해당 뷰 삭제
                                removeView(deleteBtn)
                            }

                            deleteBtn.x = (v!!.x + v.width).toFloat()
                            deleteBtn.y = (v!!.y - 1).toFloat()

                            var layoutPr = LinearLayout.LayoutParams(40,30)
                            deleteBtn.layoutParams = layoutPr
                            addView(deleteBtn)


                            cardView.setOnClickListener { removeOpBtn(v, deleteBtn) }
                            cardView_back.setOnClickListener { removeOpBtn(v, deleteBtn) }

                        }



                    }
                }

                MotionEvent.ACTION_POINTER_DOWN -> {
                    if(event.pointerCount == 2) {
                        touchMode = TOUCH_MODE.MULTI
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
                    if(touchMode == TOUCH_MODE.MULTI) {

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
                    val imageD = v.background
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

            val bytes = ByteArrayOutputStream()
            v.background!!.toBitmap().compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path: String = MediaStore.Images.Media.insertImage(
                baseContext.getContentResolver(),
                v.background!!.toBitmap(),
                "Title",
                null
            )

            val file: File = File(path)
            var inputStream: InputStream? = null
            try {
                inputStream =
                    baseContext.getContentResolver().openInputStream(Uri.parse(path))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream)

                    if(cardView.visibility == View.VISIBLE) {
                        frontImageList.put(Uri.fromFile(file).toString(), image)
                    }else {
                        backImageList.put(Uri.fromFile(file).toString(), image)
                    }
                    addView(image)
            }
    }


    inner class addTextBoxListner(type: String) : View.OnClickListener {
        val type : String = type
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

            if(cardView.visibility == View.VISIBLE) {
                frontHList.put(text, type)
            }else {
                backHList.put(text, type)
            }

            var imageLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            text.layoutParams = imageLayoutParams
            text.setOnTouchListener(listener)
            addView(text)
        }
    }

    fun updateText(c : Context, front_card : FrameLayout, back_card : FrameLayout,contents : List<Contents>) {
        val listener : OnTouchListener = DragListner()

        for(i in contents) {
            val text = TextView(c)
            var textLayoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            text.layoutParams = textLayoutParams
            text.setOnTouchListener(listener)
            // 상세설정
            text.setTextColor(Color.BLACK)
            text.x = i.coordinate!!.xAxis!!
            text.y = i.coordinate!!.yAxis!!
            text.text = i.content

            if(i.contentSize == "h1") {
                text.setTextSize(12.0f)
            }else {
                text.setTextSize(16.0f)
            }
            if (text.getParent() != null)
                (text.getParent() as ViewGroup).removeView(
                    text
                )
            if(i.isFront) {
                front_card.addView(text)
                frontHList.put(text, i.contentSize!!)
            }else {
                back_card.addView(text)
                backHList.put(text, i.contentSize!!)
            }
        }
    }

    fun updateLink(c : Context, front_card : FrameLayout, back_card : FrameLayout,contents : List<Link>){

        for(i in contents) {
            val listener : OnTouchListener = DragListner()
            val image = ImageView(c)
            image.isClickable = true

            val inputStream = MakeCardActivity().contentResolver.openInputStream(i.link.toUri())
            val drawable = Drawable.createFromStream(inputStream, i.link.toUri().toString())
            image.setImageDrawable(drawable)
            image.x = i.coordinate!!.xAxis!!
            image.y = i.coordinate!!.yAxis!!
            var link_intent = Intent(Intent.ACTION_VIEW, Uri.parse((i.link)))
            image.setOnClickListener {
                startActivity(link_intent)
            }
            var imageLayoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
            image.layoutParams = imageLayoutParams
            image.scaleType = ImageView.ScaleType.MATRIX
            image.setOnTouchListener(listener)

            if(i.isFront) {
                front_card.addView(image)
                frontLinkList.put(image, i.linkText)
            }else {
                back_card.addView(image)
                backLinkList.put(image, i.linkText)
            }
        }
    }

    fun updateImage(c : Context, front_card : FrameLayout, back_card : FrameLayout, images : List<Image>){
        val listener : OnTouchListener = DragListner()

        for(i in images) {
            val image = ImageView(c)
            image.setOnTouchListener(listener)
            //크기 설정
            var imageLayoutParams = LinearLayout.LayoutParams(100, 100)
            image.layoutParams = imageLayoutParams
            image.x = i.coordinate!!.xAxis!!
            image.y = i.coordinate!!.yAxis!!
            Glide.with(c!!).load(i.imageUrl).into(image)

            if (image.getParent() != null)
                (image.getParent() as ViewGroup).removeView(
                    image
                )
            if(i.isFront) {
                front_card.addView(image)
                frontImageList.put(i.imageUrl.toString(), image)
            }else {
                back_card.addView(image)
                backImageList.put(i.imageUrl.toString(), image)
            }
        }
    }


    fun makeUI(front_card : FrameLayout, back_card : FrameLayout, cardData : getCardResponseModel) {

        if(cardData.frontTemplateColor != null) {
            // template 색깔 저장
            front_card.setBackgroundColor(Color.parseColor(cardData.frontTemplateColor))
            back_card.setBackgroundColor(Color.parseColor(cardData.backTemplateColor))
            front_color = cardData.frontTemplateColor!!
            back_color = cardData.backTemplateColor!!
            var f_listOfImages : ArrayList<Image> = cardData.frontImages!!
            var b_listOfImages : ArrayList<Image> = cardData.backImages!!
            var f_listOfContents : ArrayList<Contents> = cardData.frontContents!!
            var b_listOfContents : ArrayList<Contents> = cardData.backContents!!
            var f_listOfLinks : ArrayList<Link> = cardData.frontLinks!!
            var b_listOfLinks : ArrayList<Link> = cardData.backLinks!!

            updateImage(baseContext!!,front_card, back_card, f_listOfImages)
            updateImage(baseContext!!,front_card, back_card, b_listOfImages)
            updateText(baseContext!!,front_card, back_card, f_listOfContents)
            updateText(baseContext!!,front_card, back_card, b_listOfContents)
            updateLink(baseContext!!,front_card, back_card, f_listOfLinks)
            updateLink(baseContext!!,front_card, back_card, b_listOfLinks)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_card)
        addCardViewModel = ViewModelProvider(this).get(AddCardViewModel::class.java)

        cardView = findViewById(R.id.front_card)
        cardView_back = findViewById(R.id.back_card)

        var cardData : getCardResponseModel? = intent.getSerializableExtra("cardData") as getCardResponseModel?
        var cardId = intent.getLongExtra("cardId", -1 ) as Long
        if((!cardId.equals(-1)) && (cardData != null)) {
            Log.d("Card check : get update", cardData.toString())
//            Toast.makeText(this, cardData.toString() + " : 명함 수정 중", Toast.LENGTH_SHORT).show()
            CARD_ID = intent.getLongExtra("cardId", -1)
            makeUI(cardView, cardView_back, cardData)
        }else {
            CARD_ID = -1
        }

        val addIconListner : OnClickListener = addIconListner()
        val addTextBox1Lister : OnClickListener = addTextBoxListner("h1")
        val addTextBox2Lister : OnClickListener = addTextBoxListner("h2")
        // 입력받는 방법을 관리하는 Manager객체를  요청하여 InputMethodmanager에 반환한다.
        val templateBtn01 = findViewById<Button>(R.id.btn_template01)
        val templateBtn02 = findViewById<Button>(R.id.btn_template02)
        val templateBtn03 = findViewById<Button>(R.id.btn_template03)
        val templateBtn04 = findViewById<Button>(R.id.btn_template04)

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

                if(i == templateBtn02){
                    cardView.background = ContextCompat.getDrawable(this, R.drawable.template_1_front)
                    cardView_back.background = ContextCompat.getDrawable(this, R.drawable.template_1_back)
                    template = 2
                }else if(i == templateBtn03) {
                    cardView.background = ContextCompat.getDrawable(this, R.drawable.template_2_front)
                    cardView_back.background = ContextCompat.getDrawable(this, R.drawable.template_2_back)
                    template = 3
                }else if(i == templateBtn04) {
                    cardView.background = ContextCompat.getDrawable(this, R.drawable.template_3_front)
                    cardView_back.background = ContextCompat.getDrawable(this, R.drawable.template_3_back)
                    template = 4
                }else {
                    template = 1
                }

                cardView.elevation = 20f
                cardView_back.elevation = 20f
            }
        }

        val backWhiteBtn = findViewById<View>(R.id.card_color_white)
        val backPinkBtn = findViewById<View>(R.id.card_color_pink)
        val backYellowBtn = findViewById<View>(R.id.card_color_yellow)
        val backGreenBtn = findViewById<View>(R.id.card_color_green)
        val backSkyBtn = findViewById<View>(R.id.card_color_skyblue)
        val backNavyBtn = findViewById<View>(R.id.card_color_navy)
        val backPurpleBtn = findViewById<View>(R.id.card_color_purple)

        val colorList = listOf<String>("#FFFFFF", "#FFC5C5", "#FFE4B1", "#8EC385", "#A3C8FF", "#6063B6", "#B591F1")

        // 배경색 바꾸기
        var colorBtnList = listOf<View>(backWhiteBtn, backPinkBtn, backYellowBtn, backGreenBtn, backSkyBtn, backNavyBtn, backPurpleBtn)

        for(i : Int in 0..colorBtnList.size-1) {
            colorBtnList[i].setOnClickListener {
                if(cardView.visibility == View.VISIBLE) {
                    if(template == 1) {
                        cardView.background = (colorBtnList[i].background)
                        front_color = colorList[i]
                    }else {
                        cardView.background.setTint(Color.parseColor(colorList[i]))
                        front_color = colorList[i]
                    }
                } else {
                    if(template == 1) {
                        cardView_back.background = (colorBtnList[i].background)
                        back_color = colorList[i]
                    }else {
                        cardView_back.background.setTint(Color.parseColor(colorList[i]))
                        back_color = colorList[i]
                    }
                }
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
        linkIconBtn.setOnClickListener {
            var makeCardActivity = this
            val dlg = addLinkDialog(makeCardActivity)
            dlg.listener = object: addLinkDialog.addLinkClickListner {
                override fun save(icon : Drawable, link : String) {
                    addLink(icon, link)
                    Log.d("image check - main", icon.toString())
                }
            }
            dlg.start()
        }

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
        }

        val mainCardCheckBox = findViewById<CheckBox>(R.id.isMainChecked)
        if(mainCardCheckBox.isChecked) {
            mainCardCheckBox.isEnabled = false
        }

        mainCardCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(mainCardCheckBox.isChecked){
                val dlg = changeMainCardDialog(this)
                dlg.listener = object: changeMainCardDialog.LessonDeleteDialogClickedListener {
                    override fun onchangeMainClicked() {
                        isPresent = mainCardCheckBox.isChecked
                        mainCardCheckBox.isEnabled = false
                    }

                    override fun onNotChangeMainClicked() {
                        mainCardCheckBox.isChecked = false
                        Log.d("mainCardCheck",mainCardCheckBox.isChecked.toString() )

                    }
                }
                dlg.start()
            }
        }

        //  태그 수정하기 버튼
        val goEditTag = findViewById<Button>(R.id.goeditTag)
        goEditTag.setOnClickListener {
            val bottomSheet = addTagbottomDialog(this)
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }



        // 회전 시키기
        val rotationBtn = findViewById<ImageButton>(R.id.rotation_btn)
        rotationBtn.setOnClickListener {
            if(cardView.visibility == View.VISIBLE) {
                flipCard(this, cardView_back, cardView)
                Toast.makeText(this, "뒷면으로 뒤집기", Toast.LENGTH_SHORT).show()
            }else {
                flipCard(this, cardView , cardView_back)
                Toast.makeText(this, "앞면으로 뒤집기", Toast.LENGTH_SHORT).show()

            }
        }

        // 공개, 비공개 체크
        val isPublicCheckBox = findViewById<CheckBox>(R.id.isPublic)
        isPublicCheckBox.setOnClickListener {
            isPublic = isPublicCheckBox.isChecked
        }

        // 저장하기
        val saveBtn = findViewById<Button>(R.id.save_button)
        saveBtn.setOnClickListener {

            // 저장할때 마다 비우고 새로 저장
            frontContents = arrayListOf()
            frontImages= arrayListOf()
            frontLinks = arrayListOf()
            backContents = arrayListOf()
            backImages= arrayListOf()
            backLinks = arrayListOf()
            var tagList = arrayListOf<String>("태그 1", "태그 2")

            var colorD : ColorDrawable = cardView.background as ColorDrawable
            frontTemplateColor = colorD.toString()
            colorD = cardView_back.background as ColorDrawable
            backTemplateColor = colorD.toString()

            // text값 저장
            for((text, type) in frontHList){
                var contentCoordinate = Coordinate(text.x, text.y)
                var contentItem = Contents(text.text.toString(), type, contentCoordinate, true)
                frontContents.add(contentItem)
            }

            // text값 저장
            for((text, type) in backHList){
                var contentCoordinate = Coordinate(text.x, text.y)
                var contentItem = Contents(text.text.toString(), type, contentCoordinate, false)
                frontContents.add(contentItem)
            }

            // image값 저장
            for((uploadFile, image) in frontImageList){
                var imageCoordinate = Coordinate(image.x, image.y)
                var imageItem = Image(true, uploadFile, imageCoordinate)
                frontImages.add(imageItem)
            }

            // image값 저장
            for((uploadFile, image) in backImageList){
                var imageCoordinate = Coordinate(image.x, image.y)
                var imageItem = Image(true, uploadFile, imageCoordinate)
                backImages.add(imageItem)
            }

            // link값 저장
            for((icon_image, link) in backLinkList){
                var linkCoordinate = Coordinate(icon_image.x, icon_image.y)
                var link = Link(link, drawalbeToUri(icon_image), true, linkCoordinate)
                frontLinks.add(link)
            }

            // link값 저장
            for((icon_image, link) in backLinkList){
                var linkCoordinate = Coordinate(icon_image.x, icon_image.y)
                var link = Link(link, drawalbeToUri(icon_image), false, linkCoordinate)
                backLinks.add(link)
            }


            var cardDatav2 = cardRequestData(
                isPublic, isPresent, frontContents, frontLinks, frontImages, front_color, backContents, backLinks, backImages, back_color, tagList)
            Log.d("datas : ",cardDatav2.toString())
            saveCard(cardDatav2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val listener : OnTouchListener = MakeCardActivity().DragListner()

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

                    val file: File = File(imageUri.path)
                    var inputStream: InputStream? = null
                    try {
                        inputStream = baseContext.getContentResolver().openInputStream(imageUri)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream)
                    val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), byteArrayOutputStream.toByteArray());

                    if(cardView.visibility == View.VISIBLE) {
                        val uploadFile: MultipartBody.Part =
                            createFormData("frontImage", file.name , requestBody)
                        frontImageList.put(imageUri.toString(), image)
                    }else {
                        val uploadFile: MultipartBody.Part =
                            createFormData("backImage", file.name, requestBody)
                        backImageList.put(imageUri.toString(), image)
                    }
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

    fun addLink(link_icon : Drawable, link : String) {

        val listener : OnTouchListener = DragListner()
        val image = ImageView(this)
        image.setImageDrawable(link_icon)
        if(image!=null) {
            image.drawable.setTint(Color.BLACK)
        }
        // 위치 설정
        image.x = 10f
        image.y = 10f

        var imageLayoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        image.layoutParams = imageLayoutParams
        image.scaleType = ImageView.ScaleType.MATRIX
        image.setOnTouchListener(listener)

        if(cardView.visibility == View.VISIBLE) {
            frontLinkList.put(image, link)
        }else {
            backLinkList.put(image, link)
        }

        addView(image)
    }


    fun drawalbeToUri(image : ImageView) : String{

        val bytes = ByteArrayOutputStream()
        image.background!!.toBitmap().compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(
            baseContext.getContentResolver(),
            image.background!!.toBitmap(),
            "Title",
            null
        )

        val file: File = File(path)

        return Uri.fromFile(file).toString()
    }


    fun addView(element : View) {
        if(cardView.visibility == View.VISIBLE) {
            cardView.addView(element)
        }else {
            cardView_back.addView(element)
        }
    }

    fun removeView(v : View) {
        if(cardView.visibility == View.VISIBLE) {
            cardView.removeView(v)
        }else {
            cardView_back.removeView(v)
        }
    }

    fun editText(v : TextView){
        val dlg = editTextDialog(this)
        dlg.listener = object: editTextDialog.editText {
            override fun edit(text: String) {
                v.text = text
            }
        }
        dlg.start()
    }

    fun removeOpBtn(v : View, element : View) {
        v.setOnTouchListener(DragListner())
        if(cardView.visibility == View.VISIBLE) {
            cardView.removeView(element)
        }else {
            cardView_back.removeView(element)
        }

    }

    fun saveCard(card : cardRequestData) {
        if(CARD_ID == -1L) {
            Log.d("CARD check - new",card.toString() )
            addCardViewModel.saveCardData(card)
        }else {
            Toast.makeText(this, CARD_ID.toString() + "수정중~", Toast.LENGTH_SHORT).show()
            Log.d("CARD check - update",card.toString() )
            addCardViewModel.updateCardData(CARD_ID,card)
        }
    }
}

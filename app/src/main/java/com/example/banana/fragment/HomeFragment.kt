package com.example.banana.fragment

import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.banana.activity.CreateQRActivity
import com.example.banana.R
import com.example.banana.data.ResponseGetQRCode
import com.example.banana.databinding.FragmentHomeBinding
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import android.widget.*
import com.bumptech.glide.Glide
import com.example.banana.data.*
import com.example.banana.viewModel.HomeViewModel
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {


    private lateinit var retAPI : API
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeViewModel
    private lateinit var cardIdlist : getCardResponseModel

    fun newInstance() : HomeFragment{
        return HomeFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retAPI = RetrofitInstance.retrofitInstance().create(API::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val front_card = view!!.findViewById<FrameLayout>(R.id.home_card_1)
        val back_card = view!!.findViewById<FrameLayout>(R.id.home_card_2)

        //초기 card 01
        binding.btnCard01.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnCard01.setTextColor(Color.parseColor("#ffffff"))

//        viewModel.showCard1()


        binding.btnCard01.setOnClickListener {



            binding.btnCard01.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnCard01.setTextColor(Color.parseColor("#ffffff"))

            binding.btnCard02.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard02.setTextColor(Color.parseColor("#f0f0f0"))

            binding.btnCard03.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard03.setTextColor(Color.parseColor("#f0f0f0"))
            viewModel.showCard1()
            cardIdlist = viewModel.getCard.value!!

            makeUI(cardIdlist,front_card,back_card)

        }

        //card02 클릭
        binding.btnCard02.setOnClickListener {
            binding.btnCard02.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnCard02.setTextColor(Color.parseColor("#ffffff"))

            binding.btnCard01.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard01.setTextColor(Color.parseColor("#f0f0f0"))

            binding.btnCard03.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard03.setTextColor(Color.parseColor("#f0f0f0"))
            viewModel.showCard2()

            cardIdlist = viewModel.getCard.value!!

            makeUI(cardIdlist,front_card,back_card)


        }

        //card03 클릭
        binding.btnCard03.setOnClickListener {

            binding.btnCard03.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnCard03.setTextColor(Color.parseColor("#ffffff"))

            binding.btnCard02.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard02.setTextColor(Color.parseColor("#f0f0f0"))

            binding.btnCard01.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard01.setTextColor(Color.parseColor("#f0f0f0"))
//
            viewModel.showCard3()

            cardIdlist = viewModel.getCard.value!!

            makeUI(cardIdlist,front_card,back_card)

        }


        return view

    }

    fun makeUI(cardData : getCardResponseModel, front_card: FrameLayout, back_card: FrameLayout) {

//        Log.d("TAG", cardData.toString())
//        val front_card = view!!.findViewById<FrameLayout>(R.id.detailed_card)
//        val back_card = view!!.findViewById<FrameLayout>(R.id.detailed_card_back)

        if(cardData.frontTemplateColor != null) {
//            val front_card = view!!.findViewById<FrameLayout>(R.id.detailed_card)
//            val back_card = view!!.findViewById<FrameLayout>(R.id.detailed_card_back)
            // template 색깔 저장
            front_card.setBackgroundColor(Color.parseColor(cardData.frontTemplateColor))
            back_card.setBackgroundColor(Color.parseColor(cardData.backTemplateColor))

            // tag 나열
//            var wordList = cardData.tags
//            val flexBoxAdapter =  KeywordViewAdapter(activity!!.baseContext, wordList!!)
//
//            FlexboxLayoutManager(this.context).apply {
//                flexWrap = FlexWrap.WRAP
//                flexDirection = FlexDirection.ROW
//                justifyContent = JustifyContent.FLEX_START
//            }
//
//            view!!.findViewById<RecyclerView>(R.id.recycler_view).adapter = flexBoxAdapter

            var f_listOfImages : ArrayList<Image> = cardData.frontImages!!
            var b_listOfImages : ArrayList<Image> = cardData.backImages!!
            var f_listOfContents : ArrayList<Contents> = cardData.frontContents!!
            var b_listOfContents : ArrayList<Contents> = cardData.backContents!!
            var f_listOfLinks : ArrayList<Link> = cardData.frontLinks!!
            var b_listOfLinks : ArrayList<Link> = cardData.backLinks!!

            drawImage(front_card, back_card, f_listOfImages)
            drawImage(front_card, back_card, b_listOfImages)
            drawText(front_card, back_card, f_listOfContents)
            drawText(front_card, back_card, b_listOfContents)
            drawLink(front_card, back_card, f_listOfLinks)
            drawLink(front_card, back_card, b_listOfLinks)

        }
    }

    fun drawImage(front_card : FrameLayout, back_card : FrameLayout, images : List<Image>) {

        for(i in images) {
            val image = ImageView(context)
            //크기 설정
            var imageLayoutParams = LinearLayout.LayoutParams(100, 100)
            image.layoutParams = imageLayoutParams
            image.x = i.coordinate!!.xAxis!!
            image.y = i.coordinate!!.yAxis!!
            Glide.with(context!!).load(i.imageUrl).into(image)
            if (image.getParent() != null)
                (image.getParent() as ViewGroup).removeView(
                    image
                )
            if(i.isFront) {
                front_card.addView(image)
            }else {
                back_card.addView(image)
            }
        }
    }

    fun drawText(front_card : FrameLayout, back_card : FrameLayout,contents : List<Contents>) {


        for(i in contents) {
            val text = TextView(context)
            var textLayoutParams = LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
            text.layoutParams = textLayoutParams

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
            }else {
                back_card.addView(text)
            }
        }
    }

    fun drawLink(front_card : FrameLayout, back_card : FrameLayout,contents : List<Link>) {


        for(i in contents) {
            val text = TextView(context)
            // 링크설정
            text.linksClickable = true

            // 상세설정
            text.setTextColor(Color.BLACK)

            text.x = i.coordinate!!.xAxis!!
            text.y = i.coordinate!!.yAxis!!
            text.text = i.linkText
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse((i.link)))
            text.setOnClickListener {
                startActivity(intent)
            }

            if (text.getParent() != null)
                (text.getParent() as ViewGroup).removeView(
                    text
                )

            if(i.isFront) {
                front_card.addView(text)
            }else {
                back_card.addView(text)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)



        //초기 card 01
        binding.btnCard01.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnCard01.setTextColor(Color.parseColor("#ffffff"))

//        binding.imageView.setImageResource(R.drawable.card011)
//        binding.imageView2.setImageResource(R.drawable.card012)







        getQRCode()

        //qr코드 버튼
        binding.btnQr.setOnClickListener {

            val intent = Intent(context, CreateQRActivity::class.java)
            intent.putExtra("QrUrl", imageString)
            Log.d("urllll2", imageString.toString())

            startActivity(intent,)



        }
        binding.homeCard1.setOnClickListener {
            VisitorComments()
        }



        //소셜 공유 버튼
        binding.btnSocialShare.setOnClickListener {
            val popupBase = binding.btnSocialShare
            Log.d("social","click")
            var pop = PopupMenu(context, popupBase)

            pop.menuInflater?.inflate(R.menu.social_popup,pop.menu)

            pop.show()

            pop.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.kakaotalk ->
                        Toast.makeText(context,"kakaotalk",Toast.LENGTH_SHORT).show()
                    R.id.instagram ->
                        Toast.makeText(context, "instagram",Toast.LENGTH_SHORT).show()

                }
                false
            }
        }

        //검색 버튼
        binding.btnSearch.setOnClickListener {
            val search = SearchFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea,search)
                addToBackStack(null)
                commit()
            }
        }

        //알람 버튼
        binding.btnAlarm.setOnClickListener {

            val alarm = AlarmFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea,alarm)
                addToBackStack(null)
                commit()
            }
        }
    }


    //--------------------------------함수----------------------------

    //qr코드 호출 함수
    var imageString = ""
    val accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg3OTM3MjQ3LCJleHAiOjE2OTA1MjkyNDd9.aiKbUg52Uj0rSvQTumCd_pfvc_SOlk6C4xKcaN1tZbE"
    private  fun getQRCode() {
        retAPI
            .getQRCode(accessToken)
            .enqueue(object : retrofit2.Callback<ResponseGetQRCode>{
                override fun onResponse(call: Call<ResponseGetQRCode>, response: Response<ResponseGetQRCode>) {
                    if (response.isSuccessful) {
                        imageString = response.body()?.address.toString()
//                    var toBitmap = BitmapFactory.decodeByteArray(image,0,image!!.size)
//                    var bitmap = BitmapFactory.decodeStream(imageString)
//                    val imageBytes = Base64.decode(imageString,0)
//                    val image = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)

                        Log.d("urllll", imageString.toString())

                        Log.d("getQr Response : ", "success")

                    } else {
                        Log.d("getQr Response : ", "Code: ${response.code()} , Message: ${response.message()} , Success: ${response.isSuccessful}")
                    }
                }
                override fun onFailure(call: Call<ResponseGetQRCode>, t: Throwable) {
                    Log.d("getQr Response : ", "Fail 2")
                }

            })
    }


    //방명록 호출 함수
    fun VisitorComments() {
        val visitorComments = VisitorCommentsFragment()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.frameArea,visitorComments)
            addToBackStack(null)
            commit()
        }
    }

    fun setEmptyCard(v : ImageView){
        v.setImageResource(R.drawable.template_empty_card)

    }





}


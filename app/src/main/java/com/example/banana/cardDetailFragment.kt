package com.example.banana

import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banana.data.Contents
import com.example.banana.data.Image
import com.example.banana.data.getCardResponseModel
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import retrofit2.Call
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var getCardDetailAPI : API = RetrofitInstance.retrofitInstance().create(API::class.java)

class cardDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_card_detail, container, false)

        var cardData : getCardResponseModel

        getCardDetailAPI.getCard(
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg3OTM3MjQ3LCJleHAiOjE2OTA1MjkyNDd9.aiKbUg52Uj0rSvQTumCd_pfvc_SOlk6C4xKcaN1tZbE",
            2
        ).enqueue(object : retrofit2.Callback<getCardResponseModel> {
            override fun onFailure(call: Call<getCardResponseModel>, t: Throwable) {
                Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
            }

            override fun onResponse(
                call: Call<getCardResponseModel>,
                response: Response<getCardResponseModel>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG - isSuccessful", response.body().toString())
                    cardData = response.body()!!
                    makeUI(cardData)
                } else {
                    Log.d("TAG - failed", response.code().toString())
                }
            }

        })
        return view
    }




    fun drawImage(front_card : FrameLayout, back_card : FrameLayout, images : List<Image>) {

        val image = ImageView(context)
        //크기 설정
        var imageLayoutParams = LinearLayout.LayoutParams(100, 100)
        image.layoutParams = imageLayoutParams
        for(i in images) {
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

        val text = TextView(context)
        var textLayoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        text.layoutParams = textLayoutParams

        // 상세설정
        text.setTextColor(Color.BLACK)

        for(i in contents) {
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

    fun drawLink(front_card : FrameLayout, back_card : FrameLayout,contents : List<Contents>) {

        val text = TextView(context)
        // 링크설정
        text.linksClickable = true

        // 상세설정
        text.setTextColor(Color.BLACK)

        for(i in contents) {
            text.x = i.coordinate!!.xAxis!!
            text.y = i.coordinate!!.yAxis!!
            text.text = i.content
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse((i.content)))
            text.setOnClickListener {
                startActivity(intent)
            }
            if(i.contentSize == "h1") {
                text.setTextSize(12.0f)
            }else {
                text.setTextSize(16.0f)
            }
            if(i.isFront) {
                front_card.addView(text)
            }else {
                back_card.addView(text)
            }
        }
    }

    fun makeUI(cardData : getCardResponseModel) {

        Log.d("TAG", cardData.toString())
        val front_card = view!!.findViewById<FrameLayout>(R.id.detailed_card)
        val back_card = view!!.findViewById<FrameLayout>(R.id.detailed_card_back)

        if(cardData.frontTemplateColor != null) {
            // template 색깔 저장
            front_card.setBackgroundColor(Color.parseColor(cardData.frontTemplateColor))
            back_card.setBackgroundColor(Color.parseColor(cardData.backTemplateColor))

            // tag 나열
            var wordList = cardData.tags
            val flexBoxAdapter =  KeywordViewAdapter(activity!!.baseContext, wordList!!)

            FlexboxLayoutManager(this.context).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }

            view!!.findViewById<RecyclerView>(R.id.recycler_view).adapter = flexBoxAdapter

            var f_listOfImages : ArrayList<Image> = cardData.frontImages!!
            var b_listOfImages : ArrayList<Image> = cardData.backImages!!
            var f_listOfContents : ArrayList<Contents> = cardData.frontContents!!
            var b_listOfContents : ArrayList<Contents> = cardData.backContents!!

            drawImage(front_card, back_card, f_listOfImages)
            drawImage(front_card, back_card, b_listOfImages)
            drawText(front_card, back_card, f_listOfContents)
            drawText(front_card, back_card, b_listOfContents)
        }
    }

    fun getCardData() : getCardResponseModel {
        var cardData : getCardResponseModel = getCardResponseModel(null, null, null, null, null, null, null, null, null, null, null);
        getCardDetailAPI.getCard(
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg3OTM3MjQ3LCJleHAiOjE2OTA1MjkyNDd9.aiKbUg52Uj0rSvQTumCd_pfvc_SOlk6C4xKcaN1tZbE",
            1
        ).enqueue(object : retrofit2.Callback<getCardResponseModel> {
            override fun onFailure(call: Call<getCardResponseModel>, t: Throwable) {
                Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
            }

            override fun onResponse(
                call: Call<getCardResponseModel>,
                response: Response<getCardResponseModel>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG - isSuccessful", response.body().toString())
                    cardData = response.body()!!
                } else {
                    if(response.code().toString() == "401") {
                    }
                    Log.d("TAG - failed", response.code().toString())
                }
            }

        })

        return cardData
    }
}

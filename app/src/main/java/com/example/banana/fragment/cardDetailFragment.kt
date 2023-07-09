package com.example.banana.fragment

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banana.R
import com.example.banana.activity.FragmentActivity
import com.example.banana.adapter.KeywordViewAdapter
import com.example.banana.auth.authApplication
import com.example.banana.data.Contents
import com.example.banana.data.Image
import com.example.banana.data.Link
import com.example.banana.data.getCardResponseModel
import com.example.banana.deleteCardDialog
import com.example.banana.fragment.HomeFragment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import retrofit2.Call
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var getCardDetailAPI : API = RetrofitInstance.retrofitInstance().create(API::class.java)
lateinit var fragmentActivty: FragmentActivity
class cardDetailFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentActivty = context as FragmentActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_card_detail, container, false)

        view.findViewById<Button>(R.id.card_delete_button).setOnClickListener {
            val dlg = deleteCardDialog(fragmentActivty)
            dlg.listener = object: deleteCardDialog.LessonDeleteDialogClickedListener {
                override fun delete() {
                    // 삭제하는 api 호출
                    val transaction: FragmentTransaction =
                        activity!!.supportFragmentManager.beginTransaction()
                    val homeFragment = HomeFragment()
                    transaction.replace(R.id.frameArea, homeFragment)
                    transaction.commit()
                }

                override fun choose_no_delete() {
                    // do nothing -> 아무일도 일어나지 않음
                }
            }
            dlg.start()
        }

        var cardData : getCardResponseModel
        getCardDetailAPI.getCard(
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg3OTM3MjQ3LCJleHAiOjE2OTA1MjkyNDd9.aiKbUg52Uj0rSvQTumCd_pfvc_SOlk6C4xKcaN1tZbE",
            Integer.parseInt(authApplication.prefs.getString("cardId", "-1")).toLong()
        ).enqueue(object : retrofit2.Callback<getCardResponseModel> {
            override fun onFailure(call: Call<getCardResponseModel>, t: Throwable) {
                Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
                val transaction: FragmentTransaction =
                    activity!!.supportFragmentManager.beginTransaction()
                val homeFragment = HomeFragment()
                transaction.replace(R.id.frameArea, homeFragment)
                transaction.commit()
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
                    val transaction: FragmentTransaction =
                        activity!!.supportFragmentManager.beginTransaction()
                    val homeFragment = HomeFragment()
                    transaction.replace(R.id.frameArea, homeFragment)
                    transaction.commit()
                }
            }

        })
        return view
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
            var textLayoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
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


}

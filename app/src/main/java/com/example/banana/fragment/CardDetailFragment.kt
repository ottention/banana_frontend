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
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banana.R
import com.example.banana.activity.FragmentActivity
import com.example.banana.activity.MainActivity
import com.example.banana.activity.MakeCardActivity
import com.example.banana.adapter.KeywordViewAdapter
import com.example.banana.data.ChartData
import com.example.banana.data.Contents
import com.example.banana.data.Image
import com.example.banana.data.Link
import com.example.banana.data.getCardResponseModel
import com.example.banana.data.linkIndexData
import com.example.banana.databinding.FragmentCardDetailBinding
import com.example.banana.databinding.FragmentChartBinding
import com.example.banana.deleteCardDialog
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import com.example.banana.viewModel.ChartViewModel
import com.example.banana.viewModel.DetailCardDataViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import okhttp3.internal.immutableListOf
import org.apache.commons.lang3.ObjectUtils.Null
import retrofit2.Call
import retrofit2.Response

var CardAPI: API = RetrofitInstance.retrofitInstance().create(API::class.java)

lateinit var fragmentActivty: FragmentActivity

class CardDetailFragment : Fragment() {

    lateinit var bundle: Bundle
    private lateinit var detailCardDataViewModel: DetailCardDataViewModel
    private lateinit var binding: FragmentCardDetailBinding
    private var cardData: getCardResponseModel? = null
    var btnIconSoucre = immutableListOf(
        R.drawable.icon_notion_black,
        R.drawable.icon_creditcard_black,
        R.drawable.icon_phone_book_black,
        R.drawable.icon_work_black,
        R.drawable.icon_bookmark_black,
        R.drawable.icon_check_black,
        R.drawable.icon_mail_black,
        R.drawable.icon_phone_blakc,
        R.drawable.icon_time_black,
        R.drawable.icon_graduation_black
    )
    var btnLinkSoucre = immutableListOf(
        R.drawable.icon_etc_link,
        R.drawable.icon_twitter_link,
        R.drawable.icon_instagram_link
    )

    fun newInstance(): CardDetailFragment {
        return CardDetailFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentActivty = context as FragmentActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card_detail, container, false)
        val view = binding.root


        if (bundle == null) {
            // 뒤로가게 하기
            getActivity()!!.getSupportFragmentManager().beginTransaction().remove(this).commit();
            getActivity()!!.getSupportFragmentManager().popBackStack();
        }
        detailCardDataViewModel = ViewModelProvider(this).get(DetailCardDataViewModel::class.java)
        var c = bundle.getLong("cardId")
        detailCardDataViewModel.getCardData(c)
        Log.d("TAG", "done")
        cardData = getCardResponseModel(
            0,
            true,
            true,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )

        detailCardDataViewModel.cardData.observe(viewLifecycleOwner) {
            cardData = it
            makeUI(cardData!!)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardDeleteButton.setOnClickListener {
            val dlg = deleteCardDialog(fragmentActivty)
            dlg.listener = object : deleteCardDialog.LessonDeleteDialogClickedListener {
                override fun delete() {
                    CardAPI.removeMyCard(
                        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4",
                        bundle!!.getLong("cardId")
                    ).enqueue(object : retrofit2.Callback<Null> {
                        override fun onFailure(call: Call<Null>, t: Throwable) {
                            Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
                        }

                        override fun onResponse(
                            call: Call<Null>,
                            response: Response<Null>
                        ) {
                            if (response.isSuccessful) {
                                Log.d("TAG - isSuccessful", response.body().toString())
                                val transaction: FragmentTransaction =
                                    activity!!.supportFragmentManager.beginTransaction()
                                val homeFragment = HomeFragment()
                                transaction.replace(R.id.frameArea, homeFragment)
                                transaction.commit()
                            } else {
                                Log.d("TAG - failed", response.code().toString())
                            }
                        }

                    })
                }

                override fun choose_no_delete() {
                    // do nothing -> 아무일도 일어나지 않음
                }
            }
            dlg.start()
        }

        binding.btnCardEdit.setOnClickListener {
            val intent = Intent(getActivity(), MakeCardActivity::class.java)
            intent.putExtra("cardData", cardData)
            intent.putExtra("cardId", bundle!!.getLong("cardId"))
            startActivity(intent)
        }
    }

    fun drawImage(
        c: Context,
        front_card: FrameLayout,
        back_card: FrameLayout,
        images: List<Image>
    ) {

        for (i in images) {
            val image = ImageView(c)
            //크기 설정
            var imageLayoutParams = LinearLayout.LayoutParams(100, 100)
            image.layoutParams = imageLayoutParams
            image.x = i.coordinate!!.xAxis!!
            image.y = i.coordinate!!.yAxis!!

            if (i.imageUrl.length <= 2) {
                Glide.with(c!!).load(btnIconSoucre[i.imageUrl.toInt()]).into(image)
            } else {
                Glide.with(c!!).load(i.imageUrl).into(image)
            }
            if (image.getParent() != null)
                (image.getParent() as ViewGroup).removeView(
                    image
                )
            if (i.isFront) {
                front_card.addView(image)
            } else {
                back_card.addView(image)
            }

        }

    }

    fun drawText(
        c: Context,
        front_card: FrameLayout,
        back_card: FrameLayout,
        contents: List<Contents>
    ) {

        for (i in contents) {
            val text = TextView(c)
            var textLayoutParams =
                LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            text.layoutParams = textLayoutParams

            // 상세설정
            text.setTextColor(Color.BLACK)
            text.x = i.coordinate!!.xAxis!!
            text.y = i.coordinate!!.yAxis!!
            text.text = i.content

            if (i.contentSize == "h1") {
                text.setTextSize(12.0f)
            } else {
                text.setTextSize(16.0f)
            }
            if (text.getParent() != null)
                (text.getParent() as ViewGroup).removeView(
                    text
                )
            if (i.isFront) {
                front_card.addView(text)
            } else {
                back_card.addView(text)
            }
        }
    }

    fun drawLink(
        c: Context,
        front_card: FrameLayout,
        back_card: FrameLayout,
        contents: List<Link>
    ) {

        for (i in contents) {
            val image = ImageView(c)
            image.isClickable = true

            var index = i.linkText.toInt()
            image.background = ContextCompat.getDrawable(c, btnLinkSoucre[index])
            image.x = i.coordinate!!.xAxis!!
            image.y = i.coordinate!!.yAxis!!
            var link_intent = Intent(Intent.ACTION_VIEW, Uri.parse((i.link)))
            image.setOnClickListener {
                startActivity(link_intent)
            }
            var imageLayoutParams =
                LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            image.layoutParams = imageLayoutParams
            image.scaleType = ImageView.ScaleType.MATRIX

            if (i.isFront) {
                front_card.addView(image)
            } else {
                back_card.addView(image)
            }
        }
    }

    fun makeUI(cardData: getCardResponseModel) {

        Log.d("TAG - makeUI", cardData.toString())
        val front_card = view!!.findViewById<FrameLayout>(R.id.detailed_card)
        val back_card = view!!.findViewById<FrameLayout>(R.id.detailed_card_back)

        if (cardData.frontTemplateColor != null) {
            // template 색깔 저장
            front_card.setBackgroundColor(Color.parseColor(cardData.frontTemplateColor))
            back_card.setBackgroundColor(Color.parseColor(cardData.backTemplateColor))

            // tag 나열
            var wordList = cardData.tags
            val flexBoxAdapter = KeywordViewAdapter(activity!!.baseContext, wordList!!)

            FlexboxLayoutManager(this.context).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }

            view!!.findViewById<RecyclerView>(R.id.recycler_view).adapter = flexBoxAdapter

            var f_listOfImages: ArrayList<Image> = cardData.frontImages!!
            var b_listOfImages: ArrayList<Image> = cardData.backImages!!
            var f_listOfContents: ArrayList<Contents> = cardData.frontContents!!
            var b_listOfContents: ArrayList<Contents> = cardData.backContents!!
            var f_listOfLinks: ArrayList<Link> = cardData.frontLinks!!
            var b_listOfLinks: ArrayList<Link> = cardData.backLinks!!

            drawImage(context!!, front_card, back_card, f_listOfImages)
            drawImage(context!!, front_card, back_card, b_listOfImages)
            drawText(context!!, front_card, back_card, f_listOfContents)
            drawText(context!!, front_card, back_card, b_listOfContents)
            drawLink(context!!, front_card, back_card, f_listOfLinks)
            drawLink(context!!, front_card, back_card, b_listOfLinks)

        }
    }


}

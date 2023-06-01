package com.example.banana

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [cardDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class cardDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var wordList = listOf<String>("학생", "디자이너", "취준생", "으악", "에휴", "에구구")

        val view = inflater.inflate(R.layout.fragment_card_detail, container, false)
        val flexBoxAdapter =  KeywordViewAdapter(activity!!.baseContext, wordList)

        FlexboxLayoutManager(this.context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
            alignItems = AlignItems.FLEX_START
        }

        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = flexBoxAdapter
        val canvas = view.findViewById<FrameLayout>(R.id.detailed_card)
        drawImage(canvas)
        drawText(canvas)
        return view
    }

    fun drawImage(canvas : FrameLayout) {
        val image = ImageView(context)

        // 위치
        val x = 100.0f
        val y = 100.0f

        // 크기
        val width = 100
        val height = 100

        // 위치 설정
        image.x = x
        image.y = y

        //크기 설정
        var imageLayoutParams = LinearLayout.LayoutParams(width, height)
        image.layoutParams = imageLayoutParams
        image.setImageResource(R.drawable.test_image)

//        Glide.with(context).load(foodImages[i]).into(image)
        canvas.addView(image)
    }

    fun drawText(canvas : FrameLayout) {

        val text = TextView(context)
        // 위치
        val x = 100.0f
        val y = 100.0f
        // 크기
        val width = 100
        val height = 100
        // 위치 설정
        text.x = x
        text.y = y

        // 상세설정
        text.setTextColor(Color.BLACK)
        text.setTextSize(12.0f)

        //크기 설정
        var textLayoutParams = LinearLayout.LayoutParams(width, height)
        text.layoutParams = textLayoutParams
        text.text = "여기에 받아온 값 리스트에서 빼서 하나씩" // 근데 폰트 크기는 어떻게 해;;;
        canvas.addView(text)
    }

    fun drawLink(canvas : FrameLayout) {
        val text = TextView(context)
        // 위치
        val x = 100.0f
        val y = 100.0f
        // 크기
        val width = 100
        val height = 100
        // 위치 설정
        text.x = x
        text.y = y

        //크기 설정
        var textLayoutParams = LinearLayout.LayoutParams(width, height)
        text.layoutParams = textLayoutParams
        text.text = "여기에 받아온 값 리스트에서 빼서 하나씩" // 근데 폰트 크기는 어떻게 해;;;
        text.setTextColor(Color.BLACK)
        text.setTextSize(12.0f)

        // 링크설정
        text.linksClickable = true
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(("text값")))
        text.setOnClickListener {
            startActivity(intent)
        }

        canvas.addView(text)
    }


}

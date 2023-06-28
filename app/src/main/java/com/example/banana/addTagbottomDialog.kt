package com.example.banana

import android.content.Context
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class addTagbottomDialog (context: Context) : BottomSheetDialogFragment()
{

    lateinit var tagList : MutableList<String>
    lateinit var  flexBoxAdapter : TagViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        tagList = mutableListOf()
        tagList.add("학생")
        tagList.add("디자이너")
        tagList.add("학생")
        tagList.add("디자이너")
        tagList.add("학생")
        tagList.add("디자이너")
        tagList.add("학생")
        tagList.add("디자이너")
        tagList.add("학생")
        tagList.add("디자이너")

        val view = inflater.inflate(R.layout.fragment_tag_bottomsheet, container, false)

        val tagAd = TagViewAdapter(activity!!.baseContext, tagList)
        flexBoxAdapter =  tagAd
        FlexboxLayoutManager(this.context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.COLUMN
            justifyContent = JustifyContent.CENTER
        }.let {
            view.findViewById<RecyclerView>(R.id.tag_recycler_view).adapter = flexBoxAdapter
            view.findViewById<RecyclerView>(R.id.tag_recycler_view).layoutManager = it
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        view?.findViewById<Button>(R.id.addTag)?.setOnClickListener {
            if(tagList.size == 10) {
                var toast = Toast.makeText(context, "태그는 최대 10개까지 생성가능합니다.", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
            }else {
                val newTag = view?.findViewById<EditText>(R.id.new_tag)?.text.toString()
                tagList.add(newTag)
                flexBoxAdapter.notifyDataSetChanged()
            }
        }
    }

    fun removeTag(pos : Int) {
        tagList.removeAt(pos)
        flexBoxAdapter.notifyDataSetChanged()
    }

}
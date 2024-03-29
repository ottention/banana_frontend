package com.example.banana

import android.content.Context
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
import com.example.banana.adapter.TagViewAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class addTagbottomDialog (context: Context, tagList: MutableList<String>, private val onSave: (tagList: MutableList<String>) -> Unit) : BottomSheetDialogFragment()
{

    var tagList : MutableList<String> = tagList
    lateinit var  flexBoxAdapter : TagViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)

        val view = inflater.inflate(R.layout.fragment_tag_bottomsheet, container, false)

        val tagAd = TagViewAdapter(activity!!.baseContext, tagList, onClickDelete = {removeTag(it)})
        flexBoxAdapter =tagAd

        FlexboxLayoutManager(this.context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
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
                view?.findViewById<EditText>(R.id.new_tag)!!.setText("")
                flexBoxAdapter.notifyDataSetChanged()
            }
        }
    }

    fun removeTag(pos : Int) {
        tagList.removeAt(pos)
        flexBoxAdapter.notifyDataSetChanged()
    }
}
package com.example.banana

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.example.banana.adapter.TagViewAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class addNotebottomDialog(context: Context, var note : String) : BottomSheetDialogFragment()
{
    lateinit var listener: addNoteListner

    interface addNoteListner{
        fun save(note : String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        val view = inflater.inflate(R.layout.fragment_add_note_bottomsheet, container, false)
        var text_area = view.findViewById<EditText>(R.id.text_note)
        if(note != "")
            text_area.setText(note)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        view?.findViewById<Button>(R.id.btn_add_note)?.setOnClickListener {
            listener.save(view?.findViewById<EditText>(R.id.text_note)?.text.toString())
            dismiss()
        }
    }


}
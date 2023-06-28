package com.example.banana

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class TagViewAdapter(val context : Context, val stringList : List<String>) : RecyclerView.Adapter<TagViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(com.example.banana.R.layout.tag_item, parent, false)
//        FlexboxLayoutManager(this.context).apply {
//            flexWrap = FlexWrap.WRAP
//            flexDirection = FlexDirection.COLUMN
//            justifyContent = JustifyContent.CENTER
//        }
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stringList[position])
    }

    override fun getItemCount(): Int {
        return stringList.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bind(item : String) {
            itemView.findViewById<TextView>(com.example.banana.R.id.tag_box).text = item
            itemView.findViewById<ImageButton>(R.id.delete_tag_btn).setOnClickListener(View.OnClickListener {
                var pos = adapterPosition
                addTagbottomDialog(context).removeTag(pos)
            })
        }

    }
}


package com.example.banana

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignSelf

import com.google.android.flexbox.FlexboxLayoutManager




class KeywordViewAdapter(val context : Context, val stringList : ArrayList<String>) : RecyclerView.Adapter<KeywordViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(com.example.banana.R.layout.keyword_item, parent, false)
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
            itemView.findViewById<TextView>(com.example.banana.R.id.keyword_box).text = item
//            var lp = itemView.getLayoutParams()
//            if (lp is FlexboxLayoutManager.getLayoutParams()) {
//                val flexboxLp = lp as FlexboxLayoutManager.LayoutParams
//                flexboxLp.flexGrow = 1.0f
//                flexboxLp.alignSelf = AlignSelf.FLEX_END
//            }
        }

    }
}
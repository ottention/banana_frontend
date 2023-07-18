package com.example.banana.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.banana.R


class TagViewAdapter(val context : Context, val stringList : List<String>, private val onClickDelete: (pos: Int) -> Unit) : RecyclerView.Adapter<TagViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tag_item, parent, false)

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
            itemView.findViewById<TextView>(R.id.tag_box).text = item
            itemView.findViewById<ImageView>(R.id.delete_tag_btn).setOnClickListener {
                onClickDelete.invoke(position)
                notifyItemRemoved(position)
            }
        }

    }
}


package com.example.banana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banana.data.CommentsData

class CommentsAdapter(private val commentData : ArrayList<CommentsData>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        var rv_item_writer : TextView
        var rv_item_content : TextView
        var rv_item_localDateTime : TextView


        init{
            rv_item_writer = view.findViewById(R.id.textview_writer)
            rv_item_content = view.findViewById(R.id.textview_content)
            rv_item_localDateTime = view.findViewById(R.id.textview_localDateTime)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comments_rv_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.rv_item_writer.text = commentData[position].writer
        holder.rv_item_content.text = commentData[position].content
        holder.rv_item_localDateTime.text = commentData[position].localDateTime

    }

}
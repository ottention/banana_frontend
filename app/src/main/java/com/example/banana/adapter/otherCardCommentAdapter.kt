package com.example.banana.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.banana.R
import com.example.banana.addLinkDialog
import com.example.banana.commentDeleteOrUpdateDialog
import com.example.banana.data.AlarmData
import com.example.banana.data.comment
import com.example.banana.fragment.ChartCardDetailFragment
import com.example.banana.viewModel.CommentDetailViewModel

class otherCardCommentAdapter (val context : Context, var commentData : ArrayList<comment>, private val onClickUpdate: (comment: comment) -> Unit, private val onDelete:(comment: comment) -> Unit) : RecyclerView.Adapter<otherCardCommentAdapter.ViewHolder>() {

    private lateinit var commentViewModel: CommentDetailViewModel

    fun updateList(newList : ArrayList<comment>) {
        commentData.clear()
        commentData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        Log.d("TAG in adapter", commentData.toString())
        val view = LayoutInflater.from(context).inflate(R.layout.chart_comment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentData[position])
    }

    override fun getItemCount(): Int {
        return commentData.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bind(item : comment) {
            Log.d("TAG", "item +")
            itemView.findViewById<TextView>(R.id.date).text = item.localDateTime;
            itemView.findViewById<TextView>(R.id.comment_area).text = item.content

            itemView.findViewById<TextView>(R.id.comment_area).setOnLongClickListener {
                val dlg = commentDeleteOrUpdateDialog(context)
                dlg.listener = object: commentDeleteOrUpdateDialog.CommentListener {
                    override fun delete() {
                        onDelete.invoke(item)
                    }

                    override fun update() {
                        onClickUpdate.invoke(item)
                    }
                }
                dlg.start()
                true
            }
        }

    }
}
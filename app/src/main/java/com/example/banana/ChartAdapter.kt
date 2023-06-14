package com.example.banana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banana.data.ChartData

class ChartAdapter (
    private val chartData: ArrayList<ChartData>,
    ) : RecyclerView.Adapter<ChartAdapter.ViewHolder>(){

   class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
       var rv_chart_card : ImageView
       var rv_rank_text : TextView
       var rv_favorite_count : TextView
       var rv_btn_heart : ImageView

       init {
           rv_chart_card = view.findViewById(R.id.chart_card_image)
           rv_rank_text = view.findViewById(R.id.chart_rank)
           rv_favorite_count = view.findViewById(R.id.favorite_count)
           rv_btn_heart = view.findViewById(R.id.btn_heart)
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chart_rv_item,parent,false)
        return ViewHolder(view)
    }

    interface ItemClick {
        fun onClick(view: View,position: Int)
    }
    var itemClick : ItemClick? = null


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rv_chart_card.setImageResource(chartData[position].chartCard)
        holder.rv_rank_text.text = chartData[position].rankText
        holder.rv_favorite_count.text = chartData[position].favoriteCount

        if(itemClick != null) {
            holder?.itemView!!.setOnClickListener{v ->
                itemClick!!.onClick(v,position)
            }
        }


    }

    override fun getItemCount(): Int {
        return chartData.size
    }


}
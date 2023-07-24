package com.example.banana.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.banana.R
import com.example.banana.data.ChartData
import com.example.banana.databinding.ChartRvItemBinding

class ChartAdapter (
//    private val chartData: ArrayList<ChartData>,
    //아래는 viewmodel 위한 임시 배열리스트
    private val chartList: MutableList<ChartData> = mutableListOf(),
    val onCardClick : (()->Unit)? = null,
    val onLikeClick : (()->Unit)? = null
    ) : RecyclerView.Adapter<ChartAdapter.ViewHolder>(){

    fun updateList(newList : ArrayList<ChartData>) {
        chartList.clear()
        chartList.addAll(newList)
        notifyDataSetChanged()
    }

   inner class ViewHolder(val binding: ChartRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
       var rv_chart_card : ImageView = binding.chartCardImage
       var rv_rank_text : TextView = binding.chartRank
       var rv_favorite_count : TextView = binding.favoriteCount
       var rv_btn_heart : ImageView = binding.btnHeart

       fun bind(chart : ChartData){
           binding.chart = chart
           binding.setCardClick {
               onCardClick!!()
           }
            binding.setLikeClick {
                onLikeClick!!()

//                binding.btnHeart?.isSelected = binding.btnHeart?.isSelected != true
                if(binding.btnHeart?.isSelected == true) {
                    binding.btnHeart?.isSelected = false
                    var like = chart.favoriteCount.toInt()
                    like -=1
                    chart.favoriteCount = like.toString()
                    notifyDataSetChanged()
                } else {
                    binding.btnHeart?.isSelected = true
                    var like = chart.favoriteCount.toInt()
                    like +=1
                    chart.favoriteCount = like.toString()
                    notifyDataSetChanged()
                }

            }
       }




   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = DataBindingUtil.inflate<ChartRvItemBinding>(LayoutInflater.from(parent.context),
            R.layout.chart_rv_item,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rv_chart_card.setImageResource(chartList[position].chartCard)
        holder.rv_rank_text.text = chartList[position].rankText
        holder.rv_favorite_count.text = chartList[position].favoriteCount


        holder.bind(chartList[position])


    }

    override fun getItemCount(): Int {
        return chartList.size
    }


}
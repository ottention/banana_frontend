package com.example.banana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banana.R
import com.example.banana.data.ChartData
import com.example.banana.data.RecentSearchData
import com.example.banana.data.TopTenTags

class RecentSearchAdapter (private val recentSearchData: MutableList<TopTenTags> = mutableListOf()) : RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>(){

    fun updateList(newList : ArrayList<TopTenTags>) {
        recentSearchData.clear()
        recentSearchData.addAll(newList)
        notifyDataSetChanged()
    }
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var rv_item_recentSearchWord : TextView

        init {
            rv_item_recentSearchWord = view.findViewById(R.id.rv_item_search)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_rv_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return recentSearchData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rv_item_recentSearchWord.text = recentSearchData[position].tag
    }

}
package com.example.banana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banana.R
import com.example.banana.data.WalletData

class WalletAdapter (private val walletData : ArrayList<WalletData>): RecyclerView.Adapter<WalletAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var rv_item_cardImage : ImageView
        var rv_item_text : TextView

        init{
            rv_item_cardImage = view.findViewById(R.id.rv_item_cardImage)
            rv_item_text = view.findViewById(R.id.rv_item_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wallet_rv_item,parent,false)
        return ViewHolder(view)
    }

    interface ItemClick {
        fun onClick(view: View,position: Int)
    }
    var itemClick : ItemClick? = null

    override fun getItemCount(): Int {
        return walletData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rv_item_cardImage.setImageResource(walletData[position].card)
        holder.rv_item_text.text = walletData[position].text

        if(itemClick != null) {
            holder?.itemView!!.setOnClickListener{v ->
                itemClick!!.onClick(v,position)
            }
        }
    }
}
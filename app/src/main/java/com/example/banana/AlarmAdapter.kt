package com.example.banana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banana.data.AlarmData

class AlarmAdapter(private var alarmData : ArrayList<AlarmData>) : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        var rv_item_alarmTime : TextView
        var rv_item_alarmText : TextView

        init {
            rv_item_alarmTime = view.findViewById(R.id.textview_alarmTime)
            rv_item_alarmText = view.findViewById(R.id.textview_alarmtext)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.alarm_rv_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return alarmData.size
    }

    interface ItemClick {
        fun onClick(view: View,position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.rv_item_alarmTime.text = alarmData[position].alarmTime
        holder.rv_item_alarmText.text = alarmData[position].alarmText

        if(itemClick != null) {
            holder?.itemView!!.setOnClickListener{v ->
                itemClick!!.onClick(v,position)
            }
        }
    }
}

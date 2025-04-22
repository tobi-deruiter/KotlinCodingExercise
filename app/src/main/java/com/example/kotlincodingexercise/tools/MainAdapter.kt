package com.example.kotlincodingexercise.tools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincodingexercise.R
import com.example.kotlincodingexercise.datamodels.Item

class MainAdapter(private val list: List<Item>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listIdView: TextView = itemView.findViewById(R.id.card_listid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_id_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.listIdView.text = item.listId.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}